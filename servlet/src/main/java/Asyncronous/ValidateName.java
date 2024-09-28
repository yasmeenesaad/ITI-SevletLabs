package Asyncronous;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ValidateName")
public class ValidateName extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/plain");

        // Get the 'name' parameter from the request
        String name = request.getParameter("name");

        // Validate the name (e.g., check if it matches a pattern, or if it's not empty)
        String result;
        if (name != null && !name.trim().isEmpty()) {
            if (name.matches("^[a-zA-Z]{3,}$")) {
                result = "Valid name";
            } else {
                result = "Invalid name (only letters, at least 3 characters)";
            }
        } else {
            result = "Name cannot be empty";
        }

        // Send the result back to the client
        PrintWriter out = response.getWriter();
        out.println(result);
        out.close();
    }
}
