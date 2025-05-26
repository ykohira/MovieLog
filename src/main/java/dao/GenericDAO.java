package dao;

import java.util.List;

public interface GenericDAO<T> {
    void add(T entity);
    T getById(int id);
    List<T> getAll();
    void update(T entity);
    void delete(int id);
}

