package com.hello;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Search extends HttpServlet {

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

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("authenticated") != null) {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Search Name</title></head><body>");

        out.println("<h1>Search Name</h1>");
        out.println("<form method='post'>");
        out.println("Enter name to search: <input type='text' name='searchName'><br>");
        out.println("<input type='submit' value='Search'>");
        out.println("</form>");

        out.println("</body></html>");
        } else {
            // User is not authenticated, redirect to the login page
            response.sendRedirect("LoginDB");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String searchName = request.getParameter("searchName");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM login WHERE username like ?");
            pstmt.setString(1, searchName);

            ResultSet rs = pstmt.executeQuery();

            out.println("<html><head><title>Search Result</title></head><body>");
            out.println("<h1>Search Result</h1>");
            out.println("<ul>");
            while (rs.next()) {
                out.println("<li>Username: " + rs.getString("username") + ", Password: " + rs.getString("password") + "</li>");
            }
            out.println("</ul>");
            List<User> userList = new ArrayList<>();

            while (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString("username"));
                user.setPass(rs.getString("password"));
                userList.add(user);
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
