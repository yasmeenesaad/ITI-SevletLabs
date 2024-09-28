package com.hello;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class SQLTool extends HttpServlet {

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
        out.println("<html><head><title>SQL Tool</title></head><body>");

        out.println("<h1>SQL Tool</h1>");
        out.println("<form method='post'>");
        out.println("<textarea name='sqlQuery' rows='5' cols='50'></textarea><br>");
        out.println("<input type='submit' value='Execute Query'>");
        out.println("</form>");

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String sqlQuery = request.getParameter("sqlQuery");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            Statement stmt = conn.createStatement();
            boolean hasResultSet = stmt.execute(sqlQuery);

            out.println("<html><head><title>SQL Tool Result</title></head><body>");
            out.println("<h1>SQL Tool Result</h1>");
            if (hasResultSet) {
                ResultSet rs = stmt.getResultSet();
                out.println("<textarea rows='10' cols='80'>");
                while (rs.next()) {
                    out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\n");
                }
                out.println("</textarea>");
                rs.close();
            } else {
                int updateCount = stmt.getUpdateCount();
                out.println("<p>Query executed successfully. Rows affected: " + updateCount + "</p>");
            }

            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}