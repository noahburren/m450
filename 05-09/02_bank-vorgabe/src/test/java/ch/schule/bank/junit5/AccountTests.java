package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.SavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTests {

    private SavingsAccount newSavings(String id) {
        return new SavingsAccount(id);
    }

    @Test
    void deposit_increases_balance() {
        Account acc = newSavings("S-1");
        assertTrue(acc.deposit(10_000, 100_00_000)); // 100 CHF
        assertEquals(100_00_000, acc.getBalance());
    }

    @Test
    void withdraw_decreases_balance_when_allowed() {
        Account acc = newSavings("S-2");
        assertTrue(acc.deposit(10_000, 100_00_000));
        assertTrue(acc.withdraw(10_001, 40_00_000));
        assertEquals(60_00_000, acc.getBalance());
    }

    @Test
    void chronological_guard_blocks_backdating_but_allows_same_day() {
        Account acc = newSavings("S-3");
        assertTrue(acc.deposit(20_000, 10_00_000));
        assertFalse(acc.withdraw(19_999, 1_00_000)); // rückdatiert -> abgelehnt
        assertTrue(acc.withdraw(20_000, 1_00_000));  // gleicher Tag -> erlaubt
        assertEquals(9_00_000, acc.getBalance());
    }

    @Test
    void negative_amount_is_rejected_zero_is_allowed() {
        Account acc = newSavings("S-4");
        // 0 ist gemäss Implementierung erlaubt
        assertTrue(acc.deposit(10_000, 0));
        assertTrue(acc.withdraw(10_001, 0));
        // negative Beträge sind verboten
        assertFalse(acc.deposit(10_002, -1));
        assertFalse(acc.withdraw(10_003, -1));
        // Saldo bleibt 0
        assertEquals(0, acc.getBalance());
    }

    @Test
    void print_outputs_header_and_lines() {
        Account acc = newSavings("S-5");
        acc.deposit(30_000, 50_00_000);
        acc.withdraw(30_001, 10_00_000);

        var baos = new java.io.ByteArrayOutputStream();
        var old = System.out;
        System.setOut(new java.io.PrintStream(baos));
        try {
            acc.print();
        } finally {
            System.setOut(old);
        }
        String out = baos.toString();
        assertTrue(out.contains("Kontoauszug 'S-5'"));
        assertTrue(out.contains("Datum"));
        assertTrue(out.contains("Saldo"));
    }
}
