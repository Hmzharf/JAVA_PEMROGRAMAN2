package com.rentcar.servlet;

import com.rentcar.model.Customer;
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

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Customer> listCustomer = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM customer";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setNama(rs.getString("nama"));
                c.setAlamat(rs.getString("alamat"));
                c.setNoTelp(rs.getString("no_telp"));
                c.setKtp(rs.getString("ktp"));
                c.setEmail(rs.getString("email"));
                listCustomer.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("listCustomer", listCustomer);
        request.getRequestDispatcher("customer.jsp").forward(request, response);
    }
}