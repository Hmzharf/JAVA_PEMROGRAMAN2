import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:sqlite:MHS.db";
    private static Connection connection = null;

    // =============================================
    // Method untuk membuat koneksi ke SQLite
    // =============================================
    public static Connection getConnection() {
        try {
            // Load JDBC Driver SQLite
            Class.forName("org.sqlite.JDBC");

            // Membuat koneksi ke database SQLite
            connection = DriverManager.getConnection(DB_URL);

            System.out.println("✅ Koneksi ke database SQLite berhasil!");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver SQLite tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Koneksi gagal: " + e.getMessage());
        }

        return connection;
    }

    // =============================================
    // Method untuk menutup koneksi
    // =============================================
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Koneksi database ditutup.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Gagal menutup koneksi: " + e.getMessage());
        }
    }
}