/**
 * Ad Soyad: Ceren Çam
 * Öğrenci No: 250541073
 * Proje: Öğrenci Not Değerlendirme Sistemi
 * Tarih: 27.11.2025
 */

import java.util.Scanner;

public class Proje1_NotSistemi {

    // calculateAverage(vize, final, odev) → double
    public static double calculateAverage(double vize, double fin, double odev) {
        return vize * 0.30 + fin * 0.40 + odev * 0.30;
    }

    // isPassingGrade(ortalama) → boolean
    public static boolean isPassingGrade(double ort) {
        return ort >= 50.0;
    }

    // getLetterGrade(ortalama) → char
    public static char getLetterGrade(double ort) {
        if (ort >= 90) return 'A';
        else if (ort >= 85) return 'B';
        else if (ort >= 80) return 'C';
        else if (ort >= 70) return 'D';
        else if (ort >= 60) return 'E';
        else return 'F';
    }

    // isHonorList(ortalama, vize, final, odev) → boolean
    public static boolean isHonorList(double ort, double vize, double fin, double odev) {
        return ort >= 85.0 && vize >= 70.0 && fin >= 70.0 && odev >= 70.0;
    }

    // hasRetakeRight(ortalama) → boolean
    public static boolean hasRetakeRight(double ort) {
        return ort >= 40.0 && ort < 50.0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Öğrenci Not Değerlendirme Sistemi ===");
        System.out.print("Vize notu (0-100): ");
        double vize = sc.nextDouble();
        System.out.print("Final notu (0-100): ");
        double fin = sc.nextDouble();
        System.out.print("Ödev notu (0-100): ");
        double odev = sc.nextDouble();

        double ort = calculateAverage(vize, fin, odev);
        boolean geçti = isPassingGrade(ort);
        char harf = getLetterGrade(ort);
        boolean onur = isHonorList(ort, vize, fin, odev);
        boolean bütünleme = hasRetakeRight(ort);

        System.out.println("\n--- Rapor ---");
        System.out.printf("Ortalama: %.2f\n", ort);
        System.out.printf("Harf Notu: %c\n", harf);
        System.out.printf("Durum: %s\n", (geçti ? "GEÇTİ" : "KALDI"));
        System.out.printf("Onur Listesi: %s\n", (onur ? "EVET" : "HAYIR"));
        System.out.printf("Bütünleme Hakkı: %s\n", (bütünleme ? "EVET" : "HAYIR"));

        // Örnek test çıktısı (istemiyorsan yorum satırı yapabilirsin)
        // Test senaryoları kontrolü için:
        // Test1: 85,90,88 -> Ort ~87.90, B, Geçti, Onur EVET
        sc.close();
    }
}
