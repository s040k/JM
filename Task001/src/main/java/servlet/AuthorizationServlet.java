package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Authorization", name = "authorizationServlet")
public class AuthorizationServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User authUser = userService.validate(login, password);

        if (authUser != null) {
            req.getSession().setAttribute("id",authUser.getId());
        }
        resp.sendRedirect("/");
    }
}
