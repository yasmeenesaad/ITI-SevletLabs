package com.hello;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Registration extends HttpServlet {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public void init() {
        ServletConfig config = getServletConfig();
        dbUrl = config.getInitParameter("dbUrl");
        dbUser = config.getInitParameter("dbUser");
        dbPassword = config.getInitParameter("dbPassword");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title> Registration Page</title></head><body>");

        // Login Form
        out.println("<h1>Register</h1>");
        out.println("<form method='post'>");
        out.println("Username: <input type='text' name='username'><br>");
        out.println("Password: <input type='password' name='password'><br>");
        out.println("<input type='submit' value='Register'>");
        out.println("</form>");

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // Save user data
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO login (username, password) VALUES (?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

            // Retrieve and display user data
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM login");

            out.println("<html><head><title>Login Data</title></head><body>");
            out.println("<h1>Login Data:</h1>");
            out.println("<ul>");
            while (rs.next()) {
                out.println("<li>Username: " + rs.getString("username") + ", Password: " + rs.getString("password") + "</li>");
            }
            out.println("</ul>");

            rs.close();
            stmt.close();
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}
