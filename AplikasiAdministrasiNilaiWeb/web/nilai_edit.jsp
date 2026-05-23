<%@ page import="java.sql.*" %>
<%@ page import="controller.DBConnection" %>

<%
String userName = (String) session.getAttribute("userName");
if (userName == null) {
    response.sendRedirect("login.jsp");
    return;
}

String id = request.getParameter("id");
if (id == null || id.equals("")) {
    response.sendRedirect("nilai.jsp");
    return;
}

int mahasiswaId = 0;
int mataKuliahId = 0;
double nilai = 0;

try {
    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement("SELECT * FROM nilai WHERE id=?");
    ps.setInt(1, Integer.parseInt(id));
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        mahasiswaId = rs.getInt("mahasiswa_id");
        mataKuliahId = rs.getInt("mata_kuliah_id");
        nilai = rs.getDouble("nilai");
    } else {
        response.sendRedirect("nilai.jsp");
        return;
    }
} catch (Exception e) {
    out.println("Error: " + e.getMessage());
}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Nilai</title>
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
        <h2>Edit Nilai</h2>

        <form method="post" action="NilaiServlet?action=edit">
            <input type="hidden" name="id" value="<%= id %>">

            <label>Mahasiswa</label>
            <select name="mahasiswa_id" required>
                <option value="">-- Pilih Mahasiswa --</option>
                <%
                try {
                    Connection con = DBConnection.getConnection();
                    Statement stMhs = con.createStatement();
                    ResultSet rsMhs = stMhs.executeQuery("SELECT id, nama, nim FROM mahasiswa ORDER BY id ASC");

                    while (rsMhs.next()) {
                        int currentId = rsMhs.getInt("id");
                %>
                    <option value="<%= currentId %>" <%= currentId == mahasiswaId ? "selected" : "" %>>
                        <%= currentId %> - <%= rsMhs.getString("nama") %> / <%= rsMhs.getString("nim") %>
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
                        int currentId = rsMk.getInt("id");
                %>
                    <option value="<%= currentId %>" <%= currentId == mataKuliahId ? "selected" : "" %>>
                        <%= currentId %> - <%= rsMk.getString("nama") %> / <%= rsMk.getInt("sks") %> SKS
                    </option>
                <%
                    }
                } catch (Exception e) {
                    out.println("<option>Error mata kuliah: " + e.getMessage() + "</option>");
                }
                %>
            </select>

            <label>Nilai</label>
            <input type="number" step="0.01" name="nilai" value="<%= nilai %>" required>

            <input type="submit" value="Simpan Perubahan">
            <a href="nilai.jsp">Kembali</a>
        </form>
    </div>
</div>

</body>
</html>