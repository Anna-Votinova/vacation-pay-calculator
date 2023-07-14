package neoflex.vacationpaycalculator.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import neoflex.vacationpaycalculator.service.VacationPayService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@RequestMapping
@Validated
public class VacationPayCalculatorController {

    private final VacationPayService service;

    private static final String DATE_FORMAT = "yyyy-MM-dd";


    @GetMapping("/calculate")
    public BigDecimal getVacationPay(@Positive @RequestParam BigDecimal salary,
                                 @RequestParam (defaultValue = "0", required = false) int days,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
                                     @RequestParam (required = false) LocalDate from,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
                                     @RequestParam (required = false) LocalDate to) {
        return service.calculate(salary, days, from, to);
    }
}