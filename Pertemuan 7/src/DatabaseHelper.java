import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    // =============================================
    // Sesuai PPT: variabel koneksi database
    // String user="root";
    // String pass="";
    // String host="localhost";
    // String db="datamhs";
    // Kita adaptasi ke SQLite
    // =============================================
    private static final String DB_NAME  = "datamhs";
    private static final String DB_PATH  = "database/" + DB_NAME + ".db";
    private static final String URL      = "jdbc:sqlite:" + DB_PATH;

    private Connection connection;

    // =============================================
    // Buka koneksi ke SQLite
    // =============================================
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(URL);
                System.out.println("[OK] Koneksi database berhasil! DB: " + DB_NAME);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR] Driver tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("[ERROR] Koneksi gagal: " + e.getMessage());
        }
        return connection;
    }

    // =============================================
    // Tutup koneksi
    // =============================================
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("[OK] Koneksi ditutup.");
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Gagal tutup koneksi: " + e.getMessage());
        }
    }

    // =============================================
    // Buat tabel mahasiswa
    // =============================================
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS mahasiswa ("
                + "id       INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nim      TEXT    NOT NULL, "
                + "nama     TEXT    NOT NULL, "
                + "jurusan  TEXT    NOT NULL, "
                + "ipk      REAL    NOT NULL"
                + ")";
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute(sql);
            System.out.println("[OK] Tabel mahasiswa siap.");
        } catch (SQLException e) {
            System.out.println("[ERROR] Buat tabel gagal: " + e.getMessage());
        }
    }

    // =============================================
    // Insert data dummy untuk testing
    // =============================================
    public void insertDummyData() {
        String checkSql = "SELECT COUNT(*) FROM mahasiswa";
        try (Statement stmt = getConnection().createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(checkSql)) {
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("[INFO] Data sudah ada, skip insert dummy.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Cek data gagal: " + e.getMessage());
        }

        String[] data = {
            "INSERT INTO mahasiswa (nim,nama,jurusan,ipk) VALUES ('2021001','Budi Santoso','Teknik Informatika',3.75)",
            "INSERT INTO mahasiswa (nim,nama,jurusan,ipk) VALUES ('2021002','Siti Rahayu','Teknik Informatika',3.85)",
            "INSERT INTO mahasiswa (nim,nama,jurusan,ipk) VALUES ('2021003','Ahmad Fauzi','Sistem Informasi',3.60)",
            "INSERT INTO mahasiswa (nim,nama,jurusan,ipk) VALUES ('2021004','Dewi Lestari','Sistem Informasi',3.90)",
            "INSERT INTO mahasiswa (nim,nama,jurusan,ipk) VALUES ('2021005','Rizky Pratama','Teknik Informatika',3.45)"
        };

        try (Statement stmt = getConnection().createStatement()) {
            for (String sql : data) {
                stmt.execute(sql);
            }
            System.out.println("[OK] Data dummy berhasil dimasukkan!");
        } catch (SQLException e) {
            System.out.println("[ERROR] Insert dummy gagal: " + e.getMessage());
        }
    }
}