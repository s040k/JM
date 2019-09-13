package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@ComponentScan(basePackages = {"web", "service"})
public class AnonimController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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
