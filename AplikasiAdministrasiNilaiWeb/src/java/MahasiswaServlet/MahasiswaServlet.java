package controller;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name="MahasiswaServlet", urlPatterns={"/MahasiswaServlet"})
public class MahasiswaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            Connection con = DBConnection.getConnection();
            if ("add".equals(action)) {
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO mahasiswa(nama,nim,jurusan) VALUES(?,?,?)");
                ps.setString(1, req.getParameter("nama"));
                ps.setString(2, req.getParameter("nim"));
                ps.setString(3, req.getParameter("jurusan"));
                ps.executeUpdate();
            } else if ("edit".equals(action)) {
                PreparedStatement ps = con.prepareStatement(
                    "UPDATE mahasiswa SET nama=?, nim=?, jurusan=? WHERE id=?");
                ps.setString(1, req.getParameter("nama"));
                ps.setString(2, req.getParameter("nim"));
                ps.setString(3, req.getParameter("jurusan"));
                ps.setInt(4, Integer.parseInt(req.getParameter("id")));
                ps.executeUpdate();
            } else if ("delete".equals(action)) {
                PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM mahasiswa WHERE id=?");
                ps.setInt(1, Integer.parseInt(req.getParameter("id")));
                ps.executeUpdate();
            }
            res.sendRedirect("mahasiswa.jsp");
        } catch(Exception e) {
            e.printStackTrace();
            res.getWriter().println("Error: " + e.getMessage());
        }
    }
}