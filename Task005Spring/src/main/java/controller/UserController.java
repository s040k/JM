package controller;

import model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
@ComponentScan(basePackages = "web")
public class UserController {
    private UserService userService = UserService.getInstance();


    @GetMapping("/users")
    public String showAllUserGet(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("simpleUsers", users);
        return "/allUsers";
    }

    @GetMapping("/users/add")
    public String addUserGet() {
        return "/addUser";
    }

    @PostMapping("/users/add")
    public String addUserPost(@RequestParam String name, @RequestParam String login, @RequestParam String password, HttpSession session) {
        boolean result;
        User doAddUser = new User();
        System.out.println(name+" "+login+" "+password);
        doAddUser.setName(name);
        doAddUser.setLogin(login);
        doAddUser.setPassword(password);

        result = userService.addUser(doAddUser);
        String resultMessage = result ?
                "Пользователь " + doAddUser.getLogin() + " успешно добавлен!" :
                "Пользователь " + doAddUser.getLogin() + " не может быть добавлен в базу!";

        session.setAttribute("resultMessage", resultMessage);
        return "redirect:/result";
    }

    @GetMapping("/users/delete")
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

            return "/deleteUser";
        } else {
            session.setAttribute("resultMessage", "Операция удаления пользователя прошла не успешно!");
            return "redirect:/result";
        }
    }

    @PostMapping("/users/delete")
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

    @GetMapping("/users/update")
    public String updateUserGet(@RequestParam String id, Model model, HttpSession session) {
        User doUpdateUser = null;

        try {
            doUpdateUser = userService.getUserById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (doUpdateUser != null) {
            model.addAttribute("id", doUpdateUser.getId());
            model.addAttribute("name", doUpdateUser.getName());
            model.addAttribute("login", doUpdateUser.getLogin());
            model.addAttribute("password", doUpdateUser.getPassword());

            return "/updateUser";
        } else {
            session.setAttribute("resultMessage", "Обновление пользователя прошло неуспешно!");
            return "redirect:/result";
        }
    }

    @PostMapping("/users/update")
    public String updateUserPost(@RequestParam String id, @RequestParam String name, @RequestParam String login, @RequestParam String password, HttpSession session) {
        boolean result = false;

        User doUpdateUser = new User();

        doUpdateUser.setId(Long.parseLong(id));
        doUpdateUser.setName(name);
        doUpdateUser.setLogin(login);
        doUpdateUser.setPassword(password);

        result = userService.updateUser(doUpdateUser);
        String resultMessage = result ?
                "Пользователь " + doUpdateUser.getLogin() + " успешно обновлен!" :
                "Обновление пользователя " + doUpdateUser.getLogin() + " прошло неуспешно!";
        session.setAttribute("resultMessage", resultMessage);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String resultPageGet(Model model) {
        return "/resultPage";
    }

}
