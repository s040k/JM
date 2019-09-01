package filter;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/login")
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        UserService userService = UserService.getInstance();
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        Long idUser = (Long) httpRequest.getSession().getAttribute("id");
        User user = userService.getUserById(idUser);

        if (user != null && user.getRole() != null && ((user.getRole().equals("admin") || user.getRole().equals("user")))) {

            if (user.getRole().equals("user")) {
                ((HttpServletResponse) servletResponse).sendRedirect("/user/userPage.jsp");
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect("admin/users");
            }

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
