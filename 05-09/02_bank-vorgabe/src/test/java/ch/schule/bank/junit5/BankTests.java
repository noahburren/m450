package ch.schule.bank.junit5;

import ch.schule.*;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class BankTests {

    @Test
    void create_and_transact_savings_via_bank() {
        Bank bank = new Bank();
        String id = bank.createSavingsAccount();  // "S-<nr>"
        assertTrue(id.startsWith("S-"));

        assertTrue(bank.deposit(id, 10_000, 100_00_000)); // 100 CHF
        // SavingsAccount hat KEIN Bonus – PromoYouth hat Bonus
        assertEquals(100_00_000, bank.getBalance(id));

        assertTrue(bank.withdraw(id, 10_001, 20_00_000));
        assertEquals(80_00_000, bank.getBalance(id));
    }

    @Test
    void create_promo_youth_gets_bonus_on_deposit() {
        Bank bank = new Bank();
        String id = bank.createPromoYouthSavingsAccount(); // "Y-<nr>"
        assertTrue(bank.deposit(id, 12_000, 100_00_000));
        // +1% → 101 CHF
        assertEquals(101_00_000, bank.getBalance(id));
    }

    @Test
    void unknown_account_returns_false_or_zero() {
        Bank bank = new Bank();
        assertFalse(bank.deposit("Z-404", 10_000, 1_00_000));
        assertFalse(bank.withdraw("Z-404", 10_000, 1_00_000));
        assertEquals(0, bank.getBalance("Z-404"));
    }

    @Test
    void print_account_statement_and_top_bottom_5() {
        Bank bank = new Bank();
        // Erzeuge einige Konten/Saldos
        String a = bank.createSavingsAccount();
        String b = bank.createSavingsAccount();
        String c = bank.createSavingsAccount();
        bank.deposit(a, 10_000, 10_00_000);
        bank.deposit(b, 10_000, 20_00_000);
        bank.deposit(c, 10_000, 30_00_000);

        // print(id)
        var baos = new java.io.ByteArrayOutputStream();
        var old = System.out;
        System.setOut(new java.io.PrintStream(baos));
        try {
            bank.print(a);
            bank.printTop5();
            bank.printBottom5();
        } finally {
            System.setOut(old);
        }
        String out = baos.toString();
        assertTrue(out.contains(a));
        // mind. eine ID-Zeile mit "ID: Saldo"
        assertTrue(Pattern.compile("\\w-\\d+:\\s+-?\\d+").matcher(out).find());
    }
}
