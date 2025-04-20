package com.vacation.calculator.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationResponse {

    private double amount; // Положенная сумма для выплаты
    private LocalDate startDate; // Дата начала отпуска (копируется из запроса)
    private LocalDate endDate; // Дата окончания отпуска (высчитывается при указании даты начала отпуска с учетом праздничных дней)


}
