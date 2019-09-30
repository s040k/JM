package app.controller;


import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/logout")
    public String logoutPageGet() {
        return "logout";
    }

    @GetMapping("/login")
    public String logingPageGet() {
        return "login";
    }


    @GetMapping("/result")
    public String resultPageGet() {
        return "resultPage";
    }

}
