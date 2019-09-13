package servlet.admin;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/admin/users/update")
public class UpdateUserServlet extends HttpServlet {
 //   private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String id = req.getParameter("id");
//        User doUpdateUser = null;
//
//        try {
//            doUpdateUser = userService.getUserById(Long.parseLong(id));
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("1"+req.getSession().getAttribute("role"));
//        if (doUpdateUser != null) {
//            req.setAttribute("id", doUpdateUser.getId());
//            req.setAttribute("name", doUpdateUser.getName());
//            req.setAttribute("login", doUpdateUser.getLogin());
//            req.setAttribute("password", doUpdateUser.getPassword());
//            req.setAttribute("role", doUpdateUser.getRole());
//
//            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/updateUser.jsp");
//            requestDispatcher.forward(req, resp);
//        } else {
//            req.getSession().setAttribute("resultMessage", "Обновление пользователя прошло неуспешно!");
//            resp.sendRedirect("/result");
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        boolean result = false;
//
//        User doUpdateUser = new User();
//
//        doUpdateUser.setId(Long.parseLong(req.getParameter("id")));
//        doUpdateUser.setName(req.getParameter("name"));
//        doUpdateUser.setLogin(req.getParameter("login"));
//        doUpdateUser.setPassword(req.getParameter("password"));
//        doUpdateUser.setRole(req.getParameter("role"));
//
//        result = userService.updateUser(doUpdateUser);
//        String resultMessage = result ?
//                "Пользователь " + doUpdateUser.getLogin() + " успешно обновлен!" :
//                "Обновление пользователя " + doUpdateUser.getLogin() + " прошло неуспешно!";
//        req.getSession().setAttribute("resultMessage", resultMessage);
//        resp.sendRedirect("/result");
    }
}
