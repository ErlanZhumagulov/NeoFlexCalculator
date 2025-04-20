package com.vacation.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Set;

@Configuration
public class HolidayConfig {

    @Bean
    public Set<LocalDate> holidays() { // Устанавливаем дополнительно выходные дни
        return Set.of(
            LocalDate.of(2025, 5, 9), // 9 мая
            LocalDate.of(2025, 8, 3), // 8 марта
            LocalDate.of(2025, 2, 23) // 23 февраля

        );
    }
}
