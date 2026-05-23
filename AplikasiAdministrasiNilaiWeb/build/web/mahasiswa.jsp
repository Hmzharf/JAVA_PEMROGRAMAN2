<%@ page import="java.sql.Connection, java.sql.Statement, java.sql.ResultSet" %>
<%@ page import="controller.DBConnection" %>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*" %>

<%
String userName = (String) session.getAttribute("userName");
if(userName == null){
    response.sendRedirect("login.jsp");
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CRUD Mahasiswa</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
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
        <h2>Tambah Mahasiswa</h2>
        <form method="post" action="MahasiswaServlet?action=add">
            <input type="text" name="nama" placeholder="Nama" required>
            <input type="text" name="nim" placeholder="NIM" required>
            <input type="text" name="jurusan" placeholder="Jurusan" required>
            <input type="submit" value="Tambah">
        </form>
    </div>

    <div class="card">
        <h3>Daftar Mahasiswa</h3>
        <table>
            <tr>
                <th>ID</th><th>Nama</th><th>NIM</th><th>Jurusan</th><th>Aksi</th>
            </tr>
            <%
            try {
                Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM mahasiswa");
                while(rs.next()){
            %>
            <tr>
                <td><%= rs.getInt("id") %></td>
                <td><%= rs.getString("nama") %></td>
                <td><%= rs.getString("nim") %></td>
                <td><%= rs.getString("jurusan") %></td>
                <td>
                    <form style="display:inline;" method="post" action="MahasiswaServlet?action=delete">
                        <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
                        <input type="submit" value="Hapus">
                    </form>
                    <form style="display:inline;" method="post" action="mahasiswa_edit.jsp">
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