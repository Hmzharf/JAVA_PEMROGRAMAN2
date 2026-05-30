<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unpam.model.Mahasiswa" %>

<%
    List<Mahasiswa> daftarMahasiswa = (List<Mahasiswa>) request.getAttribute("daftarMahasiswa");
    Mahasiswa mahasiswaEdit = (Mahasiswa) request.getAttribute("mahasiswaEdit");

    String aksi = "simpan";
    String nim = "";
    String nama = "";
    String semester = "";
    String kelas = "";
    String password = "";
    String readonly = "";

    if (mahasiswaEdit != null) {
        aksi = "update";
        nim = mahasiswaEdit.getNim();
        nama = mahasiswaEdit.getNama();
        semester = String.valueOf(mahasiswaEdit.getSemester());
        kelas = mahasiswaEdit.getKelas();
        password = mahasiswaEdit.getPassword();
        readonly = "readonly";
    }
%>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Data Mahasiswa</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>

<div class="wrapper">

    <%@ include file="layout/header.jsp" %>

    <div class="main">

        <%@ include file="layout/sidebar.jsp" %>

        <div class="content">

            <h2 class="page-title">Data Mahasiswa</h2>

            <div class="form-box">
                <form action="MahasiswaController" method="post">
                    <input type="hidden" name="aksi" value="<%= aksi %>">

                    <div class="form-group">
                        <label>NIM</label>
                        <input type="text" name="nim" value="<%= nim %>" <%= readonly %> required>
                    </div>

                    <div class="form-group">
                        <label>Nama Mahasiswa</label>
                        <input type="text" name="nama" value="<%= nama %>" required>
                    </div>

                    <div class="form-group">
                        <label>Semester</label>
                        <select name="semester" required>
                            <option value="">-- Pilih Semester --</option>
                            <% for (int i = 1; i <= 8; i++) { %>
                                <option value="<%= i %>" <%= semester.equals(String.valueOf(i)) ? "selected" : "" %>>
                                    Semester <%= i %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Kelas</label>
                        <input type="text" name="kelas" value="<%= kelas %>" required>
                    </div>

                    <div class="form-group">
                        <label>Password</label>
                        <input type="text" name="password" value="<%= password %>" required>
                    </div>

                    <button type="submit" class="btn"><%= mahasiswaEdit != null ? "Update" : "Simpan" %></button>
                    <a href="MahasiswaController" class="btn btn-danger">Batal</a>
                </form>
            </div>

            <div class="table-box">
                <table class="data-table">
                    <tr>
                        <th>No</th>
                        <th>NIM</th>
                        <th>Nama</th>
                        <th>Semester</th>
                        <th>Kelas</th>
                        <th>Aksi</th>
                    </tr>

                    <%
                        if (daftarMahasiswa != null && !daftarMahasiswa.isEmpty()) {
                            int no = 1;
                            for (Mahasiswa mhs : daftarMahasiswa) {
                    %>
                    <tr>
                        <td><%= no++ %></td>
                        <td><%= mhs.getNim() %></td>
                        <td><%= mhs.getNama() %></td>
                        <td><%= mhs.getSemester() %></td>
                        <td><%= mhs.getKelas() %></td>
                        <td>
                            <a href="MahasiswaController?aksi=edit&nim=<%= mhs.getNim() %>" class="btn">Edit</a>
                            <a href="MahasiswaController?aksi=hapus&nim=<%= mhs.getNim() %>"
                               class="btn btn-danger"
                               onclick="return confirm('Yakin hapus data ini?')">Hapus</a>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="6">Data mahasiswa belum tersedia</td>
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