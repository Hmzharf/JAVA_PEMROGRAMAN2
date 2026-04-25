import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        // =============================================
        // Setup database terlebih dahulu
        // =============================================
        System.out.println("===========================================");
        System.out.println("  PEMROGRAMAN 2 - Sistem Informasi Mhs   ");
        System.out.println("===========================================");

        DatabaseHelper db = new DatabaseHelper();
        db.createTable();
        db.insertDummyData();
        db.closeConnection();

        // =============================================
        // Jalankan GUI di Event Dispatch Thread
        // =============================================
        SwingUtilities.invokeLater(() -> {
            try {
                // Set tampilan mengikuti OS
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception e) {
                System.out.println("Look and Feel gagal: " + e.getMessage());
            }

            // Tampilkan form utama
            FormMahasiswa form = new FormMahasiswa();
            form.setVisible(true);
            System.out.println("[OK] Aplikasi berhasil dijalankan!");
        });
    }
}