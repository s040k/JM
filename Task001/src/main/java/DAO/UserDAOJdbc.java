package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJdbc implements UserDao<User, Long, String> {
    private Connection connection;
    private static UserDAOJdbc userDAOJdbc;

    private UserDAOJdbc() {
    }

    public static UserDAOJdbc getInstance(Connection connection) {
        if (userDAOJdbc == null) {
            userDAOJdbc = new UserDAOJdbc();
        }
        userDAOJdbc.connection = connection;
        return userDAOJdbc;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from users_table;");
            while (resultSet.next()) {
                result.add(getById(resultSet.getLong("id")));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !result.isEmpty() ? result : null;
    }

    @Override
    public User getById(Long id) {
        User resultClient = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from users_table where id=?;")) {

            preparedStatement.setString(1, id + "");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                resultClient = new User();
                resultClient.setId(resultSet.getLong("id"));
                resultClient.setName(resultSet.getString("name"));
                resultClient.setLogin(resultSet.getString("login"));
                resultClient.setPassword(resultSet.getString("password"));
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultClient;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE users_table SET name =?, login = ?, password = ? WHERE id = ?")) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE from users_table where id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into users_table(name, login, password) values (?,?,?);")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword() + "");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validate(User user) {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from users_table where name=? and login=? and password=?;")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            result = resultSet.next();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User getByLogin(String login) {
        User resultUser = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from users_table where login=?;")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                resultUser = getById(resultSet.getLong("id"));
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultUser;
    }


}
