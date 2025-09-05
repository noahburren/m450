package org.example;

/**
 * Simpler Taschenrechner mit Grundoperationen.
 */
public final class Calculator {

    /** @return Summe von a und b */
    public double add(double a, double b) {
        return a + b;
    }

    /** @return Differenz a - b */
    public double subtract(double a, double b) {
        return a - b;
    }

    /** @return Produkt von a und b */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * @return Quotient a / b
     * @throws ArithmeticException wenn b == 0 (explizites Fehlerverhalten statt Infinity/NaN)
     */
    public double divide(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }
}
