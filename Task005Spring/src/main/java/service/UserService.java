package service;

import DAO.UserDAOImpl;
import DAO.UserDao;
import dbUtil.SessionFactoryUtil;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;

    public static UserService getInstance(){
        if(userService==null){
            userService= new UserService();
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
                System.out.println(getUserDao().validate(user));
                return getUserDao().validate(user);
            }
        }
        return false;
    }

    public boolean updateUser(User user) {
        if (!user.getName().isEmpty() & !user.getLogin().isEmpty() & !user.getPassword().isEmpty()) {
            if (getUserDao().getByLogin(user.getLogin()) == null || getUserDao().getById(user.getId()).getLogin().equals(user.getLogin())) {
                getUserDao().update(user);
                return getUserDao().validate(user);
            }
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        getUserDao().delete(id);
        return getUserDao().getById(id)==null;
    }

    public User getUserById(Long id) {
        return getUserDao().getById(id);
    }


    private UserDao<User,Long,String> getUserDao() {
        return UserDAOImpl.getInstance(SessionFactoryUtil.getInstance().openSession());
    }
}
