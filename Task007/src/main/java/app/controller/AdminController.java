package app.controller;


import app.service.RoleService;
import app.service.UserService;
import app.model.Role;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@Controller
@ComponentScan(basePackages = {"app.service"})
public class AdminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("nonCryptPasswordEncoder")
    private PasswordEncoder passwordEncoder;


    @GetMapping("/test")
    public String testGet(HttpServletRequest request) {
//        System.out.println(
//                request.getParameter("id") + " " +
//                        request.getParameter("email") + " " +
//                        request.getParameter("login") + " " +
//                        request.getParameter("password")
//        );
//        System.out.println("XOOXOXOXOXOX");
        return "testVar";
    }

    @GetMapping("/admin")
    public String mainUserPageGet() {
        return "redirect:/admin/users";
    }

    //    @GetMapping("/admin/users")
//    public String showAllUserGet(Model model) {
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("simpleUsers", users);
//
//        return "/admin/allUsers";
//    }
    @GetMapping("/admin/users")
    public String superControllerGet(Model model, Principal principal) {
        List<User> users = userService.getAllUsers();
        User principalUser = userService.getByLogin(principal.getName());
        model.addAttribute("simpleUsers", users);
        model.addAttribute("principalUser", principalUser);

        return "adminPage2";
        //  return "/admin/allUsers";
    }

    @GetMapping("/admin/users/add")
    public String addUserGet(Model model) {
        List<Role> roles = roleService.getAll();
        model.addAttribute("simpleRoles", roles);

        return "/admin/addUser";
    }

    @PostMapping("/admin/users/add")
    public String addUserPost(HttpServletRequest request, HttpSession session) {
        boolean result = false;
        User doAddUser = new User();

        doAddUser.setEmail(request.getParameter("email"));
        doAddUser.setLogin(request.getParameter("login"));
        doAddUser.setPassword(passwordEncoder.encode(request.getParameter("password")));

        Set<Role> roles = new HashSet<>();
        String role = request.getParameter("roles");
        if (role != null && !role.isEmpty()) {
            roles.add(roleService.getRoleByName(role));
        }
        doAddUser.setRoles(roles);

        if (!roles.isEmpty() && !request.getParameter("password").isEmpty()) {
            result = userService.addUser(doAddUser);
        }

        String resultMessage = result ?
                "Пользователь " + doAddUser.getLogin() + " успешно добавлен!" :
                "Пользователь " + doAddUser.getLogin() + " не может быть добавлен в базу!";

        //session.setAttribute("resultMessage", resultMessage);

        return "redirect:/admin/users";
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
            List<Role> roles = roleService.getAll();
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
        doUpdateUser.setEmail(request.getParameter("email"));
        doUpdateUser.setLogin(request.getParameter("login"));
        doUpdateUser.setPassword(passwordEncoder.encode(request.getParameter("password")));

        System.out.println(request.getParameter("login"));
        Set<Role> roles = new HashSet<>();
        String role = request.getParameter("roles");
        if (role != null && !role.isEmpty()) {
            roles.add(roleService.getRoleByName(role));
        }
        doUpdateUser.setRoles(roles);

        if (!roles.isEmpty() && !request.getParameter("password").isEmpty()) {
            result = userService.updateUser(doUpdateUser);
        }


        String resultMessage = result ?
                "Пользователь " + doUpdateUser.getLogin() + " успешно обновлен!" :
                "Обновление пользователя " + doUpdateUser.getLogin() + " прошло неуспешно!";
        session.setAttribute("resultMessage", resultMessage);

        return "redirect:/admin/users";
    }
}
