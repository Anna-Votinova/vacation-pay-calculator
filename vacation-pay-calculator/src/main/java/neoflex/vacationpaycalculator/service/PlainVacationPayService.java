package neoflex.vacationpaycalculator.service;

import org.springframework.stereotype.Service;

@Service
public class PlainVacationPayService implements VacationPayService {

    @Override
    public double calculate(double salary, int days) {
        double preResult = salary / 29.3 * days;

        double scale = Math.pow(10, 2);

        return Math.ceil(preResult * scale) / scale;
    }
}
