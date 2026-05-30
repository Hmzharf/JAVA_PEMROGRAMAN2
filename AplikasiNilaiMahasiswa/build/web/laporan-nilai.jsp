<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unpam.model.Nilai" %>

<%
    List<Nilai> daftarNilai = (List<Nilai>) request.getAttribute("daftarNilai");
%>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Laporan Nilai</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>

<div class="wrapper">

    <%@ include file="layout/header.jsp" %>

    <div class="main">

        <%@ include file="layout/sidebar.jsp" %>

        <div class="content">

            <h2 class="page-title">Laporan Nilai Mahasiswa</h2>

            <div class="table-box">
                <table class="data-table">
                    <tr>
                        <th>No</th>
                        <th>NIM</th>
                        <th>Nama Mahasiswa</th>
                        <th>Kode MK</th>
                        <th>Mata Kuliah</th>
                        <th>Tugas</th>
                        <th>UTS</th>
                        <th>UAS</th>
                        <th>Nilai Akhir</th>
                        <th>Grade</th>
                    </tr>

                    <%
                        if (daftarNilai != null && !daftarNilai.isEmpty()) {
                            int no = 1;
                            for (Nilai n : daftarNilai) {
                    %>
                    <tr>
                        <td><%= no++ %></td>
                        <td><%= n.getNim() %></td>
                        <td><%= n.getNamaMahasiswa() %></td>
                        <td><%= n.getKodeMataKuliah() %></td>
                        <td><%= n.getNamaMataKuliah() %></td>
                        <td><%= n.getNilaiTugas() %></td>
                        <td><%= n.getNilaiUts() %></td>
                        <td><%= n.getNilaiUas() %></td>
                        <td><%= String.format("%.2f", n.getNilaiAkhir()) %></td>
                        <td><%= n.getGrade() %></td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="10">Data laporan nilai belum tersedia</td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>

        </div>
    </div>

    <%@ include file="layout/footer.jsp" %>

</div>

</body>
</html>