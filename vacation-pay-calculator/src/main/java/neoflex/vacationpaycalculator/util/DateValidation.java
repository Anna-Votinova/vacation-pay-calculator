package neoflex.vacationpaycalculator.util;

import jakarta.validation.ValidationException;

import java.time.LocalDate;

final public class DateValidation {

    public static void checkDate(LocalDate from, LocalDate to) {
        if (from.isAfter(to) || to.isAfter(LocalDate.of(2023, 12,31))
                ||  from.isBefore(LocalDate.of(2023,1, 1))) {
            throw new ValidationException("Введены некорректные даты отпуска");
        }
    }

}
