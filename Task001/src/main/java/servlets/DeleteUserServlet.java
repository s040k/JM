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
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User doDeleteUser = null;

        try {
            doDeleteUser = userService.getUserById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (doDeleteUser != null) {
            req.setAttribute("id", doDeleteUser.getId());
            req.setAttribute("login", doDeleteUser.getLogin());

            resp.setStatus(200);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/deleteUser.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            req.getSession().setAttribute("resultMessage", "Операция удаления пользователя прошла не успешно!");
            resp.sendRedirect("/resultPage.jsp");
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        boolean result = false;
        Long id = Long.parseLong(req.getParameter("id"));
        User doDeleteUser = userService.getUserById(id);
        if (doDeleteUser != null) {
            result = userService.deleteUser(doDeleteUser);
        }
        String resultMessage = result ?
                "Пользователь " + doDeleteUser.getLogin() + " успешно удален!" :
                "Операция удаления пользователя прошла не успешно!";
        req.getSession().setAttribute("resultMessage", resultMessage);
        resp.sendRedirect("/resultPage.jsp");
    }

}
