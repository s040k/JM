package servlet;

import model.User;
import service.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addUser.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        boolean result;
        UserService userService = UserService.getInstance();
        User doAddUser = new User();
        doAddUser.setName(req.getParameter("name"));
        doAddUser.setLogin(req.getParameter("login"));
        doAddUser.setPassword(req.getParameter("password"));

        result = userService.addUser(doAddUser);
        String resultMessage = result ?
                "Пользователь " + doAddUser.getLogin() + " успешно добавлен!" :
                "Пользователь " + doAddUser.getLogin() + " не может быть добавлен в базу!";
        resp.setStatus(result ? 202 : 406);
        req.getSession().setAttribute("resultMessage",resultMessage);
        resp.sendRedirect("/resultPage.jsp");
    }
}
