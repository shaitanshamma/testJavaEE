package ru.shamma.service;

import ru.shamma.dao.CategoryDao;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface CategoryService {

    void insert(CategoryDao categoryDao);

    void update(CategoryDao categoryDao);

    void delete(long id);

    Optional<CategoryDao> findById(long id);

    public List<CategoryDao> findAll();
}
