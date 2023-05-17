package neoflex.vacationpaycalculator.service;

import java.time.LocalDate;

public interface VacationPayService {

    double calculate(double salary, int days, LocalDate firstDayOfVacation, LocalDate lastDayOfVacation);

}
