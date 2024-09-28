package com.hello;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class WelcomeServlet2 extends HttpServlet {

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session=request.getSession(false);
        if(session != null)
        {
            String name= (String) session.getAttribute("name");

            response.getWriter().println("<h1> Hello, " +name + "</h1>");
        }
        else {

            response.setContentType("text/html");

            response.getWriter().println("Error : name is required ");

            request.getRequestDispatcher("SayHello.html").include(request,response);

        }

    }

}
