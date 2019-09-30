package app.controller;

import app.service.UserService;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@ComponentScan(basePackages = {"app.service"})
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String mainUserPageGet() {
        return "redirect:/user/info";
    }

    @GetMapping("/user/info")
    public String showUserGet(Model model, Principal principal) {
        User user = userService.getByLogin(principal.getName());

        model.addAttribute("principalUser", user);

        return "userPage";
    }


}
