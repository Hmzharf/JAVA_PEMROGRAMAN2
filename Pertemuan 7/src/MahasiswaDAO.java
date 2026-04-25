import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {

    private DatabaseHelper dbHelper = new DatabaseHelper();

    // =============================================
    // CREATE
    // =============================================
    public boolean tambah(Mahasiswa mhs) {
        String sql = "INSERT INTO mahasiswa (nim,nama,jurusan,ipk) VALUES (?,?,?,?)";
        try (PreparedStatement ps = dbHelper.getConnection().prepareStatement(sql)) {
            ps.setString(1, mhs.getNim());
            ps.setString(2, mhs.getNama());
            ps.setString(3, mhs.getJurusan());
            ps.setDouble(4, mhs.getIpk());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("[ERROR] Tambah gagal: " + e.getMessage());
            return false;
        }
    }

    // =============================================
    // READ ALL
    // =============================================
    public List<Mahasiswa> getAll() {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa ORDER BY id ASC";
        try (Statement stmt = dbHelper.getConnection().createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Mahasiswa(
                    rs.getInt("id"),
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("jurusan"),
                    rs.getDouble("ipk")
                ));
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Ambil data gagal: " + e.getMessage());
        }
        return list;
    }

    // =============================================
    // UPDATE
    // =============================================
    public boolean update(Mahasiswa mhs) {
        String sql = "UPDATE mahasiswa SET nim=?,nama=?,jurusan=?,ipk=? WHERE id=?";
        try (PreparedStatement ps = dbHelper.getConnection().prepareStatement(sql)) {
            ps.setString(1, mhs.getNim());
            ps.setString(2, mhs.getNama());
            ps.setString(3, mhs.getJurusan());
            ps.setDouble(4, mhs.getIpk());
            ps.setInt(5, mhs.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("[ERROR] Update gagal: " + e.getMessage());
            return false;
        }
    }

    // =============================================
    // DELETE
    // =============================================
    public boolean delete(int id) {
        String sql = "DELETE FROM mahasiswa WHERE id=?";
        try (PreparedStatement ps = dbHelper.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("[ERROR] Hapus gagal: " + e.getMessage());
            return false;
        }
    }

    public void close() { dbHelper.closeConnection(); }
}