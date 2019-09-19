package app.controller;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@ComponentScan(basePackages = {"app.service"})
public class AnonimController {

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
