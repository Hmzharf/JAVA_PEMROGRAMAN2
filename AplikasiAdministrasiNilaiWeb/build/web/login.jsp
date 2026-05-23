<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*" %>
<html>
<head>
<title>Login</title>
<link href="style.css" rel="stylesheet">
</head>
<body>
<div class="login-box">
<h2>Login</h2>
<form method="post" action="LoginServlet">
<input type="text" name="username" placeholder="Username" required>
<input type="password" name="password" placeholder="Password" required>
<input type="submit" value="Login">
</form>
<%
if("1".equals(request.getParameter("error"))) {
%>
<p class="error">Username atau password salah!</p>
<%
}
%>
</div>
</body>
</html>