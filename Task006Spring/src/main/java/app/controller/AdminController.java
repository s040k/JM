package app.controller;

import app.model.NameRoles;
import app.model.Role;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@ComponentScan(basePackages = {"app.service"})
public class AdminController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin")
    public String mainUserPageGet() {
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users")
    public String showAllUserGet(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("simpleUsers", users);
        return "/admin/allUsers";
    }

    @GetMapping("/admin/users/add")
    public String addUserGet(Model model) {
        NameRoles[] roles = NameRoles.values();
        model.addAttribute("simpleRoles", roles);

        return "/admin/addUser";
    }

    @PostMapping("/admin/users/add")
    public String addUserPost(HttpServletRequest request, HttpSession session) {
        boolean result = false;
        User doAddUser = new User();

        doAddUser.setName(request.getParameter("name"));
        doAddUser.setLogin(request.getParameter("login"));
        doAddUser.setPassword(request.getParameter("password"));

        Set<Role> roles = new HashSet<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parName = parameterNames.nextElement();
            if (parName.contains("checkBoxParameter")) {
                roles.add(new Role(request.getParameter(parName)));
            }
        }
        doAddUser.setRoles(roles);

        if (!roles.isEmpty()) {
            result = userService.addUser(doAddUser);
        }

        String resultMessage = result ?
                "Пользователь " + doAddUser.getLogin() + " успешно добавлен!" :
                "Пользователь " + doAddUser.getLogin() + " не может быть добавлен в базу!";

        session.setAttribute("resultMessage", resultMessage);
        return "redirect:/result";
    }

    @GetMapping("/admin/users/delete")
    public String deleteUserGet(@RequestParam String id, Model model, HttpSession session) {
        User doDeleteUser = null;

        try {
            doDeleteUser = userService.getUserById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (doDeleteUser != null) {
            model.addAttribute("id", doDeleteUser.getId());
            model.addAttribute("login", doDeleteUser.getLogin());

            return "/admin/deleteUser";
        } else {
            session.setAttribute("resultMessage", "Операция удаления пользователя прошла не успешно!");
            return "redirect:/result";
        }
    }

    @PostMapping("/admin/users/delete")
    public String deleteUserPost(@RequestParam String id, HttpSession session) {
        boolean result = false;
        Long idUser = Long.parseLong(id);
        result = userService.deleteUser(idUser);
        String resultMessage = result ?
                "Пользователь успешно удален!" :
                "Операция удаления пользователя прошла не успешно!";
        session.setAttribute("resultMessage", resultMessage);
        return "redirect:/result";
    }

    @GetMapping("/admin/users/update")
    public String updateUserGet(@RequestParam String id, Model model, HttpSession session) {
        User doUpdateUser = null;

        try {
            doUpdateUser = userService.getUserById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (doUpdateUser != null) {
            model.addAttribute("user", doUpdateUser);
            NameRoles[] roles = NameRoles.values();
            model.addAttribute("simpleRoles", roles);

            return "/admin/updateUser";
        } else {
            session.setAttribute("resultMessage", "Обновление пользователя прошло неуспешно!");
            return "redirect:/result";
        }
    }

    @PostMapping("/admin/users/update")
    public String updateUserPost(HttpServletRequest request, HttpSession session) {

        boolean result = false;
        User doUpdateUser = new User();

        doUpdateUser.setId(Long.parseLong(request.getParameter("id")));
        doUpdateUser.setName(request.getParameter("name"));
        doUpdateUser.setLogin(request.getParameter("login"));
        doUpdateUser.setPassword(request.getParameter("password"));


        Set<Role> roles = new HashSet<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parName = parameterNames.nextElement();
            if (parName.contains("checkBoxParameter")) {
                roles.add(new Role(request.getParameter(parName)));
            }
        }
        doUpdateUser.setRoles(roles);

        if (!roles.isEmpty()) {
            result = userService.updateUser(doUpdateUser);
        }


        String resultMessage = result ?
                "Пользователь " + doUpdateUser.getLogin() + " успешно обновлен!" :
                "Обновление пользователя " + doUpdateUser.getLogin() + " прошло неуспешно!";
        session.setAttribute("resultMessage", resultMessage);
        return "redirect:/result";
    }
}
