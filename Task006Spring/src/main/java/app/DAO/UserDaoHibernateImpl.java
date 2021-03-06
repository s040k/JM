package app.DAO;

import app.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    public void update(User user) {
      entityManager.merge(user);
    }

    public void create(User user) {
        entityManager.persist(user);
    }

    public void delete(Long id) {
        entityManager.remove(getById(id));
    }

    public boolean isExist(User user) {
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

    @Override
    public User validate(String login, String password) {
        User result = null;
        TypedQuery<User> query = entityManager.createQuery("select u from User u where " +
                        "login = :loginVal and " +
                        "password = :passwordVal"
                , User.class);
        query.setParameter("loginVal", login);
        query.setParameter("passwordVal", password);
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
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
