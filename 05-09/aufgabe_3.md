# Banken‑Simulation – Übersicht

> Java/Maven-Projekt. Kernklassen: `Bank`, `Account` (+ Unterklassen), `Booking`, `BankUtils`, Comparatoren. Geldbeträge als `long` in Milli‑Rappen gespeichert.

---

## Klassen & Rollen

* **Bank**: verwaltet Konten (`TreeMap`), erstellt Konten, Ein-/Auszahlungen, Abfragen & Kontoauszüge.
* **Account** (abstrakt): Felder `id`, `balance`, `bookings`. Methoden: `deposit`, `withdraw`, `print`, `canTransact` (keine Rückdatierung).
* **Unterklassen**:

  * `SavingsAccount`: 1 % Bonus auf Einzahlungen.
  * `PromoYouthSavingsAccount`: ebenfalls 1 % Bonus.
  * `SalaryAccount`: erlaubt Überziehung bis `creditLimit`.
* **Booking**: repräsentiert Buchung (Datum, Betrag), druckt Buchungszeile.
* **BankUtils**: Formatierung von Beträgen/Datum.
* **Comparatoren**: vergleichen Konten nach Saldo (normal/invers).

---

## Abläufe

* **Einzahlung**: `Bank.deposit` → Konto prüfen → ggf. Bonus → `Booking(+amount)` → `balance` hoch.
* **Abhebung**: `Bank.withdraw` → Regeln prüfen (Saldo, Kreditlimite) → `Booking(-amount)` → `balance` runter.
* **Print**: Konto druckt alle Buchungen mit laufendem Saldo, formatiert durch `BankUtils`.

---

## Regeln

* Chronologie: keine rückdatierten Transaktionen.
* Beträge immer `long` (vermeidet Rundungsfehler).
* Sparkonten erhalten Bonus, Lohnkonto darf ins Minus.

---

## Beispiel

```java
Bank bank = new Bank();
String id = bank.createSavingsAccount();
bank.deposit(id, 20000, 100_00_000); // 100 CHF + Bonus
bank.withdraw(id, 20001, 50_00_000);
long saldo = bank.getBalance(id);
bank.print(id);
```

---

## Hinweise

* Comparatoren-Richtung prüfen.
* Bonus-Logik: evtl. nur bei Jugend-Sparkonto.
* Datum als `int` (Banktage), ggf. zu `LocalDate` migrieren.

---

## Build & Test

* Maven Standardlayout.
* Tests mit JUnit 5 (`mvn test`).
