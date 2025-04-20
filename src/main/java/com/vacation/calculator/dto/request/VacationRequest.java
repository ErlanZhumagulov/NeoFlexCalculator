package com.vacation.calculator.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import javax.validation.constraints.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequest {

    @Positive
    private double avgSalary; // Средняя месячная зарплата за 12 месяцев

    @Positive
    private int vacationDays; // Дни отпуска

    private LocalDate startDate; // Дата начала отпуска


}
