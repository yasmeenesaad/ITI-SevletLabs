package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/SayHelloServlet")
public class LoggerFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String ipAddress = httpRequest.getRemoteAddr();
        String userInput = request.getParameter("name"); // Assuming user input is a parameter named "input"

        System.out.println("IP Address: " + ipAddress);
        System.out.println("User Input: " + userInput);

        chain.doFilter(request, response);
    }


}
