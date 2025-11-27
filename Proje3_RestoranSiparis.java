/**
 * Ad Soyad: Ceren Çam
 * Öğrenci No: 250541073
 * Proje: Akıllı Restoran Sipariş Sistemi
 * Tarih: 27.11.2025
 */

import java.util.Scanner;

public class Proje3_RestoranSiparis {

    // Menü fiyatlarını döndüren metotlar (switch-case zorunlu)
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85.0; // Izgara Tavuk
            case 2: return 120.0; // Adana Kebap
            case 3: return 110.0; // Levrek
            case 4: return 65.0; // Mantı
            default: return 0.0;
        }
    }

    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25.0; // Çorba
            case 2: return 45.0; // Humus
            case 3: return 55.0; // Sigara Böreği
            default: return 0.0;
        }
    }

    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15.0; // Kola
            case 2: return 12.0; // Ayran
            case 3: return 35.0; // Meyve Suyu
            case 4: return 25.0; // Limonata
            default: return 0.0;
        }
    }

    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65.0; // Künefe
            case 2: return 55.0; // Baklava
            case 3: return 35.0; // Sütlaç
            default: return 0.0;
        }
    }

    // isComboOrder(anaVar, icecekVar, tatliVar) → boolean
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // isHappyHour(saat) → boolean (14:00-17:00)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat < 17;
    }

    // calculateDiscount(tutar, combo, ogrenci, saat) → double (toplam indirim miktarı)
    // İndirim mantığı (ödev örneğine göre sıralama):
    // 1) Combo %15 (tüm ara toplam üzerinden)
    // 2) Happy Hour: içeceklerde %20 (sadece içeceğe göre hesaplanıp düşülür)
    // 3) Öğrenci (hafta içi): %10 (ara toplam üzerinden kalan tutardan)
    // 4) 200₺ üzeri ek %10 indirimi (ödevte belirtildiği gibi ayrıca uygulansın)
    public static double calculateDiscount(double araToplam, boolean combo, boolean ogrenci, int saat, int gun, double drinkPriceAfter) {
        double toplamIndirim = 0.0;
        double kalan = araToplam;

        // Combo
        if (combo) {
            double comboInd = araToplam * 0.15;
            toplamIndirim += comboInd;
            kalan -= comboInd;
        }

        // Happy hour: içeceklerde %20 -> bu indirim zaten drinkPriceAfter parametresi ile verilecek
        // fakat sonuca yansıtmak için çıkartıyoruz:
        double happyInd = 0.0;
        if (isHappyHour(saat)) {
            happyInd = drinkPriceAfter * 0.20;
            toplamIndirim += happyInd;
            kalan -= happyInd;
        }

        // Öğrenci (hafta içi): gun 1..5
        if (ogrenci && (gun >= 1 && gun <= 5)) {
            double ogrInd = kalan * 0.10;
            toplamIndirim += ogrInd;
            kalan -= ogrInd;
        }

        // 200₺ üzeri: %10 ekstra (öğretmenin notunda olduğu gibi ek bir kural)
        if (kalan > 200.0) {
            double ekstra = kalan * 0.10;
            toplamIndirim += ekstra;
            kalan -= ekstra;
        }

        return toplamIndirim;
    }

    // calculateServiceTip(tutar) → double (%10 öneri)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Restoran Sipariş Sistemi ===");

        // Ana yemek seçimi
        System.out.println("Ana Yemekler: 1=Izgara Tavuk(85), 2=Adana Kebap(120), 3=Levrek(110), 4=Mantı(65), 0=Yok");
        System.out.print("Ana yemek seçimi (numara): ");
        int mainSel = sc.nextInt();
        boolean hasMain = (mainSel >= 1 && mainSel <= 4);
        double mainPrice = hasMain ? getMainDishPrice(mainSel) : 0.0;

        // Başlangıç seçimi
        System.out.println("Başlangıçlar: 1=Çorba(25), 2=Humus(45), 3=Sigara Böreği(55), 0=Yok");
        System.out.print("Başlangıç seçimi (numara): ");
        int appSel = sc.nextInt();
        boolean hasApp = (appSel >= 1 && appSel <= 3);
        double appPrice = hasApp ? getAppetizerPrice(appSel) : 0.0;

        // İçecek seçimi
        System.out.println("İçecekler: 1=Kola(15), 2=Ayran(12), 3=Meyve Suyu(35), 4=Limonata(25), 0=Yok");
        System.out.print("İçecek seçimi (numara): ");
        int drinkSel = sc.nextInt();
        boolean hasDrink = (drinkSel >= 1 && drinkSel <= 4);
        double drinkPrice = hasDrink ? getDrinkPrice(drinkSel) : 0.0;

        // Tatlı seçimi
        System.out.println("Tatlılar: 1=Künefe(65), 2=Baklava(55), 3=Sütlaç(35), 0=Yok");
        System.out.print("Tatlı seçimi (numara): ");
        int dessertSel = sc.nextInt();
        boolean hasDessert = (dessertSel >= 1 && dessertSel <= 3);
        double dessertPrice = hasDessert ? getDessertPrice(dessertSel) : 0.0;

        // Saat ve gün bilgisi (gün 1..7 Pzt..Paz)
        System.out.print("Saat (tam saat, örn 15): ");
        int saat = sc.nextInt();
        System.out.print("Gün (1=Pzt ... 7=Paz): ");
        int gun = sc.nextInt();

        // Öğrenci mi?
        System.out.print("Öğrenci misiniz? (1=Evet, 0=Hayır): ");
        int ogr = sc.nextInt();
        boolean isStudent = (ogr == 1);

        // Ara toplam
        double araToplam = mainPrice + appPrice + drinkPrice + dessertPrice;

        // Combo kontrolu
        boolean combo = isComboOrder(hasMain, hasDrink, hasDessert);

        // Happy hour içecek indiriminden sonra içecek fiyatı (kullanıcı test örneğine göre içecek indirimi sabit %20)
        double drinkAfter = drinkPrice; // orijinal içecek fiyatı
        double happyDiscountAmount = 0.0;
        if (isHappyHour(saat) && hasDrink) {
            happyDiscountAmount = drinkAfter * 0.20; // 20% of drink price
        }

        // İndirim toplamı (kural sırasına göre)
        double toplamIndirim = calculateDiscount(araToplam, combo, isStudent, saat, gun, drinkAfter);

        double tutarAfterDiscounts = araToplam - toplamIndirim;

        // Bahşiş önerisi %10 (hesaplanacak final tutar üzerinden)
        double tip = calculateServiceTip(tutarAfterDiscounts);

        System.out.println("\n--- Sipariş Özeti ---");
        System.out.printf("Ara Toplam: %.2f₺\n", araToplam);
        System.out.printf("Combo (%s): %s\n", (combo ? "Var" : "Yok"), (combo ? "Uygulanır" : "Uygulanmaz"));
        if (isHappyHour(saat) && hasDrink) {
            System.out.printf("Happy Hour (içecek indirimi): -%.2f₺\n", happyDiscountAmount);
        }
        if (isStudent && (gun >= 1 && gun <= 5)) {
            System.out.println("Öğrenci İndirimi: Uygulanabilir (hafta içi)");
        }
        System.out.printf("Toplam İndirim: -%.2f₺\n", toplamIndirim);
        System.out.printf("Toplam (İndirim sonrası): %.2f₺\n", tutarAfterDiscounts);
        System.out.printf("Bahşiş Önerisi (%%10): %.2f₺\n", tip);
        System.out.printf("Ödenecek Toplam (önerilen bahşiş hariç): %.2f₺\n", tutarAfterDiscounts);
        System.out.printf("Ödenecek Toplam (bahşiş dahil önerilen): %.2f₺\n", tutarAfterDiscounts + tip);

        // Verilen test örneği:
        // Adana(120) + Humus(45) + Meyve Suyu(35) + Künefe(65), saat=15, öğrenci, çarşamba (gun=3)
        // araToplam = 265
        // combo -15% = -39.75 -> 225.25
        // happy hour içecek -20% of 35 = -7 -> 218.25
        // öğrenci -10% of kalan -> -21.825 => 196.425 => 196.42
        // bahşiş %10 ~ 19.64

        sc.close();
    }
}
