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

@WebServlet("/users/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = false;
        UserService userService = UserService.getInstance();
        Long id = Long.parseLong(req.getParameter("id"));
        User doDeleteUser = userService.getUserById(id);
        if (doDeleteUser != null) {
            result = userService.deleteUser(doDeleteUser);
        }
        String resultMessage = result ?
                "Пользователь " + doDeleteUser.getLogin() + " успешно удален!" :
                "Операция удаления пользователя прошла не успешно!";
        resp.setStatus(result ? 202 : 406);
        req.setAttribute("resultMessage", resultMessage);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/resultPage.jsp");
        requestDispatcher.forward(req, resp);
    }

}
