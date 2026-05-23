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
<title>Aplikasi Administrasi Nilai</title>
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
<h2>Selamat Datang, <%= userName %></h2>
<p>Pilih menu di atas untuk mengelola data mahasiswa, mata kuliah, dan nilai.</p>
</div>
</div>
</body>
</html>