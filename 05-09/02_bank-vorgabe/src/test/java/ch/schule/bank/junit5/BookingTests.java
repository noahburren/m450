package ch.schule.bank.junit5;

import ch.schule.Booking;
import ch.schule.BankUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingTests {

    @Test
    void booking_holds_date_and_amount_and_prints_line() {
        Booking b = new Booking(25_000, 12_34_000);
        assertEquals(25_000, b.getDate());
        assertEquals(12_34_000, b.getAmount());

        var baos = new java.io.ByteArrayOutputStream();
        var old = System.out;
        System.setOut(new java.io.PrintStream(baos));
        try {
            b.print(99_99_000); // aktueller Saldo danach
        } finally {
            System.setOut(old);
        }
        String out = baos.toString();
        assertTrue(out.contains(BankUtils.formatBankDate(25_000)));
        assertTrue(out.contains(BankUtils.formatAmount(12_34_000)));
        assertFalse(out.contains(BankUtils.formatAmount(99_99_000)));
    }
}
