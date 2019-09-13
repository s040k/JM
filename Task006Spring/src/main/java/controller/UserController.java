package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@ComponentScan(basePackages = {"web", "service"})
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
    public String showUserGet(Model model, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");

        model.addAttribute("name", user.getName());
        model.addAttribute("login", user.getLogin());
        model.addAttribute("password", user.getPassword());

        return "/user/userPage";
    }


}
