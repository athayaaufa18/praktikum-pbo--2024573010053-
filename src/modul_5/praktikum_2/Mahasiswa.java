package modul_5.praktikum_2;

public class Mahasiswa {
    // Atribut/Data (Semua Private untuk Enkapsulasi)
    private String npm;
    private String nama;
    private String jurusan;
    private int semester;
    private double ipk;
    private boolean aktif;

    // Constructor
    public Mahasiswa(String npm, String nama, String jurusan) {
        this.npm = npm;
        this.nama = nama;
        this.jurusan = jurusan;
        this.ipk = 0; // Inisialisasi awal
        this.semester = 1;
        this.aktif = true;
    }

    // ========== GETTER METHODS (Pengambil) ==========
    public String getNpm() {
        return npm;
    }

    public String getNama() {
        return nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public double getIpk() {
        return ipk;
    }

    public int getSemester() {
        return semester;
    }

    // Getter boolean: convention "is" prefix
    public boolean isAktif() {
        return aktif;
    }

    // ========== SETTER METHODS (Pengatur) ==========
    // Note: npm hanya boleh sekali (tidak ada setter)

    // Setter untuk nama, dengan validasi
    public void setNama(String nama) {
        // Validasi: nama tidak boleh kosong
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("[ERROR] Nama tidak boleh kosong!");
            return;
        }

        // Validasi: Nama hanya huruf dan spasi
        if (!nama.matches("^[a-zA-Z\\s]+$")) {
            System.out.println("[ERROR] Nama hanya boleh berisi huruf dan spasi!");
            return;
        }

        // Jika Lolos Validasi
        this.nama = nama;
        System.out.println("✓ Nama berhasil diubah menjadi: " + nama);
    }

    // Setter untuk jurusan, dengan validasi
    public void setJurusan(String jurusan) {
        if (jurusan == null || jurusan.trim().isEmpty() || !isAktif()) {
            System.out.println("[ERROR] Jurusan tidak valid atau sedang tidak aktif!");
            return;
        }

        this.jurusan = jurusan;
        System.out.println("✓ Jurusan berhasil diubah menjadi: " + jurusan);
    }

    // Setter untuk IPK, dengan validasi range
    public void setIpk(double ipk) {
        if (ipk < 0 || ipk > 4.0) {
            System.out.println("[ERROR] IPK harus antara 0.0 - 4.0!");
            return;
        }

        this.ipk = ipk;
        System.out.printf("✓ IPK berhasil diubah menjadi: %.2f\n", ipk);
    }

    // Setter untuk Semester, dengan validasi
    public void setSemester(int semester) {
        if (semester < 1 || semester > 14) {
            System.out.println("[ERROR] Semester harus antara 1 - 14!");
            return;
        }

        this.semester = semester;
        System.out.println("✓ Semester berhasil diubah menjadi: " + semester);
    }

    // Setter untuk Status Aktif
    public void setAktif(boolean aktif) {
        this.aktif = aktif;
        System.out.println("✓ Status berhasil diubah menjadi: " + (aktif ? "AKTIF" : "TIDAK AKTIF"));
    }


    // ========== PRIVATE METHODS (Perilaku Internal) ==========
    private String getPeringatanIpk() {
        if (ipk < 2.0 && ipk > 0) {
            return ("[Peringatan: IPK di bawah standar!]");
        } else if (ipk == 0) {
            return ("[BELUM ADA NILAI]");
        } else if (ipk > 3.8) {
            return ("[Excellent! IPK sangat baik!]");
        }
        return "";
    }

    // Private method
    private String getPredikat() {
        if (ipk >= 3.5) {
            return "Cum Laude";
        } else if (ipk >= 3.0) {
            return "Sangat Baik";
        } else if (ipk >= 2.5) {
            return "Baik";
        } else if (ipk >= 2.0) {
            return "Cukup";
        } else {
            return "Kurang";
        }
    }


    // ========== PUBLIC METHOD (Antarmuka Utama) ==========
    public void tampilkanInfo() {
        System.out.println("===============================");
        System.out.println("DATA LENGKAP MAHASISWA");
        System.out.println("-------------------------------");
        System.out.println("NPM       : " + npm);
        System.out.println("Nama      : " + nama);
        System.out.println("Jurusan   : " + jurusan);
        System.out.println("Semester  : " + semester);
        System.out.printf("IPK       : %.2f %s\n", ipk, getPeringatanIpk());
        System.out.println("Predikat  : " + getPredikat());
        System.out.println("Status    : " + (aktif ? "AKTIF" : "TIDAK AKTIF"));
        System.out.println("===============================");
    }
}
