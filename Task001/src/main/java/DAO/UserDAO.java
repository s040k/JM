package DAO;

import models.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDAO implements Dao<User, Long> {
    private Session session;
    private static UserDAO userDAO;

    private UserDAO() {
    }

    public static UserDAO getInstance(Session session) {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        userDAO.session = session;
        return userDAO;
    }

    public List<User> getAll() {
        List<User> result = null;
        Criteria criteria = session.createCriteria(User.class);
        result = criteria.list();
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
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("name", user.getName()));
        criteria.add(Restrictions.eq("login", user.getLogin()));
        criteria.add(Restrictions.eq("password", user.getPassword()));
        result = !criteria.list().isEmpty();
        session.close();
        return result;
    }

    public User getById(Long id) {
        User result = null;
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        result = (User) criteria.uniqueResult();
        session.close();
        return result;
    }

    public User getByLogin(String login) {
        User result = null;
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        result = (User) criteria.uniqueResult();
        session.close();
        return result;
    }


}
