package app.controller;


import app.service.RoleService;
import app.service.UserService;
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


    @GetMapping("/admin")
    public String mainUserPageGet() {
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users")
    public String superControllerGet(Model model, Principal principal) {
        List<User> users = userService.getAllUsers();
        User principalUser = userService.getByLogin(principal.getName());
        model.addAttribute("simpleUsers", users);
        model.addAttribute("principalUser", principalUser);

        return "adminPage";
    }

    @PostMapping("/admin/users/add")
    public String addUserPost(HttpServletRequest request, HttpSession session) {
        User doAddUser = new User();

        doAddUser.setEmail(request.getParameter("email"));
        doAddUser.setLogin(request.getParameter("login"));
        doAddUser.setPassword(passwordEncoder.encode(request.getParameter("password")));

        String role = request.getParameter("roles");
        if (role != null && !role.isEmpty()) {
            doAddUser.setRole(roleService.getRoleByName(role));
        }

        if (doAddUser.getRole() != null && !request.getParameter("password").isEmpty()) {
            userService.addUser(doAddUser);
        }

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/delete")
    public String deleteUserGet(@RequestParam String id, Principal principal) {
        boolean logout = false;
        User currentUser = userService.getByLogin(principal.getName());
        User doDeleteUser = null;
        System.out.println(id);
        try {
            doDeleteUser = userService.getUserById(Long.parseLong(id));
            userService.deleteUser(doDeleteUser.getId());
            logout = currentUser.getId().equals(doDeleteUser.getId());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return logout? "redirect:/logout":"redirect:/admin/users";
    }

    @PostMapping("/admin/users/update")
    public String updateUserPost(HttpServletRequest request, HttpSession session, Principal principal) {
        boolean logout = false;

        User doUpdateUser = new User();
        User currentUser = userService.getByLogin(principal.getName());

        doUpdateUser.setId(Long.parseLong(request.getParameter("id")));
        doUpdateUser.setEmail(request.getParameter("email"));
        doUpdateUser.setLogin(request.getParameter("login"));
        doUpdateUser.setPassword(passwordEncoder.encode(request.getParameter("password")));


        String role = request.getParameter("roles");
        if (role != null && !role.isEmpty()) {
            doUpdateUser.setRole(roleService.getRoleByName(role));
        }

        if (doUpdateUser.getRole() != null && !request.getParameter("password").isEmpty()) {
            userService.updateUser(doUpdateUser);
        }

        logout = currentUser.getId().equals(doUpdateUser.getId());
        return logout? "redirect:/logout":"redirect:/admin/users";
    }
}
