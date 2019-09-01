package DAO;

import java.util.List;

public interface UserDao<T, E, K> {
    public List<T> getAll();

    public T getById(E id);

    public void update(T entity);

    public void delete(E id);

    public void create(T entity);

    public boolean isExist(T entity);

    public T validate(K login, K password);

    public T getByLogin(K login);
}
