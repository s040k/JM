package DAO;

import model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    User getById(Long id);

    void update(User entity);

    void delete(Long id);

    void create(User entity);

    boolean isExist(User entity);

    User validate(String login, String password);

    User getByLogin(String login);
}
