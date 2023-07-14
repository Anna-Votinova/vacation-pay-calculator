package neoflex.vacationpaycalculator.service;

import jakarta.validation.ValidationException;
import neoflex.vacationpaycalculator.util.DateValidation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class VacationPayService {

    private static final int YEAR = 2023;

    private final List<LocalDate> holidays = List.of(
            LocalDate.of(YEAR,1, 1),
            LocalDate.of(YEAR,1, 2),
            LocalDate.of(YEAR,1, 3),
            LocalDate.of(YEAR,1, 4),
            LocalDate.of(YEAR,1, 5),
            LocalDate.of(YEAR,1, 6),
            LocalDate.of(YEAR,1, 7),
            LocalDate.of(YEAR,1, 8),
            LocalDate.of(YEAR,2, 23),
            LocalDate.of(YEAR,3, 8),
            LocalDate.of(YEAR,5, 1),
            LocalDate.of(YEAR,5, 9),
            LocalDate.of(YEAR,6, 12),
            LocalDate.of(YEAR,11, 4)
    );

    public BigDecimal calculate(BigDecimal salary, int days, LocalDate firstDayOfVacation, LocalDate lastDayOfVacation) {

        if (firstDayOfVacation != null && lastDayOfVacation != null) {
            DateValidation.checkDate(firstDayOfVacation, lastDayOfVacation);
            days = (int) ChronoUnit.DAYS.between(firstDayOfVacation, lastDayOfVacation) + 1;
            days -= calculateHolidaysNumber(days, firstDayOfVacation);
        }

        if (days <= 0) {
            throw new ValidationException("Должно быть известно количество дней отпуска или даты отпуска");
        }

        BigDecimal averageSalary = salary.divide(BigDecimal.valueOf(29.3), 2, RoundingMode.HALF_UP);

        return averageSalary.multiply(BigDecimal.valueOf(days));
    }

    private int calculateHolidaysNumber(int days, LocalDate from) {
        int holidaysNumber = 0;
        LocalDate startWith = from;

        for (int i = 0; i < days; i++) {
            if (holidays.contains(startWith)) {
                holidaysNumber++;
            }
            startWith = startWith.plusDays(1L);
        }

        return holidaysNumber;
    }

}
