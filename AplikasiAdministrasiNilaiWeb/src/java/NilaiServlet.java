package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "NilaiServlet", urlPatterns = {"/NilaiServlet"})
public class NilaiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            Connection con = DBConnection.getConnection();

            if ("add".equals(action)) {

                String mahasiswaId = request.getParameter("mahasiswa_id");
                String mataKuliahId = request.getParameter("mata_kuliah_id");
                String nilai = request.getParameter("nilai");

                if (mahasiswaId == null || mahasiswaId.equals("") ||
                    mataKuliahId == null || mataKuliahId.equals("") ||
                    nilai == null || nilai.equals("")) {

                    response.sendRedirect("nilai.jsp?error=kosong");
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO nilai(mahasiswa_id, mata_kuliah_id, nilai) VALUES (?, ?, ?)"
                );

                ps.setInt(1, Integer.parseInt(mahasiswaId));
                ps.setInt(2, Integer.parseInt(mataKuliahId));
                ps.setDouble(3, Double.parseDouble(nilai));
                ps.executeUpdate();

                response.sendRedirect("nilai.jsp?success=add");
                return;

            } else if ("edit".equals(action)) {

                PreparedStatement ps = con.prepareStatement(
                    "UPDATE nilai SET mahasiswa_id=?, mata_kuliah_id=?, nilai=? WHERE id=?"
                );

                ps.setInt(1, Integer.parseInt(request.getParameter("mahasiswa_id")));
                ps.setInt(2, Integer.parseInt(request.getParameter("mata_kuliah_id")));
                ps.setDouble(3, Double.parseDouble(request.getParameter("nilai")));
                ps.setInt(4, Integer.parseInt(request.getParameter("id")));
                ps.executeUpdate();

                response.sendRedirect("nilai.jsp?success=edit");
                return;

            } else if ("delete".equals(action)) {

                PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM nilai WHERE id=?"
                );

                ps.setInt(1, Integer.parseInt(request.getParameter("id")));
                ps.executeUpdate();

                response.sendRedirect("nilai.jsp?success=delete");
                return;

            } else {
                response.sendRedirect("nilai.jsp?error=aksi");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h2>Error NilaiServlet</h2>");
            response.getWriter().println("<p>" + e.getMessage() + "</p>");
            response.getWriter().println("<a href='nilai.jsp'>Kembali ke Halaman Nilai</a>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("nilai.jsp");
    }
}