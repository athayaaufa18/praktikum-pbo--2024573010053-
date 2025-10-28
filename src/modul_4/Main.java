package modul_4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("---------------------------------------------");
        System.out.println("--- SISTEM KARTU RENCANA STUDI (KRS) ---");
        System.out.println("---------------------------------------------");

        // Input data mahasiswa
        System.out.println("\n--- Input data Mahasiswa ---");
        System.out.print("Nama : ");
        String nama = input.nextLine();
        System.out.print("NIM  : ");
        String nim = input.nextLine();
        System.out.print("Jurusan : ");
        String jurusan = input.nextLine();

        // Membuat object Mahasiswa (tanpa parameter IPK)
        Mahasiswa mhs = new Mahasiswa(nama, nim, jurusan);

        // Membuat object KRS (maksimal 10 mata kuliah)
        KartuRencanaStudi krs = new KartuRencanaStudi(mhs, 10);

        // Menu KRS
        boolean running = true;
        while (running) {
            System.out.println("\n---------------------------------------------");
            System.out.println("               MENU KRS                      ");
            System.out.println("---------------------------------------------");
            System.out.println("1. Tambah Mata Kuliah");
            System.out.println("2. Input Nilai Mata Kuliah");
            System.out.println("3. Tampilkan KRS");
            System.out.println("4. Hapus Mata Kuliah");
            System.out.println("5. Tampilkan Nilai Terbaik & Terburuk");
            System.out.println("6. Keluar");
            System.out.println("---------------------------------------------");
            System.out.print("Pilih menu: ");

            int pilihan = input.nextInt();
            input.nextLine(); // hapus newline sisa enter

            switch (pilihan) {
                case 1:
                    // Tambah mata kuliah
                    System.out.println("\n--- TAMBAH MATA KULIAH ---");
                    System.out.print("Kode Mata Kuliah: ");
                    String kode = input.nextLine();
                    System.out.print("Nama Mata Kuliah: ");
                    String namaMK = input.nextLine();
                    System.out.print("SKS: ");
                    int sks = input.nextInt();
                    input.nextLine(); // hapus newline

                    Matakuliah mk = new Matakuliah(kode, namaMK, sks);
                    krs.tambahMatakuliah(mk);
                    break;

                case 2:
                    // Input nilai
                    System.out.println("\n--- INPUT NILAI ---");
                    System.out.print("Masukkan Kode Mata Kuliah: ");
                    String kodeCari = input.nextLine();

                    Matakuliah mkCari = krs.cariMatakuliah(kodeCari);
                    if (mkCari != null) {
                        System.out.print("Input Nilai (0-100): ");
                        double nilai = input.nextDouble();
                        input.nextLine(); // hapus newline

                        mkCari.setNilai(nilai);
                        System.out.println(" Nilai berhasil diinput!");
                    } else {
                        System.out.println(" Mata kuliah tidak ditemukan!");
                    }
                    break;

                case 3:
                    // Tampilkan KRS
                    krs.tampilkanKRS();
                    break;

                case 4:
                    // Hapus mata kuliah
                    System.out.println("\n--- HAPUS MATA KULIAH ---");
                    System.out.print("Masukkan kode mata kuliah yang ingin dihapus: ");
                    String kodeHapus = input.nextLine();
                    krs.hapusMatakuliah(kodeHapus);
                    break;

                case 5:
                    // Tampilkan nilai terbaik dan terburuk
                    krs.tampilkanNilaiTerbaikDanTerburuk();
                    break;

                case 6:
                    // Keluar
                    System.out.println("Terima kasih telah menggunakan sistem KRS!");
                    running = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        }

        input.close();
    }
}