package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao<User, Long, String> {
    private Session session;
    private static UserDaoHibernateImpl userDAO;

    private UserDaoHibernateImpl() {
    }

    public static UserDaoHibernateImpl getInstance(Session session) {
        if (userDAO == null) {
            userDAO = new UserDaoHibernateImpl();
        }
        userDAO.session = session;
        return userDAO;
    }

    public List<User> getAll() {
        List<User> result = null;
        Query query = session.createQuery("from User");
        result = query.list();
        session.close();

        return result;
    }

    public void update(User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        try {
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void create(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        try {
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void delete(Long id) {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("delete from User where id = :idValue");
            query.setLong("idValue", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public boolean validate(User user) {
        boolean result = false;
        Query query = session.createQuery("from User where " +
                "name = :nameVal and " +
                "login = :loginVal and " +
                "password = :passwordVal"
        );

        query.setString("nameVal", user.getName());
        query.setString("loginVal", user.getLogin());
        query.setString("passwordVal", user.getPassword());
        result = !query.list().isEmpty();

        session.close();
        return result;
    }

    public User getById(Long id) {
        User result = null;
        Query query = session.createQuery("from User where id = :idVal");
        query.setLong("idVal", id);
        result = (User) query.uniqueResult();

        session.close();
        return result;
    }

    public User getByLogin(String login) {
        User result = null;
        Query query = session.createQuery("from User where login = :loginVal");
        query.setString("loginVal", login);
        result = (User) query.uniqueResult();

        session.close();
        return result;
    }


}
