package app.servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/admin/users/delete")
public class DeleteUserServlet extends HttpServlet {
  //  private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//        User doDeleteUser = null;
//
//        try {
//            doDeleteUser = userService.getUserById(Long.parseLong(id));
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//
//        if (doDeleteUser != null) {
//            req.setAttribute("id", doDeleteUser.getId());
//            req.setAttribute("login", doDeleteUser.getLogin());
//
//            resp.setStatus(200);
//            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/deleteUser.jsp");
//            requestDispatcher.forward(req, resp);
//        } else {
//            req.getSession().setAttribute("resultMessage", "Операция удаления пользователя прошла не успешно!");
//            resp.sendRedirect("/result");
//        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        boolean result = false;
//        Long id = Long.parseLong(req.getParameter("id"));
//        result = userService.deleteUser(id);
//        String resultMessage = result ?
//                "Пользователь успешно удален!" :
//                "Операция удаления пользователя прошла не успешно!";
//        req.getSession().setAttribute("resultMessage", resultMessage);
//        resp.sendRedirect("/result");
    }

}
