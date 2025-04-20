package com.vacation.calculator.service;

import com.vacation.calculator.dto.request.VacationRequest;
import com.vacation.calculator.dto.response.VacationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Service
public class VacationCalculatorService {


    @Autowired
    private Set<LocalDate> holidaySet;

    public VacationResponse calculate(VacationRequest request) {

        System.out.println("chek   " + holidaySet.size());

        VacationResponse vacationResponse;


        if (request.getStartDate() != null) {
            vacationResponse = calculateWithHoliday(request);
        } else {
            vacationResponse = calculateWithoutHoliday(request);
        }

        return vacationResponse;

    }

    /**
     * Возвращаем payout, если отсутствует дата начала отпуска.
     */
    private VacationResponse calculateWithoutHoliday(VacationRequest request) {
        double dailyRate = request.getAvgSalary() * 12.0 / 365.0;
        double payout = dailyRate * request.getVacationDays();

        return new VacationResponse(
                payout, null, null
        );
    }

    /**
     * Если указана дата начала, то учитываем дополнительно праздники:
     * Идём по дням от startDate, считаем только рабочие,
     * пока не “наберём” vacationDays.
     * Возвращаем payout и фактический endDate.
     */
    private VacationResponse calculateWithHoliday(VacationRequest request) {
        LocalDate start = request.getStartDate();
        int daysToAccrue = request.getVacationDays();
        double dailyRate = request.getAvgSalary() * 12.0 / 365.0;

        int counted = 0;
        LocalDate cursor = start;
        while (counted < daysToAccrue) {
            if (isWorkingDay(cursor)) {
                counted++;
            }
            cursor = cursor.plusDays(1);
        }

        LocalDate endDate = cursor.minusDays(1);
        double payout = dailyRate * daysToAccrue;

        return new VacationResponse(
                payout,
                start,
                endDate
        );
    }

    /**
     * Выходные — суббота/воскресенье или дата из holidaySet.
     */
    private boolean isWorkingDay(LocalDate date) {
        DayOfWeek w = date.getDayOfWeek();
        if (w == DayOfWeek.SATURDAY || w == DayOfWeek.SUNDAY) return false;
        return !holidaySet.contains(date);
    }


}



