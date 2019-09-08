package app.service;

import app.DAO.UserDao;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ComponentScan(basePackages = "app.DAO")
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAll();
    }
    @Transactional
    public boolean addUser(User user) {
        if (!user.getName().isEmpty() & !user.getLogin().isEmpty() & !user.getPassword().isEmpty()) {
            if (userDao.getByLogin(user.getLogin()) == null) {
                userDao.create(user);
                System.out.println(userDao.validate(user));
                return userDao.validate(user);
            }
        }
        return false;
    }
    @Transactional
    public boolean updateUser(User user) {
        if (!user.getName().isEmpty() & !user.getLogin().isEmpty() & !user.getPassword().isEmpty()) {
            if (userDao.getByLogin(user.getLogin()) == null || userDao.getById(user.getId()).getLogin().equals(user.getLogin())) {
                userDao.update(user);
                return userDao.validate(user);
            }
        }
        return false;
    }
    @Transactional
    public boolean deleteUser(Long id) {
        userDao.delete(id);
        return userDao.getById(id) == null;
    }
    @Transactional
    public User getUserById(Long id) {
        return userDao.getById(id);
    }


}
