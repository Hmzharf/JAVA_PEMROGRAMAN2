package com.unpam.controller;

import com.unpam.model.MataKuliah;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "MataKuliahController", urlPatterns = {"/MataKuliahController"})
public class MataKuliahController extends HttpServlet {

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

        MataKuliah mataKuliah = new MataKuliah();
        List<MataKuliah> daftarMataKuliah = mataKuliah.tampilSemua();

        request.setAttribute("daftarMataKuliah", daftarMataKuliah);
        request.getRequestDispatcher("matakuliah.jsp").forward(request, response);
    }

    private void simpan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MataKuliah mataKuliah = new MataKuliah();
        mataKuliah.setKodeMataKuliah(request.getParameter("kodeMataKuliah"));
        mataKuliah.setNamaMataKuliah(request.getParameter("namaMataKuliah"));
        mataKuliah.setJumlahSks(Integer.parseInt(request.getParameter("jumlahSks")));

        mataKuliah.simpan();

        response.sendRedirect("MataKuliahController");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String kode = request.getParameter("kodeMataKuliah");

        MataKuliah model = new MataKuliah();
        MataKuliah mataKuliahEdit = model.cari(kode);
        List<MataKuliah> daftarMataKuliah = model.tampilSemua();

        request.setAttribute("mataKuliahEdit", mataKuliahEdit);
        request.setAttribute("daftarMataKuliah", daftarMataKuliah);

        request.getRequestDispatcher("matakuliah.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MataKuliah mataKuliah = new MataKuliah();
        mataKuliah.setKodeMataKuliah(request.getParameter("kodeMataKuliah"));
        mataKuliah.setNamaMataKuliah(request.getParameter("namaMataKuliah"));
        mataKuliah.setJumlahSks(Integer.parseInt(request.getParameter("jumlahSks")));

        mataKuliah.update();

        response.sendRedirect("MataKuliahController");
    }

    private void hapus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String kode = request.getParameter("kodeMataKuliah");

        MataKuliah mataKuliah = new MataKuliah();
        mataKuliah.hapus(kode);

        response.sendRedirect("MataKuliahController");
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