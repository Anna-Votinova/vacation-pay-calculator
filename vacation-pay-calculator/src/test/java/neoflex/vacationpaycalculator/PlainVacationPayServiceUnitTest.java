package neoflex.vacationpaycalculator;

import neoflex.vacationpaycalculator.service.PlainVacationPayService;
import neoflex.vacationpaycalculator.service.VacationPayService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainVacationPayServiceUnitTest {

private final VacationPayService service = new PlainVacationPayService();

    @DisplayName("JUnit test for calculate method (positive scenario)")
    @Test
    public void shouldReturnVacationPay_whenGivesCorrectData() {
        double salary = 40000;
        int days = 7;

        double preExpectedVacationPay = salary / 30 * days;

        double scale = Math.pow(10, 2);

        double expectedVacationPay = Math.ceil(preExpectedVacationPay * scale) / scale;

        double givenSalary = service.calculate(salary, days);

        assertEquals(expectedVacationPay, givenSalary);
    }

}
