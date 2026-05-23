<%@ page import="java.sql.*" %>
<%@ page import="controller.DBConnection" %>
<%
String userName = (String) session.getAttribute("userName");
if(userName == null){ response.sendRedirect("login.jsp"); }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mata Kuliah</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
<!-- Navbar -->
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
    <!-- Form Tambah Mata Kuliah -->
    <div class="card">
        <h2>Tambah Mata Kuliah</h2>
        <form method="post" action="MataKuliahServlet?action=add">
            <input type="text" name="kode" placeholder="Kode" required>
            <input type="text" name="nama" placeholder="Nama Mata Kuliah" required>
            <input type="number" name="sks" placeholder="SKS" required>
            <input type="submit" value="Tambah">
        </form>
    </div>

    <!-- Tabel Daftar Mata Kuliah -->
    <div class="card">
        <h3>Daftar Mata Kuliah</h3>
        <table>
            <tr><th>ID</th><th>Kode</th><th>Nama</th><th>SKS</th><th>Aksi</th></tr>
            <%
            try {
                Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM mata_kuliah");
                while(rs.next()){
            %>
            <tr>
                <td><%= rs.getInt("id") %></td>
                <td><%= rs.getString("kode") %></td>
                <td><%= rs.getString("nama") %></td>
                <td><%= rs.getInt("sks") %></td>
                <td>
                    <form style="display:inline;" method="post" action="MataKuliahServlet?action=delete">
                        <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
                        <input type="submit" value="Hapus">
                    </form>
                    <form style="display:inline;" method="post" action="mata_kuliah_edit.jsp">
                        <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
                        <input type="submit" value="Edit">
                    </form>
                </td>
            </tr>
            <% }} catch(Exception e){ e.printStackTrace(); } %>
        </table>
    </div>
</div>
</body>
</html>