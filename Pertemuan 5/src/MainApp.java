import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   APLIKASI DATABASE MAHASISWA SQLite   ");
        System.out.println("========================================");

        MahasiswaDAO dao = new MahasiswaDAO();
        System.out.println();

        // INSERT
        System.out.println("--- INSERT DATA ---");
        dao.simpanData(new DataMHS("2021001", "Budi Santoso",  3, "A"));
        dao.simpanData(new DataMHS("2021002", "Sri Rahayu",    3, "A"));
        dao.simpanData(new DataMHS("2021003", "Ahmad Fauzi",   5, "B"));
        dao.simpanData(new DataMHS("2021004", "Dewi Lestari",  5, "B"));
        dao.simpanData(new DataMHS("2021005", "Rizky Pratama", 7, "C"));
        System.out.println();

        // SELECT ALL
        System.out.println("--- SEMUA DATA ---");
        tampilkanTabel(dao.tampilSemuaData());
        System.out.println();

        // SELECT WHERE
        System.out.println("--- CARI NIM 2021003 ---");
        DataMHS hasil = dao.cariDataByNim("2021003");
        if (hasil != null) {
            System.out.println("+------------------+---------------------------+----------+-------+");
            System.out.println("| NIM             | NAMA                      | SEMESTER | KELAS |");
            System.out.println("+------------------+---------------------------+----------+-------+");
            System.out.println(hasil);
            System.out.println("+------------------+---------------------------+----------+-------+");
        }
        System.out.println();

        // UPDATE
        System.out.println("--- UPDATE NIM 2021003 ---");
        dao.updateData(new DataMHS("2021003", "Ahmad Fauzi Updated", 6, "A"));
        System.out.println();

        // TAMPIL SETELAH UPDATE
        System.out.println("--- DATA SETELAH UPDATE ---");
        tampilkanTabel(dao.tampilSemuaData());
        System.out.println();

        // DELETE
        System.out.println("--- HAPUS NIM 2021005 ---");
        dao.hapusData("2021005");
        System.out.println();

        // TAMPIL SETELAH DELETE
        System.out.println("--- DATA SETELAH DELETE ---");
        tampilkanTabel(dao.tampilSemuaData());
        System.out.println();

        // TUTUP KONEKSI
        DatabaseConnection.closeConnection();
        System.out.println("========================================");
        System.out.println("            Program Selesai!            ");
        System.out.println("========================================");
    }

    private static void tampilkanTabel(List<DataMHS> listMHS) {
        if (listMHS.isEmpty()) {
            System.out.println("Tidak ada data.");
            return;
        }
        System.out.println("+------------------+---------------------------+----------+-------+");
        System.out.println("| NIM             | NAMA                      | SEMESTER | KELAS |");
        System.out.println("+------------------+---------------------------+----------+-------+");
        for (DataMHS mhs : listMHS) {
            System.out.println(mhs);
        }
        System.out.println("+------------------+---------------------------+----------+-------+");
        System.out.println("Total: " + listMHS.size() + " mahasiswa");
    }
}