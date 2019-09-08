package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSession(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAll() {
        List<User> result = null;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");
        result = query.list();

        return result;
    }

    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void create(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from User where id = :idValue");
        query.setLong("idValue", id);
        query.executeUpdate();

    }

    public boolean validate(User user) {
        boolean result = false;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where " +
                "name = :nameVal and " +
                "login = :loginVal and " +
                "password = :passwordVal"
        );

        query.setString("nameVal", user.getName());
        query.setString("loginVal", user.getLogin());
        query.setString("passwordVal", user.getPassword());
        result = !query.list().isEmpty();

        return result;
    }

    public User getById(Long id) {
        User result = null;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where id = :idVal");
        query.setLong("idVal", id);
        result = (User) query.uniqueResult();

        return result;
    }

    public User getByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        User result = null;
        Query query = session.createQuery("from User where login = :loginVal");
        query.setString("loginVal", login);
        result = (User) query.uniqueResult();

        return result;
    }


}
