package rikkei.academy.service;

import java.util.List;

public interface IGenericService<T>{
    List<T> findAll();
    void save(T t);
    T findByID(int id);
    void deleteByID(int id);
}
