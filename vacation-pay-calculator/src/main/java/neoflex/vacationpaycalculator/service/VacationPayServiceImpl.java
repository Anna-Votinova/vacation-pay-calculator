package neoflex.vacationpaycalculator.service;

import jakarta.validation.ValidationException;
import neoflex.vacationpaycalculator.util.DateValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/*
* При сложном расчете отпускных используем классическую схему - из отпускных дней вычитается число праздничных дней,
* так как они не оплачиваются государством. Их всего 14, они сохранены в списке.
* Далее мы делим среднюю зарплату на среднее количество дней в месяце. В разных источниках это 29.3 дня.
* И умножаем на количество отпускных за вычетом праздников. Выходные при этом в классической схеме не принимаются
* в расчет. Этот вариант программы рассчитывает, что мы производим расчет только за 2023 год.
* Также пока нет ограничений на количество дней отпуска.
* */

@Service
public class VacationPayServiceImpl implements VacationPayService {

    private static final int YEAR = 2023;

    private final List<LocalDate> holidays = List.of(
            convert(1, 1),
            convert(1, 2),
            convert(1, 3),
            convert(1, 4),
            convert(1, 5),
            convert(1, 6),
            convert(1, 7),
            convert(1, 8),
            convert(2, 23),
            convert(3, 8),
            convert(5, 1),
            convert(5, 9),
            convert(6, 12),
            convert(11, 4)
    );


    @Override
    public double calculate(double salary, int days, LocalDate firstDayOfVacation, LocalDate lastDayOfVacation) {

        if (firstDayOfVacation != null && lastDayOfVacation != null) {
            DateValidation.checkDate(firstDayOfVacation, lastDayOfVacation);
            days = (int) DAYS.between(firstDayOfVacation, lastDayOfVacation) + 1;
            days -= calculateWorkingDay(days, firstDayOfVacation);
        }

        if (days <= 0) {
            throw new ValidationException("Должно быть известно количество дней отпуска или даты отпуска");
        }

        double preResult = salary / 29.3 * days;

        double scale = Math.pow(10, 2);

        return Math.ceil(preResult * scale) / scale;
    }



    private LocalDate convert(int month, int day) {
        return LocalDate.of(YEAR, month, day);
    }

    private int calculateWorkingDay(int days, LocalDate from) {
        int holidaysNumber = 0;
        LocalDate startWith = from;

        for(int i = 0; i < days; i++) {
            if (holidays.contains(startWith)) {
                holidaysNumber++;
            }
            startWith = startWith.plusDays(1L);
        }

        return holidaysNumber;
    }

}
