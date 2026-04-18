import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {

    private Connection koneksi;

    public MahasiswaDAO() {
        this.koneksi = DatabaseConnection.getConnection();
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS datamhs ("
                + "nim      VARCHAR(15) NOT NULL PRIMARY KEY,"
                + "nama     VARCHAR(30),"
                + "semester INTEGER,"
                + "kelas    VARCHAR(1)"
                + ")";
        try {
            Statement stmt = koneksi.createStatement();
            stmt.execute(sql);
            System.out.println("Tabel datamhs siap digunakan.");
        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel: " + e.getMessage());
        }
    }

    public boolean simpanData(DataMHS mhs) {
        String sql = "INSERT INTO datamhs (nim, nama, semester, kelas) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pStat = koneksi.prepareStatement(sql);
            pStat.setString(1, mhs.getNim());
            pStat.setString(2, mhs.getNama());
            pStat.setInt(3, mhs.getSemester());
            pStat.setString(4, mhs.getKelas());
            pStat.executeUpdate();
            System.out.println("Data berhasil disimpan: " + mhs.getNama());
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan: " + e.getMessage());
            return false;
        }
    }

    public List<DataMHS> tampilSemuaData() {
        List<DataMHS> listMHS = new ArrayList<>();
        String sql = "SELECT * FROM datamhs";
        try {
            Statement stmt = koneksi.createStatement();
            ResultSet rs   = stmt.executeQuery(sql);
            while (rs.next()) {
                DataMHS mhs = new DataMHS();
                mhs.setNim(rs.getString("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setSemester(rs.getInt("semester"));
                mhs.setKelas(rs.getString("kelas"));
                listMHS.add(mhs);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data: " + e.getMessage());
        }
        return listMHS;
    }

    public DataMHS cariDataByNim(String nim) {
        String sql  = "SELECT * FROM datamhs WHERE nim = ?";
        DataMHS mhs = null;
        try {
            PreparedStatement pStat = koneksi.prepareStatement(sql);
            pStat.setString(1, nim);
            ResultSet rs = pStat.executeQuery();
            if (rs.next()) {
                mhs = new DataMHS();
                mhs.setNim(rs.getString("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setSemester(rs.getInt("semester"));
                mhs.setKelas(rs.getString("kelas"));
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari data: " + e.getMessage());
        }
        return mhs;
    }

    public boolean updateData(DataMHS mhs) {
        String sql = "UPDATE datamhs SET nama=?, semester=?, kelas=? WHERE nim=?";
        try {
            PreparedStatement pStat = koneksi.prepareStatement(sql);
            pStat.setString(1, mhs.getNama());
            pStat.setInt(2, mhs.getSemester());
            pStat.setString(3, mhs.getKelas());
            pStat.setString(4, mhs.getNim());
            int row = pStat.executeUpdate();
            if (row > 0) {
                System.out.println("Data berhasil diupdate: " + mhs.getNim());
                return true;
            } else {
                System.out.println("NIM tidak ditemukan: " + mhs.getNim());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Gagal update: " + e.getMessage());
            return false;
        }
    }

    public boolean hapusData(String nim) {
        String sql = "DELETE FROM datamhs WHERE nim = ?";
        try {
            PreparedStatement pStat = koneksi.prepareStatement(sql);
            pStat.setString(1, nim);
            int row = pStat.executeUpdate();
            if (row > 0) {
                System.out.println("Data berhasil dihapus: NIM " + nim);
                return true;
            } else {
                System.out.println("NIM tidak ditemukan: " + nim);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Gagal hapus: " + e.getMessage());
            return false;
        }
    }
}