<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Beranda</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>

<div class="wrapper">

    <%@ include file="layout/header.jsp" %>

    <div class="main">

        <%@ include file="layout/sidebar.jsp" %>

        <div class="content">

            <div class="welcome-box">
                <h2>Selamat Datang</h2>
                <p>
                    Aplikasi ini digunakan untuk mengelola data mahasiswa, data mata kuliah,
                    input nilai, serta laporan nilai mahasiswa. Semua data pada aplikasi ini
                    terhubung langsung dengan database MySQL.
                </p>
            </div>

            <div class="card-grid">
                <div class="card">
                    <h3>Data Mahasiswa</h3>
                    <p>Tambah, ubah, hapus, dan tampilkan data mahasiswa.</p>
                    <a href="MahasiswaController" class="btn">Buka</a>
                </div>

                <div class="card">
                    <h3>Data Mata Kuliah</h3>
                    <p>Tambah, ubah, hapus, dan tampilkan data mata kuliah.</p>
                    <a href="MataKuliahController" class="btn">Buka</a>
                </div>

                <div class="card">
                    <h3>Input Nilai</h3>
                    <p>Kelola nilai tugas, UTS, dan UAS mahasiswa.</p>
                    <a href="NilaiController" class="btn">Buka</a>
                </div>

                <div class="card">
                    <h3>Laporan Nilai</h3>
                    <p>Tampilkan laporan nilai akhir dan grade dari database.</p>
                    <a href="LaporanNilaiController" class="btn">Buka</a>
                </div>
            </div>

        </div>
    </div>

    <%@ include file="layout/footer.jsp" %>

</div>

</body>
</html>