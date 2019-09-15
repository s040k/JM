package app.DAO;

import app.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;


//    private Session session;
//    private static UserDaoHibernateImpl userDAO;
//
//    private UserDaoHibernateImpl() {
//    }
//
//    public static UserDaoHibernateImpl getInstance(Configuration configuration) {
//        if (userDAO == null) {
//            userDAO = new UserDaoHibernateImpl();
//        }
//        userDAO.session = createSessionFactory(configuration).openSession();
//        return userDAO;
//    }

    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
    public void update(User user) {
        entityManager.merge(user);
    }

    public void create(User user) {
        entityManager.merge(user);
    }

    public void delete(Long id) {
        entityManager.remove(getById(id));
    }

    public boolean isExist(User user) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where " +
                        "name = :nameVal and " +
                        "login = :loginVal and " +
                        "password = :passwordVal and "+
                        "role = :roleVal"
                , User.class);

        query.setParameter("nameVal", user.getName());
        query.setParameter("loginVal", user.getLogin());
        query.setParameter("passwordVal", user.getPassword());
        query.setParameter("roleVal", user.getRole());
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
