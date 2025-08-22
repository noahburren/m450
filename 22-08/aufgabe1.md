# Aufgabe 1 – Testarten in der Informatik

## 1. Unittests (Modultests)
- **Zweck:** Überprüfen einzelne Programmteile (z. B. eine Funktion oder Methode), ob sie korrekt arbeiten.
- **Praxisbeispiel:** In Java wird mit JUnit getestet, ob eine Funktion die richtige Summe zurückgibt.
- **Durchführung:** Entwickler schreiben automatisierte Tests, die beim Build oder in der IDE ausgeführt werden.

## 2. Integrationstests
- **Zweck:** Testen das Zusammenspiel mehrerer Komponenten oder Module.
- **Praxisbeispiel:** Eine Webanwendung: Test, ob Daten korrekt von der Benutzeroberfläche bis zur Datenbank gelangen.
- **Durchführung:** Automatisierte Tests laufen nach dem Unit-Test, oft in einer Testumgebung mit Datenbank und API.

## 3. Systemtests / End-to-End-Tests
- **Zweck:** Überprüfen das Gesamtsystem aus Sicht des Benutzers.
- **Praxisbeispiel:** Ein Test prüft, ob ein Benutzer sich erfolgreich registrieren und einloggen kann.
- **Durchführung:** Oft mit Tools wie Selenium oder Cypress, die Klicks und Eingaben im Browser simulieren.  
