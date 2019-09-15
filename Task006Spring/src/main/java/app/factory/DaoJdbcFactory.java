package app.factory;

import app.DAO.UserDao;
import app.DAO.UserDaoJDBCimpl;
import app.dbUtil.DBHelper;

public class DaoJdbcFactory implements DaoFactory {
    @Override
    public UserDao createDao() {
        return UserDaoJDBCimpl.getInstance(DBHelper.getInstance().getConnection());
    }
}
