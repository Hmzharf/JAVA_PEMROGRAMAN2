<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="header">
    <h2>Informasi Nilai Mahasiswa</h2>
    <h1>UNIVERSITAS PAMULANG</h1>
    <p>Jl. Surya Kencana No. 1 Pamulang, Tangerang Selatan, Banten</p>

    <%
        if (session.getAttribute("namaUser") != null) {
    %>
        <p>Login sebagai: <b><%= session.getAttribute("namaUser") %></b></p>
    <%
        }
    %>
</div>

<div class="navbar">
    <ul>
        <li><a href="index.jsp">Home</a></li>
        <li><a href="MahasiswaController">Mahasiswa</a></li>
        <li><a href="MataKuliahController">Mata Kuliah</a></li>
        <li><a href="NilaiController">Nilai</a></li>
        <li><a href="LaporanNilaiController">Laporan Nilai</a></li>
        <li><a href="LoginController">Login</a></li>
        <li><a href="LogoutController">Logout</a></li>
    </ul>
</div>