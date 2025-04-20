package com.vacation.calculator.controller;

import com.vacation.calculator.dto.request.VacationRequest;
import com.vacation.calculator.dto.response.VacationResponse;
import com.vacation.calculator.service.VacationCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/calculate")
@Validated
public class VacationController {

    @Autowired
    private VacationCalculatorService calculator;


    @GetMapping
    public ResponseEntity<VacationResponse> calculate(@Valid VacationRequest request) {
        VacationResponse result = calculator.calculate(request);
        return ResponseEntity.ok(result);
    }
}
