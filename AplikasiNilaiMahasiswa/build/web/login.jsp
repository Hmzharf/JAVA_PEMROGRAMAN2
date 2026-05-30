<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Login Aplikasi</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>

<div class="wrapper">

    <%@ include file="layout/header.jsp" %>

    <div class="main">

        <%@ include file="layout/sidebar.jsp" %>

        <div class="content">

            <h2 class="page-title">Login Pengguna</h2>

            <div class="form-box">

                <%
                    String pesan = (String) request.getAttribute("pesan");
                    if (pesan != null) {
                %>
                    <div class="info-box">
                        <h3>Login Gagal</h3>
                        <p><%= pesan %></p>
                    </div>
                    <br>
                <%
                    }
                %>

                <form action="LoginController" method="post">

                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" name="username" placeholder="Masukkan username" required>
                    </div>

                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="password" placeholder="Masukkan password" required>
                    </div>

                    <button type="submit" class="btn">Login</button>
                    <button type="reset" class="btn btn-danger">Reset</button>

                </form>
            </div>

        </div>
    </div>

    <%@ include file="layout/footer.jsp" %>

</div>

</body>
</html>