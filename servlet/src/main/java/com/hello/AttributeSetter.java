package com.hello;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.ArrayList;

public class AttributeSetter extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("setter servlet");
        ServletContext cxt= req.getServletContext();
        ArrayList supportedCountries=new ArrayList<>();
        supportedCountries.add("Egypt");
        supportedCountries.add("German");
        supportedCountries.add("France");
        cxt.setAttribute("Countries",supportedCountries);

        System.out.println("setter servlet");
    }
}
