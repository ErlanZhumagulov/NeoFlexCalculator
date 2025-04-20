package com.vacation.calculator.service;

import com.vacation.calculator.controller.VacationController;
import com.vacation.calculator.dto.request.VacationRequest;
import com.vacation.calculator.dto.response.VacationResponse;
import com.vacation.calculator.service.VacationCalculatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VacationController.class)
public class VacationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacationCalculatorService calculatorService;

    // Тест валидного запроса
    @Test
    void calculate_ValidRequest_ReturnsOk() throws Exception {
        VacationRequest request = new VacationRequest(3650.0, 10, null);
        Mockito.when(calculatorService.calculate(request))
                .thenReturn(new VacationResponse(1200.0, null, null));

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("avgSalary", "3650.0")
                        .param("vacationDays", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(1200.0));
    }

    // Тест невалидного запроса (отрицательная зарплата)
    @Test
    void calculate_InvalidRequest_ReturnsBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("avgSalary", "-100")
                        .param("vacationDays", "10"))
                .andExpect(status().isBadRequest());
    }
}