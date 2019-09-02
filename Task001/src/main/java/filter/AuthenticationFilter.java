package filter;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*", "/admin/*"}, filterName = "authenticationFilter")
public class AuthenticationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        boolean result = false;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String uri = httpRequest.getRequestURI();
        User user = (User) httpRequest.getSession().getAttribute("user");

        if (user != null) {
            if (uri.startsWith("/user")) {
                result = user.getRole().equals("admin") || user.getRole().equals("user");

            } else if (uri.startsWith("/admin")) {
                result = user.getRole().equals("admin");
            }
        }

        if (result) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpRequest.getSession().setAttribute("resultMessage", "У вас недостаточно прав для просмотра данной страницы.");
            ((HttpServletResponse)servletResponse).sendRedirect("/result");
        }
    }

    @Override
    public void destroy() {

    }


}
