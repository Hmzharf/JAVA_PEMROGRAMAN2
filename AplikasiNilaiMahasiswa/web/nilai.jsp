<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unpam.model.Nilai" %>
<%@ page import="com.unpam.model.Mahasiswa" %>
<%@ page import="com.unpam.model.MataKuliah" %>

<%
    List<Nilai> daftarNilai = (List<Nilai>) request.getAttribute("daftarNilai");
    List<Mahasiswa> daftarMahasiswa = (List<Mahasiswa>) request.getAttribute("daftarMahasiswa");
    List<MataKuliah> daftarMataKuliah = (List<MataKuliah>) request.getAttribute("daftarMataKuliah");

    Nilai nilaiEdit = (Nilai) request.getAttribute("nilaiEdit");

    String aksi = "simpan";
    String idNilai = "";
    String nim = "";
    String kodeMataKuliah = "";
    String nilaiTugas = "";
    String nilaiUts = "";
    String nilaiUas = "";

    if (nilaiEdit != null) {
        aksi = "update";
        idNilai = String.valueOf(nilaiEdit.getIdNilai());
        nim = nilaiEdit.getNim();
        kodeMataKuliah = nilaiEdit.getKodeMataKuliah();
        nilaiTugas = String.valueOf(nilaiEdit.getNilaiTugas());
        nilaiUts = String.valueOf(nilaiEdit.getNilaiUts());
        nilaiUas = String.valueOf(nilaiEdit.getNilaiUas());
    }
%>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Data Nilai</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>

<div class="wrapper">

    <%@ include file="layout/header.jsp" %>

    <div class="main">

        <%@ include file="layout/sidebar.jsp" %>

        <div class="content">

            <h2 class="page-title">Input Nilai Mahasiswa</h2>

            <div class="form-box">
                <form action="NilaiController" method="post">
                    <input type="hidden" name="aksi" value="<%= aksi %>">
                    <input type="hidden" name="idNilai" value="<%= idNilai %>">

                    <div class="form-group">
                        <label>Mahasiswa</label>
                        <select name="nim" required>
                            <option value="">-- Pilih Mahasiswa --</option>
                            <%
                                if (daftarMahasiswa != null) {
                                    for (Mahasiswa mhs : daftarMahasiswa) {
                            %>
                            <option value="<%= mhs.getNim() %>" <%= nim.equals(mhs.getNim()) ? "selected" : "" %>>
                                <%= mhs.getNim() %> - <%= mhs.getNama() %>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Mata Kuliah</label>
                        <select name="kodeMataKuliah" required>
                            <option value="">-- Pilih Mata Kuliah --</option>
                            <%
                                if (daftarMataKuliah != null) {
                                    for (MataKuliah mk : daftarMataKuliah) {
                            %>
                            <option value="<%= mk.getKodeMataKuliah() %>" <%= kodeMataKuliah.equals(mk.getKodeMataKuliah()) ? "selected" : "" %>>
                                <%= mk.getKodeMataKuliah() %> - <%= mk.getNamaMataKuliah() %>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Nilai Tugas</label>
                        <input type="number" name="nilaiTugas" value="<%= nilaiTugas %>" min="0" max="100" required>
                    </div>

                    <div class="form-group">
                        <label>Nilai UTS</label>
                        <input type="number" name="nilaiUts" value="<%= nilaiUts %>" min="0" max="100" required>
                    </div>

                    <div class="form-group">
                        <label>Nilai UAS</label>
                        <input type="number" name="nilaiUas" value="<%= nilaiUas %>" min="0" max="100" required>
                    </div>

                    <button type="submit" class="btn"><%= nilaiEdit != null ? "Update" : "Simpan" %></button>
                    <a href="NilaiController" class="btn btn-danger">Batal</a>
                </form>
            </div>

            <div class="table-box">
                <table class="data-table">
                    <tr>
                        <th>No</th>
                        <th>NIM</th>
                        <th>Mahasiswa</th>
                        <th>Mata Kuliah</th>
                        <th>Tugas</th>
                        <th>UTS</th>
                        <th>UAS</th>
                        <th>Nilai Akhir</th>
                        <th>Grade</th>
                        <th>Aksi</th>
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
                        <td><%= n.getNamaMataKuliah() %></td>
                        <td><%= n.getNilaiTugas() %></td>
                        <td><%= n.getNilaiUts() %></td>
                        <td><%= n.getNilaiUas() %></td>
                        <td><%= String.format("%.2f", n.getNilaiAkhir()) %></td>
                        <td><%= n.getGrade() %></td>
                        <td>
                            <a href="NilaiController?aksi=edit&idNilai=<%= n.getIdNilai() %>" class="btn">Edit</a>
                            <a href="NilaiController?aksi=hapus&idNilai=<%= n.getIdNilai() %>"
                               class="btn btn-danger"
                               onclick="return confirm('Yakin hapus nilai ini?')">Hapus</a>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="10">Data nilai belum tersedia</td>
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