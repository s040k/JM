package filter;
import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*", "/admin/*"}, filterName = "authenticationFilter")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        boolean result;
        UserService userService = UserService.getInstance();
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String uri = httpRequest.getRequestURI();
        Long idUser = (Long) httpRequest.getSession().getAttribute("id");
        User user = userService.getUserById(idUser);

        if (user == null || user.getRole()==null) {
            result = false;

        } else if (uri.startsWith("/user")) {
            result = user.getRole().equals("admin") || user.getRole().equals("user");

        } else if (uri.startsWith("/admin")) {
            result = user.getRole().equals("admin");

        } else {
            result = true;
        }

        if (result) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpRequest.getSession().setAttribute("resultMessage", "У вас недостаточно прав для просмотра данной страницы.");
            servletRequest.getRequestDispatcher("/errorPage.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
