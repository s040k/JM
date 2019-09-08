package service;

import model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    boolean updateUser(User entity);

    boolean deleteUser(Long id);

    boolean addUser(User entity);

}
