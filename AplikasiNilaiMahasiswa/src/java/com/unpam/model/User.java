package com.unpam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    private String username;
    private String namaUser;
    private String level;
    private String pesan;

    private final Koneksi koneksi = new Koneksi();

    public String getUsername() {
        return username;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public String getLevel() {
        return level;
    }

    public String getPesan() {
        return pesan;
    }

    public boolean login(String username, String password) {
        boolean berhasil = false;

        String sql = "SELECT * FROM tbuser WHERE username=? AND password=?";

        try (Connection connection = koneksi.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                berhasil = true;
                this.username = rs.getString("username");
                this.namaUser = rs.getString("nama_user");
                this.level = rs.getString("level");
            } else {
                pesan = "Username atau password salah";
            }

            rs.close();

        } catch (Exception ex) {
            pesan = "Kesalahan login: " + ex;
        }

        return berhasil;
    }
}