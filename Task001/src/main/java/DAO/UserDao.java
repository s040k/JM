package DAO;

import java.util.List;

public interface UserDao<T, E, K> {
    public List<T> getAll();

    public T getById(E id);

    public void update(T entity);

    public void delete(E id);

    public void create(T entity);

    public boolean validate(T entity);

    public T getByLogin(K login);
}
