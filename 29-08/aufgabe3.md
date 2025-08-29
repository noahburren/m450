## Übung 3 – Bank-Software (GSON, OKHTTP)

**Ziel:** Testfälle identifizieren (Black-Box), mögliche White-Box-Ziele, Code-Verbesserungen.

---

### 1) Mögliche Black-Box-Testfälle (aus Benutzersicht)

| ID  | Feature             | Eingabe                                | Erwartetes Ergebnis                                |
|----:|---------------------|----------------------------------------|---------------------------------------------------|
| BB1 | Kontoerstellung     | `Bank.createAccount("Max", 1000)`       | Konto für „Max“ wird angelegt, Startsaldo = 1000  |
| BB2 | Einzahlung          | `account.deposit(500)` bei Saldo 1000   | Neuer Saldo = 1500                                |
| BB3 | Auszahlung ok       | `account.withdraw(200)` bei Saldo 1000  | Neuer Saldo = 800                                 |
| BB4 | Auszahlung zu hoch  | `account.withdraw(2000)` bei Saldo 1000 | Fehlermeldung/Exception, Saldo bleibt 1000        |
| BB5 | Wechselkurs-Abfrage | `ExchangeRateOkhttp.getRate("USD","CHF")` | Gültiger Kurswert wird zurückgegeben              |
| BB6 | Wechselkurs-Fehler  | `ExchangeRateOkhttp.getRate("XXX","CHF")` | Fehlermeldung oder Exception                      |
| BB7 | Mehrere Konten      | 2× `createAccount(...)`                 | `Bank.getAccounts()` liefert 2 Konten zurück      |
| BB8 | Counter             | `Counter.increment()` mehrfach          | Zählerwert erhöht sich erwartungsgemäß            |
| BB9 | Thread-Sicherheit   | Counter in mehreren Threads             | Endwert = Anzahl Inkremente (kein Race Condition) |
| BB10| Main-Ausführung     | Start von `Main.java`                   | Programm läuft ohne Absturz, Menü erscheint       |

---

### 2) Kandidaten für White-Box-Tests (Methodenebene)

- **`Account`**
  - `deposit(amount)` → Pfade: `amount > 0` / `amount <= 0`
  - `withdraw(amount)` → Pfade: `amount <= balance` / `amount > balance`

- **`Bank`**
  - `createAccount(name, balance)` → Name leer? negativer Startbetrag?
  - `getAccounts()` → liefert Liste aller Accounts zurück

- **`Counter`**
  - `increment()` → Race-Condition prüfen, Synchronisation sicherstellen

- **`ExchangeRateOkhttp`**
  - `getRate(base, target)` → normaler Erfolg / ungültige Währung / Netzwerkfehler

- **`Main`**
  - Menüführung und Benutzerinteraktionen (z. B. Konto wählen, Aktion ausführen)

---

### 3) Verbesserungen / Best Practices

- **Datentypen:** Geldbeträge mit `BigDecimal` statt `double`, um Rundungsfehler zu vermeiden.  
- **Validierung:**  
  - `deposit`/`withdraw` sollen negative Beträge strikt ablehnen.  
  - `createAccount` soll leere Namen/negative Beträge abweisen.  
- **Fehlerbehandlung:** Exceptions mit klaren Messages statt stillschweigend nichts tun.  
- **Thread-Sicherheit:**  
  - `Counter` besser mit `AtomicInteger`.  
  - Falls mehrere Threads auf `Bank` zugreifen: Synchronisation nötig.  
- **Architektur:** `Main` (UI) strikt von Logik (`Bank`, `Account`) trennen.  
- **Externe Calls (`ExchangeRateOkhttp`)**: Timeout-Handling, Logging, Retry-Strategie.  

---

### 4) Markdown-Tabelle (Kombination Black-/White-Box)

| ID  | Typ       | Beschreibung                     | Eingabe / Bedingung              | Erwartet                     | Notizen            |
|----:|----------|---------------------------------|----------------------------------|-------------------------------|--------------------|
| BB2 | Black-Box | Einzahlung                       | deposit(500) bei Saldo 1000      | Saldo 1500                    | Positivfall        |
| BB4 | Black-Box | Auszahlung > Guthaben            | withdraw(2000) bei Saldo 1000    | Exception/Fehler, Saldo=1000  | Negativfall        |
| BB5 | Black-Box | Wechselkurs-Abfrage              | USD→CHF                          | Kurswert > 0                  | API-Verfügbarkeit  |
| WB1 | White-Box | `deposit` Validierung            | amount <= 0                      | Exception                     | Branch-Coverage    |
| WB2 | White-Box | `withdraw` Branches              | amount <= balance / > balance    | korrekt abziehen / Fehler     | Branch-Coverage    |
| WB3 | White-Box | `getRate` Fehlerpfade            | ungültige Währung / Timeout      | Exception/Fehlerhandling      | OkHttp-Mocking     |
| WB4 | White-Box | `Counter.increment` Thread-Test  | 10 Threads je 100 Inkremente     | Endwert = 1000                | Race Condition Test|

---

### README-Hinweis
- **Übung 1 & 2:** Markdown-Dateien öffnen.  
- **Übung 3:** Projekt lokal starten:  
  - **Maven**: `mvn clean install && mvn exec:java`  
  - **ohne Maven**: JARs (`gson`, `okhttp`) ins Projekt einbinden, `Main.java` starten.  
- Tests können als einfache `MainTest`-Klasse oder mit JUnit geschrieben werden.
