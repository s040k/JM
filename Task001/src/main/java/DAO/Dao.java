package DAO;

import java.util.List;

public interface Dao <T,E> {
    public List<T> getAll();
    public T getById(E id);
    public void update (T entity);
    public void delete (T entity);
    public void create (T entity);
}
