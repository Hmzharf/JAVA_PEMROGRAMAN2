package com.unpam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MataKuliah {
    private String kodeMataKuliah;
    private String namaMataKuliah;
    private int jumlahSks;
    private String pesan;

    private final Koneksi koneksi = new Koneksi();

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

    public int getJumlahSks() {
        return jumlahSks;
    }

    public void setJumlahSks(int jumlahSks) {
        this.jumlahSks = jumlahSks;
    }

    public String getPesan() {
        return pesan;
    }

    public boolean simpan() {
        boolean berhasil = false;
        String sql = "INSERT INTO tbmatakuliah(kode_mk, nama_mk, sks) VALUES (?, ?, ?)";

        try (Connection connection = koneksi.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, kodeMataKuliah);
            ps.setString(2, namaMataKuliah);
            ps.setInt(3, jumlahSks);

            berhasil = ps.executeUpdate() > 0;

        } catch (Exception ex) {
            pesan = "Kesalahan simpan mata kuliah: " + ex;
        }

        return berhasil;
    }

    public boolean update() {
        boolean berhasil = false;
        String sql = "UPDATE tbmatakuliah SET nama_mk=?, sks=? WHERE kode_mk=?";

        try (Connection connection = koneksi.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, namaMataKuliah);
            ps.setInt(2, jumlahSks);
            ps.setString(3, kodeMataKuliah);

            berhasil = ps.executeUpdate() > 0;

        } catch (Exception ex) {
            pesan = "Kesalahan update mata kuliah: " + ex;
        }

        return berhasil;
    }

    public boolean hapus(String kodeMataKuliah) {
        boolean berhasil = false;
        String sql = "DELETE FROM tbmatakuliah WHERE kode_mk=?";

        try (Connection connection = koneksi.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, kodeMataKuliah);
            berhasil = ps.executeUpdate() > 0;

        } catch (Exception ex) {
            pesan = "Kesalahan hapus mata kuliah: " + ex;
        }

        return berhasil;
    }

    public MataKuliah cari(String kodeMataKuliah) {
        MataKuliah mataKuliah = null;
        String sql = "SELECT * FROM tbmatakuliah WHERE kode_mk=?";

        try (Connection connection = koneksi.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, kodeMataKuliah);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                mataKuliah = new MataKuliah();
                mataKuliah.setKodeMataKuliah(rs.getString("kode_mk"));
                mataKuliah.setNamaMataKuliah(rs.getString("nama_mk"));
                mataKuliah.setJumlahSks(rs.getInt("sks"));
            }

            rs.close();

        } catch (Exception ex) {
            pesan = "Kesalahan cari mata kuliah: " + ex;
        }

        return mataKuliah;
    }

    public List<MataKuliah> tampilSemua() {
        List<MataKuliah> daftar = new ArrayList<>();
        String sql = "SELECT * FROM tbmatakuliah ORDER BY kode_mk ASC";

        try (Connection connection = koneksi.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MataKuliah mataKuliah = new MataKuliah();
                mataKuliah.setKodeMataKuliah(rs.getString("kode_mk"));
                mataKuliah.setNamaMataKuliah(rs.getString("nama_mk"));
                mataKuliah.setJumlahSks(rs.getInt("sks"));
                daftar.add(mataKuliah);
            }

        } catch (Exception ex) {
            pesan = "Kesalahan tampil mata kuliah: " + ex;
        }

        return daftar;
    }
}