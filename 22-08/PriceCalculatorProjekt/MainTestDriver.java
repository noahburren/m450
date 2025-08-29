public class MainTestDriver {

    public static boolean test_calculate_price() {
        boolean test_ok = true;

        // Beispiel 1
        double price1 = PriceCalculator.calculatePrice(20000, 1000, 3000, 0, 0);
        if (price1 != 24000.0) test_ok = false;

        // Beispiel 2
        double price2 = PriceCalculator.calculatePrice(10000, 500, 2000, 3, 10);
        System.out.println("Berechneter Preis: " + price2);

        return test_ok;
    }

    public static void main(String[] args) {
        boolean ok = test_calculate_price();
        if (ok) {
            System.out.println("Alle Tests erfolgreich!");
        } else {
            System.out.println("Mindestens ein Test ist fehlgeschlagen!");
        }
    }
}
