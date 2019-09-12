package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void create(User user) {
        entityManager.merge(user);
    }

    public void delete(Long id) {
        entityManager.remove(getById(id));
    }

    public boolean validate(User user) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where " +
                        "name = :nameVal and " +
                        "login = :loginVal and " +
                        "password = :passwordVal"
                , User.class);

        query.setParameter("nameVal", user.getName());
        query.setParameter("loginVal", user.getLogin());
        query.setParameter("passwordVal", user.getPassword());
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    public User getByLogin(String login) {
        User result = null;
        TypedQuery<User> query = entityManager.createQuery("select u from User u where login=:loginVal", User.class);
        query.setParameter("loginVal", login);
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {

        }
        return result;
    }


}
