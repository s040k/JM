package servlet.user;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/info")
public class ViewUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

            req.setAttribute("name",user.getName());
            req.setAttribute("login",user.getLogin());
            req.setAttribute("password",user.getPassword());

        req.getRequestDispatcher("/user/userPage.jsp").forward(req, resp);

    }
}
