package ch.schule.bank.junit5;

import ch.schule.PromoYouthSavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromoYouthSavingsAccountTests {

    @Test
    void deposit_adds_1_percent_bonus() {
        PromoYouthSavingsAccount acc = new PromoYouthSavingsAccount("Y-1");
        assertTrue(acc.deposit(20_000, 200_00_000)); // 200 CHF
        // +1% Bonus → 202 CHF
        assertEquals(202_00_000, acc.getBalance());
    }
}
