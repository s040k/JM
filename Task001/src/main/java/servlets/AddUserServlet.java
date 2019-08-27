package servlets;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        req.setAttribute("resultMessage", resultMessage);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/resultPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
