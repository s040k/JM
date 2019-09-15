package app.factory;

import app.DAO.UserDao;

public interface DaoFactory {
    public UserDao createDao ();
}
