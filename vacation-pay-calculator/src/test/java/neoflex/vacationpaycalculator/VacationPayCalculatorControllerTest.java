package neoflex.vacationpaycalculator;

import neoflex.vacationpaycalculator.controller.VacationPayCalculatorController;
import neoflex.vacationpaycalculator.service.PlainVacationPayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VacationPayCalculatorController.class)
public class VacationPayCalculatorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    PlainVacationPayService service;

    private double vacationPay;

    @BeforeEach
    public void setUp() {

        vacationPay = 9556.32;
    }


    @DisplayName("MockMvc test for getVacationPay method")
    @Test
    void givenCorrectData_whenGetVacationPay_thenReturnVacationPay() throws Exception {
        when(service.calculate(anyInt(), anyInt())).thenReturn(vacationPay);

        mvc.perform(get("/calculate")
                   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                   .param("salary", "40000")
                   .param("days", "7")
                   .characterEncoding(StandardCharsets.UTF_8))
           .andExpect(status().isOk());

    }

    @DisplayName("MockMvc test for getVacationPay method (negative scenario)")
    @Test
    void givenNegativeSalary_whenGetVacationPay_thenThrowException() throws Exception {

        mvc.perform(get("/calculate")
                   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                   .param("salary", "-40000")
                   .param("days", "7")
                   .characterEncoding(StandardCharsets.UTF_8))
           .andExpect(status().is(400));

    }

    @DisplayName("MockMvc test for getVacationPay method (negative scenario)")
    @Test
    void givenNegativeDays_whenGetVacationPay_thenThrowException() throws Exception {

        mvc.perform(get("/calculate")
                   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                   .param("salary", "40000")
                   .param("days", "-7")
                   .characterEncoding(StandardCharsets.UTF_8))
           .andExpect(status().is(400));

    }

    @DisplayName("MockMvc test for getVacationPay method (negative scenario)")
    @Test
    void givenZeroDays_whenGetVacationPay_thenThrowException() throws Exception {

        mvc.perform(get("/calculate")
                   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                   .param("salary", "40000")
                   .param("days", "0")
                   .characterEncoding(StandardCharsets.UTF_8))
           .andExpect(status().is(400));

    }

    @DisplayName("MockMvc test for getVacationPay method (negative scenario)")
    @Test
    void givenZeroSalary_whenGetVacationPay_thenThrowException() throws Exception {

        mvc.perform(get("/calculate")
                   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                   .param("salary", "0")
                   .param("days", "7")
                   .characterEncoding(StandardCharsets.UTF_8))
           .andExpect(status().is(400));

    }

}
