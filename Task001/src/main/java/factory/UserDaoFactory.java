package factory;

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
