package ru.shamma.controller;


import ru.shamma.dao.CategoryDao;
import ru.shamma.service.CategoryService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CategoryController implements Serializable {

    @EJB
    private CategoryService categoryService;

    private CategoryDao categoryDao;

    public CategoryDao getCategory() {
        return categoryDao;
    }

    public void setCategory(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<CategoryDao> getAllCategories()  {
        return categoryService.findAll();
    }

    public String editCategory(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
        return "/category.xhtml?faces-redirect=true";
    }

    public void deleteCategory(CategoryDao categoryDao)  {
        categoryService.delete(categoryDao.getId());
    }

    public String createCategory() {
        this.categoryDao = new CategoryDao();
        return "/category.xhtml?faces-redirect=true";
    }

    public String saveCategory() {
        if (categoryDao.getId() != null) {
            categoryService.update(categoryDao);
        } else {
            categoryService.insert(categoryDao);
        }
        return "/categories.xhtml?faces-redirect=true";
    }
}
