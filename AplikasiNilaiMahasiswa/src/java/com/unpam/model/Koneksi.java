package com.unpam.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE = "jdbc:mysql://localhost:3306/dbaplikasipenilaianmahasiswa";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private String pesanKesalahan = "";

    public String getPesanKesalahan() {
        return pesanKesalahan;
    }

    public Connection getConnection() {
        Connection connection = null;
        pesanKesalahan = "";

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            pesanKesalahan = "Driver MySQL tidak ditemukan: " + ex;
        } catch (SQLException ex) {
            pesanKesalahan = "Koneksi database gagal: " + ex;
        }

        return connection;
    }
}