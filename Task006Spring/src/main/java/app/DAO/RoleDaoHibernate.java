package app.DAO;

import app.model.Role;
import app.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoHibernate implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String roleName) {
        Role result = null;

        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where nameRole=:nameRoleVal", Role.class);
        query.setParameter("nameRoleVal", roleName);
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {

        }
        return result;
    }

    @Override
    public List<Role> getAll() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

}
