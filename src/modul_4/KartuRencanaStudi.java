package modul_4;

public class KartuRencanaStudi {
    private final Mahasiswa mahasiswa;
    private final Matakuliah[] daftarMataKuliah;
    private int jumlahMatkul;
    private final int maxMatkul;
    private final int batasSKS = 24; // Batas maksimum total SKS

    public KartuRencanaStudi(Mahasiswa mahasiswa, int maxMatkul) {
        this.mahasiswa = mahasiswa;
        this.maxMatkul = maxMatkul;
        this.daftarMataKuliah = new Matakuliah[maxMatkul];
        this.jumlahMatkul = 0;
    }

    // Menambah mata kuliah ke KRS (dengan validasi total SKS ≤ 24)
    public void tambahMatakuliah(Matakuliah matkul) {
        int totalSKSSekarang = hitungTotalSKS();
        if (jumlahMatkul >= maxMatkul) {
            System.out.println("\nNote: KRS sudah penuh! Maksimal " + maxMatkul + " mata kuliah.");
        } else if (totalSKSSekarang + matkul.getSks() > batasSKS) {
            System.out.println("\nNote: Tidak dapat menambahkan " + matkul.getNama() +
                    " karena total SKS akan melebihi batas (" + batasSKS + " SKS).");
        } else {
            daftarMataKuliah[jumlahMatkul] = matkul;
            jumlahMatkul++;
            System.out.println("\nNote: Mata Kuliah " + matkul.getNama() + " berhasil ditambahkan.");
        }
    }

    // Menghapus mata kuliah berdasarkan kode
    public void hapusMatakuliah(String kode) {
        boolean ditemukan = false;
        for (int i = 0; i < jumlahMatkul; i++) {
            if (daftarMataKuliah[i].getKode().equalsIgnoreCase(kode)) {
                ditemukan = true;
                System.out.println("\nNote: Mata kuliah " + daftarMataKuliah[i].getNama() + " telah dihapus dari KRS.");
                // Geser elemen setelahnya ke kiri
                for (int j = i; j < jumlahMatkul - 1; j++) {
                    daftarMataKuliah[j] = daftarMataKuliah[j + 1];
                }
                daftarMataKuliah[jumlahMatkul - 1] = null;
                jumlahMatkul--;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("\nNote: Mata kuliah dengan kode " + kode + " tidak ditemukan di KRS.");
        }
    }

    // Menghitung total SKS
    public int hitungTotalSKS() {
        int totalSKS = 0;
        for (int i = 0; i < jumlahMatkul; i++) {
            totalSKS += daftarMataKuliah[i].getSks();
        }
        return totalSKS;
    }

    // Menghitung IPK
    public double hitungIPK() {
        if (jumlahMatkul == 0) return 0.0;

        double totalBobot = 0.0;
        int totalSKS = 0;

        for (int i = 0; i < jumlahMatkul; i++) {
            Matakuliah mk = daftarMataKuliah[i];
            totalBobot += mk.getBobotNilai() * mk.getSks();
            totalSKS += mk.getSks();
        }

        return totalSKS > 0 ? totalBobot / totalSKS : 0.0;
    }

    // Menampilkan mata kuliah dengan nilai terbaik dan terburuk
    public void tampilkanNilaiTerbaikDanTerburuk() {
        if (jumlahMatkul == 0) {
            System.out.println("Belum ada mata kuliah untuk dianalisis.");
            return;
        }

        Matakuliah terbaik = daftarMataKuliah[0];
        Matakuliah terburuk = daftarMataKuliah[0];

        for (int i = 1; i < jumlahMatkul; i++) {
            if (daftarMataKuliah[i].getBobotNilai() > terbaik.getBobotNilai()) {
                terbaik = daftarMataKuliah[i];
            }
            if (daftarMataKuliah[i].getBobotNilai() < terburuk.getBobotNilai()) {
                terburuk = daftarMataKuliah[i];
            }
        }

        System.out.println("\n=== NILAI TERBAIK & TERBURUK ===");
        System.out.println("Nilai Terbaik : " + terbaik.getNama() + " (" + terbaik.getNilai() + ")");
        System.out.println("Nilai Terburuk: " + terburuk.getNama() + " (" + terburuk.getNilai() + ")");
    }

    // Menampilkan seluruh KRS
    public void tampilkanKRS() {
        System.out.println("---------------------------------------------");
        System.out.println("       KARTU RENCANA STUDI (KRS)             ");
        System.out.println("---------------------------------------------");
        System.out.println("Nama Mahasiswa : " + mahasiswa.getNama());
        System.out.println("NPM            : " + mahasiswa.getNim());
        System.out.println("JURUSAN        : " + mahasiswa.getJurusan());
        System.out.println("---------------------------------------------");
        System.out.println("KODE | MATA KULIAH | SKS | NILAI ");
        System.out.println("---------------------------------------------");

        if (jumlahMatkul == 0) {
            System.out.println("Belum ada mata kuliah yang diambil.");
        } else {
            for (int i = 0; i < jumlahMatkul; i++) {
                daftarMataKuliah[i].tampilkanInfo();
            }
        }

        System.out.println("---------------------------------------------");
        System.out.println("TOTAL SKS        : " + hitungTotalSKS());
        System.out.printf("IPK Semester     : %.2f\n", hitungIPK());
        System.out.println("---------------------------------------------\n");
    }

    // Mencari mata kuliah berdasarkan kode
    public Matakuliah cariMatakuliah(String kode) {
        for (int i = 0; i < jumlahMatkul; i++) {
            if (daftarMataKuliah[i].getKode().equalsIgnoreCase(kode)) {
                return daftarMataKuliah[i];
            }
        }
        return null;
    }
}