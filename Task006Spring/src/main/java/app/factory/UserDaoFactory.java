package app.factory;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class UserDaoFactory {

    public static DaoFactory getDaoFactoryByProperty(String path) {
        String method = getPropertyMethod(path);

         if (method.equals("jdbc")) {
            return new DaoJdbcFactory();
        } else {
            return new DaoHibernateFactory();
        }
    }

    private static String getPropertyMethod(String path) {
        FileInputStream fis;
        Properties property = new Properties();
        String resultMethod = "";
        try {
            fis = new FileInputStream(UserDaoFactory.class.getClassLoader().getResource("daoConfig.properties").getPath());
            property.load(fis);

            resultMethod = property.getProperty("db.method");
            System.out.println(UserDaoFactory.class.getClassLoader().getResource("daoConfig.properties"));
            System.out.println(UserDaoFactory.class.getClassLoader().getResource("daoConfig.properties").getAuthority());
            System.out.println(UserDaoFactory.class.getClassLoader().getResource("daoConfig.properties").getPath());
            System.out.println(resultMethod);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMethod;
    }
}
