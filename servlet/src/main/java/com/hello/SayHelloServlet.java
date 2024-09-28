package com.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


public class SayHelloServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        String name=request.getParameter("name");

        HttpSession session=request.getSession(true);

        session.setAttribute("name",name);

        response.sendRedirect("WelcomeServlet2");

    }
}
