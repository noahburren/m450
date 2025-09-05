package ch.schule.bank.junit5;

import ch.schule.SavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTests {

    @Test
    void withdraw_fails_when_balance_insufficient() {
        SavingsAccount acc = new SavingsAccount("S-10");
        assertTrue(acc.deposit(10_000, 20_00_000));
        assertFalse(acc.withdraw(10_001, 25_00_000)); // zu wenig Saldo
        assertTrue(acc.withdraw(10_002, 20_00_000));  // exakt Saldo
        assertEquals(0, acc.getBalance());
    }
}
