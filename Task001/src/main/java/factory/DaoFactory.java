package factory;

import DAO.UserDao;

public interface DaoFactory {
    public UserDao createDao ();
}
