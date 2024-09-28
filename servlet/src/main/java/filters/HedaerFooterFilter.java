package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.LogRecord;
@WebFilter()
public class HedaerFooterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        PrintWriter out=response.getWriter();
        out.println("<h1> This is header </h1>");
        chain.doFilter(request,response);
        out.println("<h1>This is Footer</h1>");
    }

}
