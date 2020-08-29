package ru.shamma.service;

import ru.shamma.dao.CategoryDao;
import ru.shamma.persist.Category;
import ru.shamma.persist.CategoryRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class CategoryServiceImpl implements CategoryService {

    @EJB
    private CategoryRepository categoryRepository;


    @Override
    public void insert(CategoryDao categoryDao) {
        Category category = new Category(categoryDao.getId(),
                                          categoryDao.getTitle());
        categoryRepository.insert(category);
    }

    @Override
    public void update(CategoryDao categoryDao) {
        Category category = new Category(categoryDao.getId(),
                categoryDao.getTitle());
        categoryRepository.insert(category);;
    }

    @Override
    public void delete(long id) {
        categoryRepository.delete(id);
    }

    @Override
    public Optional<CategoryDao> findById(long id) {
        return categoryRepository.findById(id).map(CategoryDao::new);
    }

    @Override
    public List<CategoryDao> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDao::new).collect(Collectors.toList());
    }
}
