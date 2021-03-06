package service;

import DAO.UserDao;
import factory.DaoFactory;
import factory.UserDaoFactory;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserService() {
    }

    public List<User> getAllUsers() {
        return getUserDao().getAll();
    }

    public boolean addUser(User user) {
        if (!user.getName().isEmpty() & !user.getLogin().isEmpty() & !user.getPassword().isEmpty()) {
            if (getUserDao().getByLogin(user.getLogin()) == null) {
                getUserDao().create(user);
                return getUserDao().isExist(user);
            }
        }
        return false;
    }

    public boolean updateUser(User user) {
        if (!user.getName().isEmpty() & !user.getLogin().isEmpty() & !user.getPassword().isEmpty()) {
            if (getUserDao().getByLogin(user.getLogin()) == null || getUserDao().getById(user.getId()).getLogin().equals(user.getLogin())) {
                getUserDao().update(user);
                return getUserDao().isExist(user);
            }
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        getUserDao().delete(id);
        return getUserDao().getById(id) == null;
    }

    public User getUserById(Long id) {
        return id != null ? getUserDao().getById(id) : null;
    }

    public User validate(String login, String password) {
        return getUserDao().validate(login, password);
    }

    private UserDao getUserDao() {
        String path = "H:\\JM\\GitProject\\Task001\\src\\main\\java\\resources\\daoConfig.properties";
        DaoFactory daoFactory = UserDaoFactory.getDaoFactoryByProperty(path);
        return daoFactory.createDao();
    }
}
