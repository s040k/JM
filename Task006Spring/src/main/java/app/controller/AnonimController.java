package app.controller;


import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@ComponentScan(basePackages = {"app.service"})
public class AnonimController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String mainPageGet(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            if (request.isUserInRole("ROLE_ADMIN")) {
                return "redirect:/admin";
            } else {
                return "redirect:/user";
            }
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPageGet() {
        return "login";
    }


    @GetMapping("/result")
    public String resultPageGet() {
        return "resultPage";
    }
}
