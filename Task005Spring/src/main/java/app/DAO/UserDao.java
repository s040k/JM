package app.DAO;

import app.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    User getById(Long id);

    void update(User entity);

    void delete(Long id);

    void create(User entity);

    boolean validate(User entity);

    User getByLogin(String login);
}
