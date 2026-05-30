package com.unpam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Nilai {
    private int idNilai;
    private String nim;
    private String namaMahasiswa;
    private String kodeMataKuliah;
    private String namaMataKuliah;
    private int nilaiTugas;
    private int nilaiUts;
    private int nilaiUas;
    private double nilaiAkhir;
    private String grade;
    private String pesan;

    private final Koneksi koneksi = new Koneksi();

    public int getIdNilai() {
        return idNilai;
    }

    public void setIdNilai(int idNilai) {
        this.idNilai = idNilai;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getKodeMataKuliah() {
        return kodeMataKuliah;
    }

    public void setKodeMataKuliah(String kodeMataKuliah) {
        this.kodeMataKuliah = kodeMataKuliah;
    }

    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }

    public void setNamaMataKuliah(String namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }

    public int getNilaiTugas() {
        return nilaiTugas;
    }

    public void setNilaiTugas(int nilaiTugas) {
        this.nilaiTugas = nilaiTugas;
    }

    public int getNilaiUts() {
        return nilaiUts;
    }

    public void setNilaiUts(int nilaiUts) {
        this.nilaiUts = nilaiUts;
    }

    public int getNilaiUas() {
        return nilaiUas;
    }

    public void setNilaiUas(int nilaiUas) {
        this.nilaiUas = nilaiUas;
    }

    public double getNilaiAkhir() {
        return nilaiAkhir;
    }

    public String getGrade() {
        return grade;
    }

    public String getPesan() {
        return pesan;
    }

    private double hitungNilaiAkhir(int tugas, int uts, int uas) {
        return (tugas * 0.30) + (uts * 0.30) + (uas * 0.40);
    }

    private String hitungGrade(double nilaiAkhir) {
        if (nilaiAkhir >= 85) {
            return "A";
        } else if (nilaiAkhir >= 75) {
            return "B";
        } else if (nilaiAkhir >= 65) {
            return "C";
        } else if (nilaiAkhir >= 50) {
            return "D";
        } else {
            return "E";
        }
    }

    public boolean simpan() {
        boolean berhasil = false;
        String sql = "INSERT INTO tbnilai(nim, kode_mk, nilai_tugas, nilai_uts, nilai_uas) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = koneksi.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, nim);
            ps.setString(2, kodeMataKuliah);
            ps.setInt(3, nilaiTugas);
            ps.setInt(4, nilaiUts);
            ps.setInt(5, nilaiUas);

            berhasil = ps.executeUpdate() > 0;

        } catch (Exception ex) {
            pesan = "Kesalahan simpan nilai: " + ex;
        }

        return berhasil;
    }

    public boolean update() {
        boolean berhasil = false;
        String sql = "UPDATE tbnilai SET nim=?, kode_mk=?, nilai_tugas=?, nilai_uts=?, nilai_uas=? WHERE id_nilai=?";

        try (Connection connection = koneksi.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, nim);
            ps.setString(2, kodeMataKuliah);
            ps.setInt(3, nilaiTugas);
            ps.setInt(4, nilaiUts);
            ps.setInt(5, nilaiUas);
            ps.setInt(6, idNilai);

            berhasil = ps.executeUpdate() > 0;

        } catch (Exception ex) {
            pesan = "Kesalahan update nilai: " + ex;
        }

        return berhasil;
    }

    public boolean hapus(int idNilai) {
        boolean berhasil = false;
        String sql = "DELETE FROM tbnilai WHERE id_nilai=?";

        try (Connection connection = koneksi.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idNilai);
            berhasil = ps.executeUpdate() > 0;

        } catch (Exception ex) {
            pesan = "Kesalahan hapus nilai: " + ex;
        }

        return berhasil;
    }

    public Nilai cari(int idNilai) {
        Nilai nilai = null;
        String sql = "SELECT * FROM tbnilai WHERE id_nilai=?";

        try (Connection connection = koneksi.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idNilai);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nilai = new Nilai();
                nilai.setIdNilai(rs.getInt("id_nilai"));
                nilai.setNim(rs.getString("nim"));
                nilai.setKodeMataKuliah(rs.getString("kode_mk"));
                nilai.setNilaiTugas(rs.getInt("nilai_tugas"));
                nilai.setNilaiUts(rs.getInt("nilai_uts"));
                nilai.setNilaiUas(rs.getInt("nilai_uas"));
            }

            rs.close();

        } catch (Exception ex) {
            pesan = "Kesalahan cari nilai: " + ex;
        }

        return nilai;
    }

    public List<Nilai> tampilSemua() {
        List<Nilai> daftar = new ArrayList<>();

        String sql = "SELECT n.id_nilai, n.nim, m.nama, n.kode_mk, mk.nama_mk, "
                + "n.nilai_tugas, n.nilai_uts, n.nilai_uas "
                + "FROM tbnilai n "
                + "JOIN tbmahasiswa m ON n.nim = m.nim "
                + "JOIN tbmatakuliah mk ON n.kode_mk = mk.kode_mk "
                + "ORDER BY n.id_nilai DESC";

        try (Connection connection = koneksi.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Nilai nilai = new Nilai();

                nilai.setIdNilai(rs.getInt("id_nilai"));
                nilai.setNim(rs.getString("nim"));
                nilai.setNamaMahasiswa(rs.getString("nama"));
                nilai.setKodeMataKuliah(rs.getString("kode_mk"));
                nilai.setNamaMataKuliah(rs.getString("nama_mk"));
                nilai.setNilaiTugas(rs.getInt("nilai_tugas"));
                nilai.setNilaiUts(rs.getInt("nilai_uts"));
                nilai.setNilaiUas(rs.getInt("nilai_uas"));

                double akhir = hitungNilaiAkhir(
                        nilai.getNilaiTugas(),
                        nilai.getNilaiUts(),
                        nilai.getNilaiUas()
                );

                nilai.nilaiAkhir = akhir;
                nilai.grade = hitungGrade(akhir);

                daftar.add(nilai);
            }

        } catch (Exception ex) {
            pesan = "Kesalahan tampil nilai: " + ex;
        }

        return daftar;
    }
}