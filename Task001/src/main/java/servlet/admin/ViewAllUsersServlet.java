package servlet.admin;

import model.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class ViewAllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        List<User> users = userService.getAllUsers();
        req.setAttribute("simpleUsers", users);
        resp.setStatus(200);
        req.getRequestDispatcher("/admin/allUsers.jsp").forward(req, resp);
    }
}
