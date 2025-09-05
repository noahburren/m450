package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator – Grundoperationen")
class CalculatorTest {

    private Calculator calculator;
    private static final double EPS = 1e-9; // Double-Vergleich mit Toleranz

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0, 0, 0",
            "1.5, 2.5, 4.0",
            "-3, 7, 4",
            "-2, -8, -10",
            "1e9, 1e9, 2e9"
    })
    void add(double a, double b, double expected) {
        assertEquals(expected, calculator.add(a, b), EPS);
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
            "5, 3, 2",
            "3, 5, -2",
            "-10, -5, -5",
            "1.25, 0.25, 1.0",
            "1e9, 1, 999999999"
    })
    void subtract(double a, double b, double expected) {
        assertEquals(expected, calculator.subtract(a, b), EPS);
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "0, 99, 0",
            "-4, 3, -12",
            "-4, -3, 12",
            "2.5, 4, 10",
            "1e6, 1e3, 1e9"
    })
    void multiply(double a, double b, double expected) {
        assertEquals(expected, calculator.multiply(a, b), EPS);
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "10, 2, 5",
            "-9, 3, -3",
            "7.5, 2.5, 3",
            "-8, -2, 4"
    })
    void divide(double a, double b, double expected) {
        assertEquals(expected, calculator.divide(a, b), EPS);
    }

    @Test
    @DisplayName("Division durch 0 wirft ArithmeticException")
    void divideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(1.0, 0.0));
        assertThrows(ArithmeticException.class, () -> calculator.divide(-123.45, -0.0));
    }
}
