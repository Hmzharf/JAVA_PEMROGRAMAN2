package controller;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name="MataKuliahServlet", urlPatterns={"/MataKuliahServlet"})
public class MataKuliahServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            Connection con = DBConnection.getConnection();

            if("add".equals(action)) {
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO mata_kuliah(kode,nama,sks) VALUES(?,?,?)"
                );
                ps.setString(1, request.getParameter("kode"));
                ps.setString(2, request.getParameter("nama"));
                ps.setInt(3, Integer.parseInt(request.getParameter("sks")));
                ps.executeUpdate();

                // Redirect setelah insert supaya browser tidak blank
                response.sendRedirect("mata_kuliah.jsp");

            } else if("edit".equals(action)) {
                PreparedStatement ps = con.prepareStatement(
                    "UPDATE mata_kuliah SET kode=?,nama=?,sks=? WHERE id=?"
                );
                ps.setString(1, request.getParameter("kode"));
                ps.setString(2, request.getParameter("nama"));
                ps.setInt(3, Integer.parseInt(request.getParameter("sks")));
                ps.setInt(4, Integer.parseInt(request.getParameter("id")));
                ps.executeUpdate();

                response.sendRedirect("mata_kuliah.jsp");

            } else if("delete".equals(action)) {
                PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM mata_kuliah WHERE id=?"
                );
                ps.setInt(1, Integer.parseInt(request.getParameter("id")));
                ps.executeUpdate();

                response.sendRedirect("mata_kuliah.jsp");
            }

        } catch(Exception e){
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}