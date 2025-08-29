public class PriceCalculator {

    public static double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount;

        // Fehler 1: Reihenfolge war falsch (>=3 kam vor >=5)
        if (extras >= 5) {                 // NEW: zuerst >=5 prüfen
            addon_discount = 15.0;
        } else if (extras >= 3) {          // NEW: dann >=3
            addon_discount = 10.0;
        } else {
            addon_discount = 0.0;
        }

        // Fehler 2: Händler-Rabatt wurde auf Zubehör angewendet
        // Lösung: Rabatte getrennt berechnen
        double basePart   = baseprice  * (1 - discount / 100.0);      // NEW: Rabatt nur auf Grundpreis
        double extrasPart = extraprice * (1 - addon_discount / 100.0); // NEW: Rabatt nur auf Zubehör

        return basePart + specialprice + extrasPart; // NEW: korrekt zusammensetzen
    }
}
