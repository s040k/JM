package DAO;

import java.util.List;

public interface Dao <T,E> {
    public List<T> getAll();
    public T getById(E id);
    public void update (T entity);
    public void delete (E id);
    public void create (T entity);
}
