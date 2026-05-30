<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    session.invalidate();
%>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>

<div class="wrapper">

    <%@ include file="layout/header.jsp" %>

    <div class="main">

        <%@ include file="layout/sidebar.jsp" %>

        <div class="content">

            <div class="welcome-box">
                <h2>Logout Berhasil</h2>
                <p>
                    Anda telah keluar dari aplikasi. Silakan klik tombol di bawah ini
                    untuk kembali ke halaman login.
                </p>
                <br>
                <a href="login.jsp" class="btn">Kembali ke Login</a>
            </div>

        </div>
    </div>

    <%@ include file="layout/footer.jsp" %>

</div>

</body>
</html>