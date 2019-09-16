package app.service;

import app.DAO.UserDao;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public boolean addUser(User user) {
        if (!user.getName().isEmpty() & !user.getLogin().isEmpty() & !user.getPassword().isEmpty()) {
            if (userDao.getByLogin(user.getLogin()) == null) {
                userDao.create(user);
                return userDao.isExist(user);
            }
        }
        return false;
    }

    public boolean updateUser(User user) {
        if (!user.getName().isEmpty() & !user.getLogin().isEmpty() & !user.getPassword().isEmpty()) {
            if (userDao.getByLogin(user.getLogin()) == null || userDao.getById(user.getId()).getLogin().equals(user.getLogin())) {
                userDao.update(user);
                return userDao.isExist(user);
            }
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        userDao.delete(id);
        return userDao.getById(id) == null;
    }

    public User getUserById(Long id) {
        return id != null ? userDao.getById(id) : null;
    }

    public User validate(String login, String password) {
        return userDao.validate(login, password);
    }

    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }
}
