package ru.shamma.controller;


import ru.shamma.persist.Category;
import ru.shamma.persist.CategoryRepository;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CategoryController implements Serializable {

    @EJB
    private CategoryRepository categoryRepository;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getAllCategories()  {
        return categoryRepository.findAll();
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category)  {
        categoryRepository.delete(category.getId());
    }

    public String createCategory() {
        this.category = new Category();
        return "/category.xhtml?faces-redirect=true";
    }

    public String saveCategory() {
        if (category.getId() != null) {
            categoryRepository.update(category);
        } else {
            categoryRepository.insert(category);
        }
        return "/categories.xhtml?faces-redirect=true";
    }
}
