package com.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class RetrieveCookieServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Check if cookies are enabled
        if (request.getCookies() == null || request.getCookies().length == 0) {
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Error</h1>");
            out.println("<p>Please enable cookies to use this feature.</p>");
            out.println("</body></html>");
        } else {
            out.println("<html><head><title>Retrieve Cookie</title></head><body>");
            out.println("<h1>Retrieve Cookie</h1>");
            out.println("<p>Cookie retrieved successfully!</p>");

            // Display the cookie value
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("testCookie")) {
                    out.println("<p>Cookie Name: " + cookie.getName() + ", Value: " + cookie.getValue() + "</p>");
                }
            }

            out.println("</body></html>");
        }
    }
}