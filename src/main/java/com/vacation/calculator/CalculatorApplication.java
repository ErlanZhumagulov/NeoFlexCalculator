package com.vacation.calculator;

import com.vacation.calculator.dto.request.VacationRequest;
import com.vacation.calculator.dto.response.VacationResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorApplication {


	public static void main(String[] args) {

		SpringApplication.run(CalculatorApplication.class, args);
	}

}
