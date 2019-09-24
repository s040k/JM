package app.service;

import app.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(Long id);

    User getUserById(Long id);

    User validate(String login, String password);

    User getByLogin(String login);
}
