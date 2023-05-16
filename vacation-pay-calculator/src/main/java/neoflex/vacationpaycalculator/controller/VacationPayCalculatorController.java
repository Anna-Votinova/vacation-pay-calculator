package neoflex.vacationpaycalculator.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import neoflex.vacationpaycalculator.service.PlainVacationPayService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping
@Validated
public class VacationPayCalculatorController {

    private final PlainVacationPayService service;


    @GetMapping("/calculate")
    public double getVacationPay(@Positive @RequestParam double salary,
                                 @Positive @RequestParam int days) {
        return service.calculate(salary, days);
    }
}
