package com.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddCookieServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Cookie cookie=new Cookie("testCookie", "hello");
        // Add a cookie to the response
        response.addCookie(cookie);

        // Redirect to the servlet that attempts to retrieve the cookie
        response.sendRedirect("RetrieveCookieServlet");
    }
}
