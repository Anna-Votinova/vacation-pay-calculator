package neoflex.vacationpaycalculator.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Расчет отпускных", description = "Позволяет расчитать отпускные")
public class VacationPayCalculatorController {

    private final VacationPayService service;

    private static final String DATE_FORMAT = "yyyy-MM-dd";


    @GetMapping("/calculate")
    @Operation(summary = "Получение суммы отпускных",
               description = "Позволяет получить сумму отпускных на основе введенных данных: " +
                       "если не будут введены точные даты начала и окончания отпуска, " +
                       "калькулятор посчитает отпускные по количеству введенных дней отпуска")
    public BigDecimal getVacationPay(
            @RequestParam @Positive @Parameter(description = "Средняя зарплата за месяц") BigDecimal salary,
            @RequestParam(defaultValue = "0", required = false) @Parameter(description = "Количество дней отпуска")
            int days,
            @RequestParam(required = false) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
            @Parameter(description = "Дата начала отпуска") LocalDate from,
            @RequestParam(required = false) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
            @Parameter(description = "Дата окончания отпуска") LocalDate to) {
        return service.calculate(salary, days, from, to);
    }
}