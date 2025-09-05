package ch.schule.bank.junit5;

import ch.schule.SalaryAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryAccountTests {

    @Test
    void overdraft_allowed_down_to_credit_limit_but_not_below() {
        SalaryAccount acc = new SalaryAccount("L-1", -50_00_000); // Limit: -50 CHF
        assertTrue(acc.deposit(40_000, 10_00_000));     // +10
        assertTrue(acc.withdraw(40_001, 55_00_000));    // → -45 (ok)
        assertEquals(-45_00_000, acc.getBalance());
        assertFalse(acc.withdraw(40_002, 10_00_000));   // → -55 (unter Limit)
        assertEquals(-45_00_000, acc.getBalance());
    }
}
