package app.controller.rest;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("rest/users")
public class RestUserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity addUser(@RequestBody User user) {
        boolean result;
        user.setId(null);

        try {
            result = userService.addUser(user);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            result = false;
        }

        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity updateUser(@RequestBody User user) {
        boolean result;

        try {
            result = userService.updateUser(user);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            result = false;
        }

        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        boolean result = userService.deleteUser(id);

        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
