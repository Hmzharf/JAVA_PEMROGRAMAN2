package com.unpam.view;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MainForm extends HttpServlet {

    public void tampilkan(HttpServletRequest request, HttpServletResponse response, String konten)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);

        String menu = "<br><b>Master Data</b><br>"
                + "<a href='MahasiswaController'>Mahasiswa</a><br>"
                + "<a href='MataKuliahController'>Mata Kuliah</a><br><br>"
                + "<b>Transaksi</b><br>"
                + "<a href='#'>Nilai</a><br><br>"
                + "<b>Laporan</b><br>"
                + "<a href='#'>Nilai</a><br><br>"
                + "<a href='LoginController'>Login</a><br><br>";

        String topMenu = "<nav><ul>"
                + "<li><a href='MainForm'>Home</a></li>"
                + "<li><a href='#'>Master Data</a></li>"
                + "<li><a href='MahasiswaController'>Mahasiswa</a></li>"
                + "<li><a href='MataKuliahController'>Mata Kuliah</a></li>"
                + "<li><a href='LoginController'>Login</a></li>"
                + "</ul></nav>";

        if (konten == null || konten.equals("")) {
            konten = "<h2>Selamat Datang di Aplikasi Informasi Nilai Mahasiswa</h2>";
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Informasi Nilai Mahasiswa</title>");
            out.println("<style>");
            out.println("body { font-family: Arial; background-color: #808080; }");
            out.println("table { background-color: #eeeeee; }");
            out.println("a { text-decoration: none; color: blue; }");
            out.println("input { margin: 5px; padding: 5px; }");
            out.println("button { padding: 7px 15px; }");
            out.println("nav ul { list-style: none; padding: 0; }");
            out.println("nav li { display: inline-block; margin-right: 15px; }");
            out.println("</style>");
            out.println("</head>");

            out.println("<body>");
            out.println("<center>");
            out.println("<table width='80%'>");

            out.println("<tr>");
            out.println("<td colspan='2' align='center'>");
            out.println("<br>");
            out.println("<h2>Informasi Nilai Mahasiswa</h2>");
            out.println("<h1>UNIVERSITAS PAMULANG</h1>");
            out.println("<h4>Jl. Surya Kencana No. 1 Pamulang, Tangerang Selatan, Banten</h4>");
            out.println("<br>");
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr height='400'>");

            out.println("<td width='200' align='center' valign='top' bgcolor='#eeffee'>");
            out.println(menu);
            out.println("</td>");

            out.println("<td align='center' valign='top' bgcolor='#ffffff'>");
            out.println(topMenu);
            out.println("<br>");
            out.println(konten);
            out.println("</td>");

            out.println("</tr>");

            out.println("<tr>");
            out.println("<td colspan='2' align='center' bgcolor='#eeeeff'>");
            out.println("<small>");
            out.println("Copyright &copy; 2014 Universitas Pamulang<br>");
            out.println("Jl. Surya Kencana No. 1 Pamulang, Tangerang Selatan, Banten<br>");
            out.println("</small>");
            out.println("</td>");
            out.println("</tr>");

            out.println("</table>");
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tampilkan(request, response, "");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tampilkan(request, response, "");
    }
}