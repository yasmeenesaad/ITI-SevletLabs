package com.hello;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class LoginDB extends HttpServlet {

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
        out.println("<html><head><title>Login Page</title></head><body>");

//        // Include the header
//        String headerContent = new String(Files.readAllBytes(Paths.get("header.html")));
//        response.getWriter().println(headerContent);

        // Login Form
        out.println("<h1>Login</h1>");
        out.println("<form method='post'>");
        out.println("Username: <input type='text' name='username'><br>");
        out.println("Password: <input type='password' name='password'><br>");
        out.println("<input type='submit' value='Login'>");
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

            // Check if the username and password exist in the database
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM login WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            out.println("<html><head><title>Login Result</title></head><body>");
            if (rs.next()) {
                out.println("<h1>Login Successful</h1>");
                out.println("<p>Welcome, " + username + "!</p>");

                //if login succsefully start session and go to search servlet
                HttpSession session = request.getSession();
                session.setAttribute("authenticated", true);
                response.sendRedirect("Search");
                //////////////////////////////////////////

            } else {
                out.println("<h1>Login Failed</h1>");
                out.println("<p>Invalid username or password.</p>");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}