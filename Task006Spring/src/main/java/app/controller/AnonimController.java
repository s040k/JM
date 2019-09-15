package app.controller;

import app.model.User;
import app.model.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Set;

@Controller
@ComponentScan(basePackages = {"web", "app.service"})
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

//    @GetMapping("/eror")
//    public String erorPageGet() {
//        return "eror";
//    }

    @GetMapping("/login")
    public String loginPageGet() {
        return "login";
    }

    @PostMapping("/authorization")
    public String authorizationPagePost(@RequestParam String login, @RequestParam String password, HttpServletRequest req) {

        User authUser = userService.validate(login, password);

        if (authUser != null) {
            req.getSession().setAttribute("user", authUser);
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutPageGet() {
        return "logout";
    }

    @PostMapping("/logout")
    public String logoutPagePost(HttpServletRequest req) {
        req.getSession().setAttribute("user", null);
        return "redirect:/";
    }

    @GetMapping("/result")
    public String resultPageGet(Model model) {
        return "resultPage";
    }
}
