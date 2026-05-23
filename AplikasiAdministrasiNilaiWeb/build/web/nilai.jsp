<%@ page import="java.sql.*" %>
<%@ page import="controller.DBConnection" %>

<%
String userName = (String) session.getAttribute("userName");
if (userName == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Data Nilai</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<nav>
    <ul>
        <li><a href="index.jsp">Home</a></li>
        <li><a href="mahasiswa.jsp">Mahasiswa</a></li>
        <li><a href="mata_kuliah.jsp">Mata Kuliah</a></li>
        <li><a href="nilai.jsp">Nilai</a></li>
        <li><a href="logout.jsp">Logout</a></li>
    </ul>
</nav>

<div class="container">

    <div class="card">
        <h2>Tambah Nilai</h2>

        <%
            String success = request.getParameter("success");
            String error = request.getParameter("error");

            if ("add".equals(success)) {
        %>
            <p style="color:green;">Data nilai berhasil ditambahkan.</p>
        <%
            } else if ("edit".equals(success)) {
        %>
            <p style="color:green;">Data nilai berhasil diubah.</p>
        <%
            } else if ("delete".equals(success)) {
        %>
            <p style="color:green;">Data nilai berhasil dihapus.</p>
        <%
            } else if (error != null) {
        %>
            <p style="color:red;">Terjadi kesalahan. Pastikan data mahasiswa dan mata kuliah sudah ada.</p>
        <%
            }
        %>

        <form method="post" action="NilaiServlet?action=add">

            <label>Mahasiswa</label>
            <select name="mahasiswa_id" required>
                <option value="">-- Pilih Mahasiswa --</option>
                <%
                try {
                    Connection con = DBConnection.getConnection();
                    Statement stMhs = con.createStatement();
                    ResultSet rsMhs = stMhs.executeQuery("SELECT id, nama, nim FROM mahasiswa ORDER BY id ASC");

                    while (rsMhs.next()) {
                %>
                    <option value="<%= rsMhs.getInt("id") %>">
                        <%= rsMhs.getInt("id") %> - <%= rsMhs.getString("nama") %> / <%= rsMhs.getString("nim") %>
                    </option>
                <%
                    }
                } catch (Exception e) {
                    out.println("<option>Error mahasiswa: " + e.getMessage() + "</option>");
                }
                %>
            </select>

            <label>Mata Kuliah</label>
            <select name="mata_kuliah_id" required>
                <option value="">-- Pilih Mata Kuliah --</option>
                <%
                try {
                    Connection con = DBConnection.getConnection();
                    Statement stMk = con.createStatement();
                    ResultSet rsMk = stMk.executeQuery("SELECT id, nama, sks FROM mata_kuliah ORDER BY id ASC");

                    while (rsMk.next()) {
                %>
                    <option value="<%= rsMk.getInt("id") %>">
                        <%= rsMk.getInt("id") %> - <%= rsMk.getString("nama") %> / <%= rsMk.getInt("sks") %> SKS
                    </option>
                <%
                    }
                } catch (Exception e) {
                    out.println("<option>Error mata kuliah: " + e.getMessage() + "</option>");
                }
                %>
            </select>

            <label>Nilai</label>
            <input type="number" step="0.01" name="nilai" placeholder="Contoh: 85" required>

            <input type="submit" value="Tambah Nilai">
        </form>
    </div>

    <div class="card">
        <h3>Daftar Nilai</h3>

        <table>
            <tr>
                <th>ID</th>
                <th>Nama Mahasiswa</th>
                <th>NIM</th>
                <th>Mata Kuliah</th>
                <th>SKS</th>
                <th>Nilai</th>
                <th>Aksi</th>
            </tr>

            <%
            try {
                Connection con = DBConnection.getConnection();

                String sql =
                    "SELECT n.id, n.nilai, " +
                    "m.nama AS nama_mahasiswa, m.nim, " +
                    "mk.nama AS nama_mk, mk.sks " +
                    "FROM nilai n " +
                    "JOIN mahasiswa m ON n.mahasiswa_id = m.id " +
                    "JOIN mata_kuliah mk ON n.mata_kuliah_id = mk.id " +
                    "ORDER BY n.id DESC";

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
            %>

            <tr>
                <td><%= rs.getInt("id") %></td>
                <td><%= rs.getString("nama_mahasiswa") %></td>
                <td><%= rs.getString("nim") %></td>
                <td><%= rs.getString("nama_mk") %></td>
                <td><%= rs.getInt("sks") %></td>
                <td><%= rs.getDouble("nilai") %></td>
                <td>
                    <form style="display:inline;" method="post" action="nilai_edit.jsp">
                        <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
                        <input type="submit" value="Edit">
                    </form>

                    <form style="display:inline;" method="post" action="NilaiServlet?action=delete"
                          onsubmit="return confirm('Yakin ingin menghapus data nilai ini?');">
                        <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
                        <input type="submit" value="Hapus">
                    </form>
                </td>
            </tr>

            <%
                }
            } catch (Exception e) {
            %>
                <tr>
                    <td colspan="7" style="color:red;">
                        Error menampilkan data nilai: <%= e.getMessage() %>
                    </td>
                </tr>
            <%
            }
            %>
        </table>
    </div>

</div>

</body>
</html>