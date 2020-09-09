package ru.shamma.dao;


import ru.shamma.persist.Category;

public class CategoryDao {

    private Long id;

    private String title;

    public CategoryDao(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public CategoryDao() {
    }

    public CategoryDao(String title) {
        this.title = title;
    }

    public CategoryDao(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
