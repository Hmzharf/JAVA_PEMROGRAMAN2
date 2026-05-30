package com.unpam.controller;

import com.unpam.model.Nilai;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LaporanNilaiController", urlPatterns = {"/LaporanNilaiController"})
public class LaporanNilaiController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Nilai nilai = new Nilai();

        request.setAttribute("daftarNilai", nilai.tampilSemua());
        request.getRequestDispatcher("laporan-nilai.jsp").forward(request, response);
    }
}