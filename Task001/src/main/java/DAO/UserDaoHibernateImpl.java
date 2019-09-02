package DAO;

import dbUtil.DBHelper;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Session session;
    private static UserDaoHibernateImpl userDAO;

    private UserDaoHibernateImpl() {
    }

    public static UserDaoHibernateImpl getInstance(Configuration configuration) {
        if (userDAO == null) {
            userDAO = new UserDaoHibernateImpl();
        }
        userDAO.session = createSessionFactory(configuration).openSession();
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

    public boolean isExist(User user) {
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

    @Override
    public User validate(String login, String password) {
        User result = null;
        Query query = session.createQuery("from User where " +
                "login = :loginVal and " +
                "password = :passwordVal"
        );
        query.setString("loginVal", login);
        query.setString("passwordVal", password);
        result = (User) query.uniqueResult();

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

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registryBuilder.build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

}
