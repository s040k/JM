package factory;

import DAO.UserDao;
import DAO.UserDaoHibernateImpl;
import dbUtil.DBHelper;

public class DaoHibernateFactory implements DaoFactory {
    @Override
    public UserDao createDao() {
        return UserDaoHibernateImpl.getInstance(DBHelper.getInstance().getConfiguration());
    }
}
