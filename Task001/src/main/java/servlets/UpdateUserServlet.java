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

@WebServlet("/users/update")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = false;
        UserService userService = UserService.getInstance();
        User doUpdateUser = new User();

        doUpdateUser.setId(Long.parseLong(req.getParameter("id")));
        doUpdateUser.setName(req.getParameter("name"));
        doUpdateUser.setLogin(req.getParameter("login"));
        doUpdateUser.setPassword(req.getParameter("password"));

        result = userService.updateUser(doUpdateUser);
        String resultMessage = result ?
                "Пользователь " + doUpdateUser.getLogin() + " успешно обновлен!" :
                "Обновление пользователя " + doUpdateUser.getLogin() + " прошло неуспешно!";
        resp.setStatus(result ? 202 : 406);
        req.setAttribute("resultMessage", resultMessage);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/resultPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
