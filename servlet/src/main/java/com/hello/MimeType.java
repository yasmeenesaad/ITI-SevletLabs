package com.hello;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
class User {

    private   String userName;
    private String pass;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public User(String uname, String password)
    {
        userName = uname;
        pass = password;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "{ userName: "+userName+", password : " +pass+"}";
    }
}

@WebServlet(value = "/MimeType")
public class MimeType extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        User jsonObject = new User("yasmeen", "saad");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();

    }
}
