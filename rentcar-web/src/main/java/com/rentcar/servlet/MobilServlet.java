package com.rentcar.servlet;

import com.rentcar.model.Mobil;
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

@WebServlet("/mobil")
public class MobilServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Mobil> listMobil = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM mobil";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Mobil m = new Mobil();
                m.setId(rs.getInt("id"));
                m.setPlatNomor(rs.getString("plat_nomor"));
                m.setMerk(rs.getString("merk"));
                m.setModel(rs.getString("model"));
                m.setTahun(rs.getInt("tahun"));
                m.setHargaSewaPerHari(rs.getDouble("harga_sewa_per_hari"));
                m.setStatus(rs.getString("status"));
                listMobil.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("listMobil", listMobil);
        request.getRequestDispatcher("mobil.jsp").forward(request, response);
    }
}