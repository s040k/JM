package dbUtil;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConnectionFactoryUtil {
    private static ConnectionFactoryUtil connectionFactoryUtil;

    private ConnectionFactoryUtil() {
    }

    public static ConnectionFactoryUtil getInstance(){
        if(connectionFactoryUtil==null){
            connectionFactoryUtil = new ConnectionFactoryUtil();
        }
        return connectionFactoryUtil;
    }

    public Connection getSqlConnetcion() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());


            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("mydbteset?").          //db name
                    append("serverTimezone=" + TimeZone.getDefault().getID() + "&").//TimeZone
                    append("user=root&").          //login
                    append("password=root");       //password


            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}

