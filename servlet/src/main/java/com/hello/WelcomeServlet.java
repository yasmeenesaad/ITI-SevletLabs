package com.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Include the header
//        request.getRequestDispatcher("/header.html").include(request, response);

        // Main content
        response.getWriter().println("<h1>Welcome to the System!</h1>");
        response.getWriter().println("<p>You have successfully logged in.</p>");

        // Include the footer
      //  request.getRequestDispatcher("/footer.html").include(request, response);
    }
}