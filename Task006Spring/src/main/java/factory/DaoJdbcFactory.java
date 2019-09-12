package factory;

import DAO.UserDao;
import DAO.UserDaoJDBCimpl;
import dbUtil.DBHelper;

public class DaoJdbcFactory implements DaoFactory {
    @Override
    public UserDao createDao() {
        return UserDaoJDBCimpl.getInstance(DBHelper.getInstance().getConnection());
    }
}
