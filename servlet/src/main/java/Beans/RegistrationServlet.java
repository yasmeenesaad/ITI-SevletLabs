package Beans;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDataBean usr=(UserDataBean) req.getSession(false).getAttribute("userBean");
        PrintWriter out = resp.getWriter();
        out.println("first name: "+usr.getFirstName());
        out.println("last name: "+usr.getLastName());
        out.println("Age: "+usr.getAge());
        out.println("email: "+usr.getEmail());
        out.println("mobile : "+usr.getMobile());
    }
}
