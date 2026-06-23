package com.rentcar.servlet;

import com.rentcar.model.Transaksi;
import com.rentcar.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/transaksi")
public class TransaksiServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Transaksi> listTransaksi = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM transaksi ORDER BY created_at DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Transaksi t = new Transaksi();
                t.setId(rs.getInt("id"));
                t.setMobilId(rs.getInt("mobil_id"));
                t.setCustomerId(rs.getInt("customer_id"));
                t.setTanggalSewa(rs.getDate("tanggal_sewa"));
                t.setTanggalKembali(rs.getDate("tanggal_kembali"));
                t.setTotalHarga(rs.getDouble("total_harga"));
                t.setStatus(rs.getString("status"));
                listTransaksi.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("listTransaksi", listTransaksi);
        request.getRequestDispatcher("transaksi.jsp").forward(request, response);
    }
}