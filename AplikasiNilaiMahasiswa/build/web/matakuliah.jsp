<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unpam.model.MataKuliah" %>

<%
    List<MataKuliah> daftarMataKuliah = (List<MataKuliah>) request.getAttribute("daftarMataKuliah");
    MataKuliah mataKuliahEdit = (MataKuliah) request.getAttribute("mataKuliahEdit");

    String aksi = "simpan";
    String kode = "";
    String nama = "";
    String sks = "";
    String readonly = "";

    if (mataKuliahEdit != null) {
        aksi = "update";
        kode = mataKuliahEdit.getKodeMataKuliah();
        nama = mataKuliahEdit.getNamaMataKuliah();
        sks = String.valueOf(mataKuliahEdit.getJumlahSks());
        readonly = "readonly";
    }
%>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Data Mata Kuliah</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>

<div class="wrapper">

    <%@ include file="layout/header.jsp" %>

    <div class="main">

        <%@ include file="layout/sidebar.jsp" %>

        <div class="content">

            <h2 class="page-title">Data Mata Kuliah</h2>

            <div class="form-box">
                <form action="MataKuliahController" method="post">
                    <input type="hidden" name="aksi" value="<%= aksi %>">

                    <div class="form-group">
                        <label>Kode Mata Kuliah</label>
                        <input type="text" name="kodeMataKuliah" value="<%= kode %>" <%= readonly %> required>
                    </div>

                    <div class="form-group">
                        <label>Nama Mata Kuliah</label>
                        <input type="text" name="namaMataKuliah" value="<%= nama %>" required>
                    </div>

                    <div class="form-group">
                        <label>Jumlah SKS</label>
                        <select name="jumlahSks" required>
                            <option value="">-- Pilih SKS --</option>
                            <% for (int i = 1; i <= 6; i++) { %>
                                <option value="<%= i %>" <%= sks.equals(String.valueOf(i)) ? "selected" : "" %>>
                                    <%= i %> SKS
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <button type="submit" class="btn"><%= mataKuliahEdit != null ? "Update" : "Simpan" %></button>
                    <a href="MataKuliahController" class="btn btn-danger">Batal</a>
                </form>
            </div>

            <div class="table-box">
                <table class="data-table">
                    <tr>
                        <th>No</th>
                        <th>Kode</th>
                        <th>Nama Mata Kuliah</th>
                        <th>SKS</th>
                        <th>Aksi</th>
                    </tr>

                    <%
                        if (daftarMataKuliah != null && !daftarMataKuliah.isEmpty()) {
                            int no = 1;
                            for (MataKuliah mk : daftarMataKuliah) {
                    %>
                    <tr>
                        <td><%= no++ %></td>
                        <td><%= mk.getKodeMataKuliah() %></td>
                        <td><%= mk.getNamaMataKuliah() %></td>
                        <td><%= mk.getJumlahSks() %></td>
                        <td>
                            <a href="MataKuliahController?aksi=edit&kodeMataKuliah=<%= mk.getKodeMataKuliah() %>" class="btn">Edit</a>
                            <a href="MataKuliahController?aksi=hapus&kodeMataKuliah=<%= mk.getKodeMataKuliah() %>"
                               class="btn btn-danger"
                               onclick="return confirm('Yakin hapus data ini?')">Hapus</a>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="5">Data mata kuliah belum tersedia</td>
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