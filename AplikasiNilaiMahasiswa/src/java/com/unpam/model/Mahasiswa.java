package com.unpam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Mahasiswa {
    private String nim;
    private String nama;
    private int semester;
    private String kelas;
    private String password;
    private String pesan;

    private final Koneksi koneksi = new Koneksi();

    public Mahasiswa() {
    }

    public Mahasiswa(String nim, String nama, int semester, String kelas, String password) {
        this.nim = nim;
        this.nama = nama;
        this.semester = semester;
        this.kelas = kelas;
        this.password = password;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPesan() {
        return pesan;
    }

    public boolean simpan() {
        boolean berhasil = false;

        Connection connection = koneksi.getConnection();

        if (connection != null) {
            try {
                String sql = "INSERT INTO tbmahasiswa(nim, nama, semester, kelas, password) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setString(1, nim);
                ps.setString(2, nama);
                ps.setInt(3, semester);
                ps.setString(4, kelas);
                ps.setString(5, password);

                int hasil = ps.executeUpdate();

                if (hasil > 0) {
                    berhasil = true;
                } else {
                    pesan = "Data mahasiswa gagal disimpan";
                }

                ps.close();
                connection.close();

            } catch (Exception ex) {
                pesan = "Kesalahan simpan data: " + ex;
            }
        } else {
            pesan = koneksi.getPesanKesalahan();
        }

        return berhasil;
    }

    public boolean update() {
        boolean berhasil = false;

        Connection connection = koneksi.getConnection();

        if (connection != null) {
            try {
                String sql = "UPDATE tbmahasiswa SET nama=?, semester=?, kelas=?, password=? WHERE nim=?";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setString(1, nama);
                ps.setInt(2, semester);
                ps.setString(3, kelas);
                ps.setString(4, password);
                ps.setString(5, nim);

                int hasil = ps.executeUpdate();

                if (hasil > 0) {
                    berhasil = true;
                } else {
                    pesan = "Data mahasiswa gagal diupdate";
                }

                ps.close();
                connection.close();

            } catch (Exception ex) {
                pesan = "Kesalahan update data: " + ex;
            }
        } else {
            pesan = koneksi.getPesanKesalahan();
        }

        return berhasil;
    }

    public boolean hapus(String nim) {
        boolean berhasil = false;

        Connection connection = koneksi.getConnection();

        if (connection != null) {
            try {
                String sql = "DELETE FROM tbmahasiswa WHERE nim=?";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setString(1, nim);

                int hasil = ps.executeUpdate();

                if (hasil > 0) {
                    berhasil = true;
                } else {
                    pesan = "Data mahasiswa gagal dihapus";
                }

                ps.close();
                connection.close();

            } catch (Exception ex) {
                pesan = "Kesalahan hapus data: " + ex;
            }
        } else {
            pesan = koneksi.getPesanKesalahan();
        }

        return berhasil;
    }

    public Mahasiswa cari(String nim) {
        Mahasiswa mahasiswa = null;

        Connection connection = koneksi.getConnection();

        if (connection != null) {
            try {
                String sql = "SELECT * FROM tbmahasiswa WHERE nim=?";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setString(1, nim);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    mahasiswa = new Mahasiswa();
                    mahasiswa.setNim(rs.getString("nim"));
                    mahasiswa.setNama(rs.getString("nama"));
                    mahasiswa.setSemester(rs.getInt("semester"));
                    mahasiswa.setKelas(rs.getString("kelas"));
                    mahasiswa.setPassword(rs.getString("password"));
                }

                rs.close();
                ps.close();
                connection.close();

            } catch (Exception ex) {
                pesan = "Kesalahan cari data: " + ex;
            }
        } else {
            pesan = koneksi.getPesanKesalahan();
        }

        return mahasiswa;
    }

    public List<Mahasiswa> tampilSemua() {
        List<Mahasiswa> daftar = new ArrayList<>();

        Connection connection = koneksi.getConnection();

        if (connection != null) {
            try {
                String sql = "SELECT * FROM tbmahasiswa ORDER BY nim ASC";
                PreparedStatement ps = connection.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Mahasiswa mahasiswa = new Mahasiswa();

                    mahasiswa.setNim(rs.getString("nim"));
                    mahasiswa.setNama(rs.getString("nama"));
                    mahasiswa.setSemester(rs.getInt("semester"));
                    mahasiswa.setKelas(rs.getString("kelas"));
                    mahasiswa.setPassword(rs.getString("password"));

                    daftar.add(mahasiswa);
                }

                rs.close();
                ps.close();
                connection.close();

            } catch (Exception ex) {
                pesan = "Kesalahan tampil data: " + ex;
            }
        } else {
            pesan = koneksi.getPesanKesalahan();
        }

        return daftar;
    }
}