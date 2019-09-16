package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import app.service.UserService;

import java.util.Collection;

@Controller
@ComponentScan(basePackages = {"app.service"})
public class AnonimController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPageGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin";
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return "redirect:/user";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPageGet() {
        return "login";
    }


    @GetMapping("/result")
    public String resultPageGet(Model model) {
        return "resultPage";
    }
}
