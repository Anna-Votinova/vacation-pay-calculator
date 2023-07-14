package neoflex.vacationpaycalculator;

import jakarta.validation.ValidationException;
import neoflex.vacationpaycalculator.service.VacationPayService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VacationPayServiceUnitTest {

    private final BigDecimal salary = new BigDecimal("40000");

    private final VacationPayService service = new VacationPayService();

    @DisplayName("JUnit test for calculate method (positive scenario)")
    @Test
    void shouldReturnVacationPay_whenGivenCorrectData() {
        int days = 7;

        BigDecimal givenSalary = service.calculate(salary, days, null, null);

        assertEquals("9556.33", givenSalary.toString());
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    void shouldThrowException_whenGivenNegativeDays() {
        int days = -7;

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, null, null));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    void shouldThrowException_whenGivenZeroDays() {
        int days = 0;

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, null, null));
    }

    @DisplayName("JUnit test for calculate method (positive scenario)")
    @Test
    void shouldReturnVacationPay_whenGivenCorrectAddAndMainData() {
        int days = 14;

        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 1, 14);

        BigDecimal givenSalary = service.calculate(salary, days, from, to);

        assertEquals("8191.14", givenSalary.toString());
    }

    @DisplayName("JUnit test for calculate method (positive scenario)")
    @Test
    void shouldReturnVacationPay_whenGivenCorrectAddDataAnNegativeDays() {
        int days = -14;
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 1, 14);

        BigDecimal givenSalary = service.calculate(salary, days, from, to);

        assertEquals("8191.14", givenSalary.toString());
    }

    @DisplayName("JUnit test for calculate method (positive scenario)")
    @Test
    void shouldReturnVacationPay_whenGivenCorrectAddDataAnZeroDay() {
        int days = 0;
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 1, 14);

        BigDecimal givenSalary = service.calculate(salary, days, from, to);

        assertEquals("8191.14", givenSalary.toString());
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    void shouldThrowException_whenGivenWrongFromAndTo() {
        int days = 14;
        LocalDate from = LocalDate.of(2023, 1, 14);
        LocalDate to = LocalDate.of(2023, 1, 1);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, from, to));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    void shouldThrowException_whenGivenPreviousYear() {
        int days = 14;
        LocalDate from = LocalDate.of(2022, 1, 1);
        LocalDate to = LocalDate.of(2023, 1, 14);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, from, to));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    void shouldThrowException_whenGivenNextYear() {
        int days = 14;
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2024, 1, 14);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, from, to));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    void shouldThrowException_whenGivenToBeforeFrom() {
        int days = 14;
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2022, 1, 14);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, from, to));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    void shouldThrowException_whenGivenDaysZeroAndNullTo() {
        int days = 0;
        LocalDate from = LocalDate.of(2023, 1, 1);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, from, null));
    }

    @DisplayName("JUnit test for calculate method (negative scenario)")
    @Test
    void shouldThrowException_whenGivenDaysZeroAndNullFrom() {
        int days = 0;
        LocalDate to = LocalDate.of(2023, 1, 14);

        assertThrows(ValidationException.class,
                () -> service.calculate(salary, days, null, to));
    }

}
