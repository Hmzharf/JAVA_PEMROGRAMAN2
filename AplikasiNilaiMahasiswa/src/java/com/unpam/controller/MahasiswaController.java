package com.unpam.controller;

import com.unpam.model.Mahasiswa;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "MahasiswaController", urlPatterns = {"/MahasiswaController"})
public class MahasiswaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String aksi = request.getParameter("aksi");

        if (aksi == null) {
            aksi = "list";
        }

        if (aksi.equals("simpan")) {
            simpan(request, response);
        } else if (aksi.equals("edit")) {
            edit(request, response);
        } else if (aksi.equals("update")) {
            update(request, response);
        } else if (aksi.equals("hapus")) {
            hapus(request, response);
        } else {
            tampil(request, response);
        }
    }

    private void tampil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Mahasiswa mahasiswa = new Mahasiswa();
        List<Mahasiswa> daftarMahasiswa = mahasiswa.tampilSemua();

        request.setAttribute("daftarMahasiswa", daftarMahasiswa);
        request.getRequestDispatcher("mahasiswa.jsp").forward(request, response);
    }

    private void simpan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNim(request.getParameter("nim"));
        mahasiswa.setNama(request.getParameter("nama"));
        mahasiswa.setSemester(Integer.parseInt(request.getParameter("semester")));
        mahasiswa.setKelas(request.getParameter("kelas"));
        mahasiswa.setPassword(request.getParameter("password"));

        mahasiswa.simpan();

        response.sendRedirect("MahasiswaController");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nim = request.getParameter("nim");

        Mahasiswa model = new Mahasiswa();
        Mahasiswa mahasiswaEdit = model.cari(nim);
        List<Mahasiswa> daftarMahasiswa = model.tampilSemua();

        request.setAttribute("mahasiswaEdit", mahasiswaEdit);
        request.setAttribute("daftarMahasiswa", daftarMahasiswa);

        request.getRequestDispatcher("mahasiswa.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNim(request.getParameter("nim"));
        mahasiswa.setNama(request.getParameter("nama"));
        mahasiswa.setSemester(Integer.parseInt(request.getParameter("semester")));
        mahasiswa.setKelas(request.getParameter("kelas"));
        mahasiswa.setPassword(request.getParameter("password"));

        mahasiswa.update();

        response.sendRedirect("MahasiswaController");
    }

    private void hapus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nim = request.getParameter("nim");

        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.hapus(nim);

        response.sendRedirect("MahasiswaController");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}