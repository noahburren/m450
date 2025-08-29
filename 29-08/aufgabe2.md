## Übung 2 – Autovermietung: funktionale Black-Box-Tests

> Angenommene Kernfunktionen: Standort/Datum wählen, Fahrzeugklasse wählen, Extras, Bezahlung, Buchungsbestätigung.

| ID | Beschreibung | Erwartetes Resultat | Effektives Resultat | Status | Mögliche Ursache |
|---:|--------------|---------------------|---------------------|--------|------------------|
| R1 | **Suche mit gültigen Daten**: Standort „Zürich HB“, Abholung 10.09. 10:00, Rückgabe 12.09. 10:00 | Liste verfügbarer Fahrzeuge mit Preisen wird angezeigt | _…_ | _OK/Fehler_ | _…_ |
| R2 | **Ungültiges Datum**: Rückgabe vor Abholung | Fehlermeldung „Rückgabedatum muss nach Abholdatum liegen“, keine Suche | _…_ | _OK/Fehler_ | Client-Validierung fehlt / Server-Validierung fehlt |
| R3 | **Filter Fahrzeugklasse**: nur „Kompakt“ | Ergebnisliste zeigt nur Kompaktklasse | _…_ | _OK/Fehler_ | Falsche Filterlogik |
| R4 | **Checkout Zahlung erfolgreich**: gültige Kreditkarte | Buchung wird bestätigt, Buchungsnummer per E-Mail | _…_ | _OK/Fehler_ | Payment-Gateway/Timeout |
| R5 | **Extras & Gesamtpreis**: Kindersitz + Zusatzfahrer | Gesamtpreis enthält korrekte Zuschläge und Steuern | _…_ | _OK/Fehler_ | Preisberechnungs-Fehler/Steuerregel |

> Ergänzende Ideen: Login/Registrierung, Gutschein-Code, Stornierung, unterschiedliche Abhol-/Rückgabeorte (One-Way-Fee), Kilometerlimit, Kaution.

---