package controller;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name="LoginServlet", urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String u = req.getParameter("username");
        String p = req.getParameter("password");
        try {
            Connection c = DBConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, u);
            ps.setString(2, p);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("userName", u);
                res.sendRedirect("index.jsp");
            } else {
                res.sendRedirect("login.jsp?error=1");
            }
        } catch(Exception e){ e.printStackTrace(); }
    }
}