package com.hello;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AttributeGetter extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        ServletContext cxt= req.getServletContext();
        ArrayList<String> Countries= (ArrayList<String>) cxt.getAttribute("Countries");
        if (Countries != null){
            PrintWriter out = res.getWriter();
            out.println("supported countries are");
            Countries.forEach(out::println);
        }
    }
}
