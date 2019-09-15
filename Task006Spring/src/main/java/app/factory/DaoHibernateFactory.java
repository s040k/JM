package app.factory;

import app.DAO.UserDao;

public class DaoHibernateFactory implements DaoFactory {
    @Override
    public UserDao createDao() {
      //  return UserDaoHibernateImpl.getInstance(DBHelper.getInstance().getConfiguration());
        return null;
    }
}
