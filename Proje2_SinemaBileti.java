/**
 * Ad Soyad: Ceren Çam
 * Öğrenci No: 250541073
 * Proje: Sinema Bileti Fiyatlandırma Sistemi
 * Tarih: 27.11.2025
 */

import java.util.Scanner;

public class Proje2_SinemaBileti {

    // isWeekend(gun) → boolean (1=Monday ... 7=Sunday)
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    // isMatinee(saat) → boolean (matine 12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // calculateBasePrice(gun, saat) → double
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) return 45.0; // hafta içi matine
        if (!weekend && !matinee) return 65.0; // hafta içi normal
        if (weekend && matinee) return 55.0; // hafta sonu matine
        return 85.0; // hafta sonu normal
    }

    // calculateDiscount(yas, meslek, gun) → double (oran olarak: 0.20 => %20)
    // meslek: 1=Öğrenci, 2=Öğretmen, 3=Diğer
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double discount = 0.0;

        // yaş tabanlı indirimler (üst üste uygulanmaz; yaş indirimi varsa kullanılır)
        if (yas >= 65) {
            discount = 0.30;
            return discount;
        } else if (yas <= 12) {
            discount = 0.25;
            return discount;
        }

        // meslek bazlı indirim
        switch (meslek) {
            case 1: // Öğrenci
                // Pzt-Prş: gün 1..4 -> %20, Cum-Paz 5..7 -> %15
                if (gun >= 1 && gun <= 4) discount = 0.20;
                else discount = 0.15;
                break;
            case 2: // Öğretmen
                // sadece Çarşamba (3) %35
                if (gun == 3) discount = 0.35;
                else discount = 0.0;
                break;
            default:
                discount = 0.0;
                break;
        }
        return discount;
    }

    // getFormatExtra(filmTuru) → double (1=2D,2=3D,3=IMAX,4=4DX)
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 2: return 25.0; // 3D
            case 3: return 35.0; // IMAX
            case 4: return 50.0; // 4DX
            default: return 0.0; // 2D
        }
    }

    // calculateFinalPrice(...) → double
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double discounted = base * (1.0 - discountRate);
        double extra = getFormatExtra(filmTuru);
        return discounted + extra;
    }

    // generateTicketInfo(...) → void
    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {
        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double discounted = base * (1.0 - discountRate);
        double extra = getFormatExtra(filmTuru);
        double finalPrice = discounted + extra;

        System.out.println("\n--- Bilet Bilgisi ---");
        System.out.printf("Gün (1=Pzt ... 7=Paz): %d\n", gun);
        System.out.printf("Saat: %d:00\n", saat);
        System.out.printf("Yaş: %d\n", yas);
        String meslekStr = (meslek == 1) ? "Öğrenci" : (meslek == 2) ? "Öğretmen" : "Diğer";
        System.out.printf("Meslek: %s\n", meslekStr);
        String formatStr = (filmTuru == 1) ? "2D" : (filmTuru == 2) ? "3D" : (filmTuru == 3) ? "IMAX" : "4DX";
        System.out.printf("Film Türü: %s\n", formatStr);

        System.out.printf("Temel Fiyat: %.2f₺\n", base);
        System.out.printf("İndirim Oranı: %.0f%%\n", discountRate * 100);
        System.out.printf("İndirimli Bilet (Temel için): %.2f₺\n", discounted);
        System.out.printf("Format Ek Ücret: %.2f₺\n", extra);
        System.out.printf("Nihai Fiyat: %.2f₺\n", finalPrice);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Sinema Bileti Hesaplama ===");
        System.out.print("Gün (1=Pzt,2=Sal,3=Çar,4=Per,5=Cum,6=Cmt,7=Paz): ");
        int gun = sc.nextInt();
        System.out.print("Saat (tam saat, örn 10, 20): ");
        int saat = sc.nextInt();
        System.out.print("Yaş: ");
        int yas = sc.nextInt();
        System.out.print("Meslek (1=Öğrenci,2=Öğretmen,3=Diğer): ");
        int meslek = sc.nextInt();
        System.out.print("Film türü (1=2D,2=3D,3=IMAX,4=4DX): ");
        int filmTuru = sc.nextInt();

        generateTicketInfo(gun, saat, yas, meslek, filmTuru);

        // Örnek testler:
        // Test1: gun=4 saat=10 yas=22 meslek=1 filmTuru=2 -> Temel 45, %20 -> 36, +25 = 61
        // Test2: gun=3 saat=20 yas=35 meslek=2 filmTuru=3 -> Temel 65, %35 -> 42.25, +35 = 77.25

        sc.close();
    }
}
