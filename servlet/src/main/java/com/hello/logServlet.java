package com.hello;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class logServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if(userName !=null &&!userName.isEmpty() && password!=null && !password.isEmpty()){
            if (userName.equals("jasmine") && password.equals("4520")) {
                RequestDispatcher rd=request.getRequestDispatcher("WelcomeServletURL");
                rd.forward(request,response);
                //response.sendRedirect("WelcomeServletURL");
            }

        }


        else {
//             response.setContentType("tex/html");
//             RequestDispatcher rd=request.getRequestDispatcher("login.html");
//             response.getWriter().println("user name and password are required");
//             rd.include(request,response);
            out.println("Login Failed, please try again");
            out.println("<a href= login.html >Login Page</a>");
            response.sendRedirect("login.html");
        }
    }
}
