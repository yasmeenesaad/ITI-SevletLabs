//package com.hello;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet(value = "/process-data")
//public class ProcessData extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//
//        // Create a UserBean and populate it with the data
//        UserDataBean user = new UserDataBean();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//
//        // Set the bean in the request or session scope
//        request.setAttribute("user", user);  // Use request scope
//        // session.setAttribute("user", user);  // Use session scope if needed
//        request.getRequestDispatcher("jsp/display_data.jsp").forward(request, response);
//    }
//}
