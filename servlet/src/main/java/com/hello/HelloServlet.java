package com.hello;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(
        value = "/sayhello",
        initParams = {
        @WebInitParam(name = "name", value = "yasmeen"),
                @WebInitParam(name = "age", value = "25"),
                @WebInitParam(name = "country",value = "egypt")
}
)
public class HelloServlet implements Servlet {
    ServletConfig conf;

    @Override
    public void init (ServletConfig config) throws ServletException
    {
        System.out.println("I am inside the init method");
        conf=config;
    }

    @Override
    public void service (ServletRequest request,ServletResponse response) throws ServletException, IOException
    {
//                // Include the header
//        String headerContent = new String(Files.readAllBytes(Paths.get("header.html")));
//        response.getWriter().println(headerContent);


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name=conf.getInitParameter("name");
        String age=conf.getInitParameter("age");
        String country=conf.getInitParameter("country");
        out.println("<h1> my name is "+name+" my age is "+age+" I'm from "+country+"</h1>");
        System.out.println("I am inside the service method");

//                // Include the footer
//        String footerContent = new String(Files.readAllBytes(Paths.get("footer.html")));
//        response.getWriter().println(footerContent);
    }

    @Override
    public void destroy ()
    {
        System.out.println("I am inside the destroy method");
    }

    @Override
    public String getServletInfo()
    {
        return null;
    }

    @Override
    public ServletConfig getServletConfig()
    {
        return null;
    }

}