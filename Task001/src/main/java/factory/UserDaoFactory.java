package factory;

import DAO.UserDao;
import DAO.UserDaoHibernateImpl;
import DAO.UserDaoJDBCimpl;
import dbUtil.DBHelper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class UserDaoFactory {
    public static UserDao createDao(String path) {
        String method = getPropertyMethod(path);

        if (method.equals("hibernate")) {
            return UserDaoHibernateImpl.getInstance(createSessionFactory().openSession());
        } else if (method.equals("jdbc")) {
            return UserDaoJDBCimpl.getInstance(DBHelper.getInstance().getConnection());
        } else {
            throw new RuntimeException();
        }
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registryBuilder.build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static String getPropertyMethod(String path) {
        FileInputStream fis;
        Properties property = new Properties();
        String resultMethod = "";
        try {
            fis = new FileInputStream(path);
            property.load(fis);

            resultMethod = property.getProperty("db.method");
            System.out.println(resultMethod);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMethod;
    }
}
