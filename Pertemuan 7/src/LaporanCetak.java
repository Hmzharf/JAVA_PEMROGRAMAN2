import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.export.*;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaporanCetak {

    private DatabaseHelper dbHelper = new DatabaseHelper();
    private MahasiswaDAO   dao      = new MahasiswaDAO();

    // =============================================
    // Method 1: Cetak dengan koneksi SQLite langsung
    // Sesuai PPT pertemuan 7
    // =============================================
    public void cetakLaporan() {
        try {
            String jrxmlPath = "src/laporan/LaporanMahasiswa.jrxml";
            File   jrxmlFile = new File(jrxmlPath);

            if (!jrxmlFile.exists()) {
                System.out.println("[ERROR] File jrxml tidak ditemukan!");
                return;
            }

            // Step 1: Compile jrxml
            System.out.println("[INFO] Compile jrxml...");
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);

            // Step 2: Koneksi database
            Connection conn = dbHelper.getConnection();

            // Step 3: Parameter
            Map<String, Object> params = new HashMap<>();
            params.put("REPORT_TITLE", "Laporan Data Mahasiswa");

            // Step 4: Fill laporan
            System.out.println("[INFO] Mengisi data...");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport, params, conn
            );

            // Step 5: Preview
            JasperViewer.viewReport(jasperPrint, false);

            // Step 6: Export PDF
            exportToPDF(jasperPrint);

        } catch (JRException e) {
            System.out.println("[ERROR] " + e.getMessage());
            e.printStackTrace();
        } finally {
            dbHelper.closeConnection();
        }
    }

    // =============================================
    // Method 2: Cetak dari List<Mahasiswa>
    // Menggunakan JRBeanCollectionDataSource
    // =============================================
    public void cetakLaporanDariList(List<Mahasiswa> listMhs) {
        try {
            String jrxmlPath = "src/laporan/LaporanMahasiswa.jrxml";

            // Step 1: Compile
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);

            // Step 2: Buat datasource dari List
            JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(listMhs);

            // Step 3: Parameter
            Map<String, Object> params = new HashMap<>();
            params.put("REPORT_TITLE", "Laporan Data Mahasiswa");
            params.put("CREATED_BY",   "Admin Sistem");

            // Step 4: Fill
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport, params, dataSource
            );

            // Step 5: Preview
            JasperViewer.viewReport(jasperPrint, false);

            // Step 6: Export PDF
            exportToPDF(jasperPrint);

        } catch (JRException e) {
            System.out.println("[ERROR] Cetak gagal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // =============================================
    // Export ke PDF
    // =============================================
    private void exportToPDF(JasperPrint jasperPrint) {
        try {
            String outputDir = "laporan/";
            new File(outputDir).mkdirs();

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String pdfPath   = outputDir + "LaporanMahasiswa_" + timestamp + ".pdf";

            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);
            System.out.println("[OK] PDF berhasil dibuat: " + pdfPath);

        } catch (JRException e) {
            System.out.println("[ERROR] Export PDF gagal: " + e.getMessage());
        }
    }
}