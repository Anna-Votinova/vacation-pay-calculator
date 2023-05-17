package neoflex.vacationpaycalculator;

import jakarta.validation.ValidationException;
import neoflex.vacationpaycalculator.service.VacationPayServiceImpl;
import neoflex.vacationpaycalculator.service.VacationPayService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VacationPayServiceUnitTest {

    private final double salary = 40000;
    private int days = 7;


    private final VacationPayService service = new VacationPayServiceImpl();

    @DisplayName("JUnit test for calculate method (positive scenario)")
    @Test
    public void shouldReturnVacationPay_whenGivenCorrectData() {

        double preExpectedVacationPay = salary / 29.3 * days;

        double scale = Math.pow(10, 2);

        double expectedVacationPay = Math.ceil(preExpectedVacationPay * scale) / scale;

        double givenSalary = service.calculate(salary, days, null, null);

        assertEquals(expectedVacationPay, givenSalary);
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    public void shouldThrowException_whenGivenNegativeDays() {
        days = -7;

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, null, null));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    public void shouldThrowException_whenGivenZeroDays() {
        days = 0;

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, null, null));
    }

    @DisplayName("JUnit test for calculate method (positive scenario)")
    @Test
    public void shouldReturnVacationPay_whenGivenCorrectAddAndMainData() {
        days = 14;

        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 1, 14);

        double expectedVacationPay = 8191.13;

        double givenSalary = service.calculate(salary, days, from, to);

        assertEquals(expectedVacationPay, givenSalary);
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    public void shouldReturnVacationPay_whenGivenCorrectAddDataAndWrongDays() {

        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 1, 14);

        double expectedVacationPay = 8191.13;

        double givenSalary = service.calculate(salary, days, from, to);

        assertEquals(expectedVacationPay, givenSalary);
    }

    @DisplayName("JUnit test for calculate method (positive scenario)")
    @Test
    public void shouldReturnVacationPay_whenGivenCorrectAddDataAnNegativeDays() {
        days = -14;
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 1, 14);

        double expectedVacationPay = 8191.13;

        double givenSalary = service.calculate(salary, days, from, to);

        assertEquals(expectedVacationPay, givenSalary);
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    public void shouldReturnVacationPay_whenGivenCorrectAddDataAnZeroDay() {
        days = 0;
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 1, 14);

        double expectedVacationPay = 8191.13;

        double givenSalary = service.calculate(salary, days, from, to);

        assertEquals(expectedVacationPay, givenSalary);
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    public void shouldThrowException_whenGivenWrongFromAndTo() {
        days = 14;
        LocalDate from = LocalDate.of(2023, 1, 14);
        LocalDate to = LocalDate.of(2023, 1, 1);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, from, to));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    public void shouldThrowException_whenGivenPreviousYear() {
        days = 14;
        LocalDate from = LocalDate.of(2022, 1, 1);
        LocalDate to = LocalDate.of(2023, 1, 14);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, from, to));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    public void shouldThrowException_whenGivenNextYear() {
        days = 14;
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2024, 1, 14);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, from, to));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    public void shouldThrowException_whenGivenToBeforeFrom() {
        days = 14;
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2022, 1, 14);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, from, to));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    public void shouldThrowException_whenGivenDaysZeroAndNullTo() {
        days = 0;
        LocalDate from = LocalDate.of(2023, 1, 1);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, from, null));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    public void shouldThrowException_whenGivenDaysZeroAndNullFrom() {
        days = 0;
        LocalDate to = LocalDate.of(2023, 1, 14);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, null, to));
    }

}
