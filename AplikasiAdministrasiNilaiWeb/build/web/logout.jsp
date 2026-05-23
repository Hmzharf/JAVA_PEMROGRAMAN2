<%@ page import="jakarta.servlet.http.*" %>
<%
session.invalidate();
response.sendRedirect("login.jsp");
%>