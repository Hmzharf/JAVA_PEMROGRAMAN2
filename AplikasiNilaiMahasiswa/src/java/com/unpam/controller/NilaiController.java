package com.unpam.controller;

import com.unpam.model.Mahasiswa;
import com.unpam.model.MataKuliah;
import com.unpam.model.Nilai;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "NilaiController", urlPatterns = {"/NilaiController"})
public class NilaiController extends HttpServlet {

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

    private void kirimDataKeJsp(HttpServletRequest request)
            throws ServletException, IOException {

        Nilai nilai = new Nilai();
        Mahasiswa mahasiswa = new Mahasiswa();
        MataKuliah mataKuliah = new MataKuliah();

        request.setAttribute("daftarNilai", nilai.tampilSemua());
        request.setAttribute("daftarMahasiswa", mahasiswa.tampilSemua());
        request.setAttribute("daftarMataKuliah", mataKuliah.tampilSemua());
    }

    private void tampil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        kirimDataKeJsp(request);
        request.getRequestDispatcher("nilai.jsp").forward(request, response);
    }

    private void simpan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Nilai nilai = new Nilai();
        nilai.setNim(request.getParameter("nim"));
        nilai.setKodeMataKuliah(request.getParameter("kodeMataKuliah"));
        nilai.setNilaiTugas(Integer.parseInt(request.getParameter("nilaiTugas")));
        nilai.setNilaiUts(Integer.parseInt(request.getParameter("nilaiUts")));
        nilai.setNilaiUas(Integer.parseInt(request.getParameter("nilaiUas")));

        nilai.simpan();

        response.sendRedirect("NilaiController");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idNilai = Integer.parseInt(request.getParameter("idNilai"));

        Nilai model = new Nilai();
        Nilai nilaiEdit = model.cari(idNilai);

        kirimDataKeJsp(request);
        request.setAttribute("nilaiEdit", nilaiEdit);

        request.getRequestDispatcher("nilai.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Nilai nilai = new Nilai();
        nilai.setIdNilai(Integer.parseInt(request.getParameter("idNilai")));
        nilai.setNim(request.getParameter("nim"));
        nilai.setKodeMataKuliah(request.getParameter("kodeMataKuliah"));
        nilai.setNilaiTugas(Integer.parseInt(request.getParameter("nilaiTugas")));
        nilai.setNilaiUts(Integer.parseInt(request.getParameter("nilaiUts")));
        nilai.setNilaiUas(Integer.parseInt(request.getParameter("nilaiUas")));

        nilai.update();

        response.sendRedirect("NilaiController");
    }

    private void hapus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idNilai = Integer.parseInt(request.getParameter("idNilai"));

        Nilai nilai = new Nilai();
        nilai.hapus(idNilai);

        response.sendRedirect("NilaiController");
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