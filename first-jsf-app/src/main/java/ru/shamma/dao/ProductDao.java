package ru.shamma.dao;

import ru.shamma.persist.Brand;
import ru.shamma.persist.Category;
import ru.shamma.persist.Product;

import java.math.BigDecimal;

public class ProductDao {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String brandTitle;

    private String categoryTitle;

    private Long categoryId;

    private Long brandId;

    public ProductDao() {
    }

    public ProductDao(Long id, String name, String description, BigDecimal price, Category category, Brand brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = category.getId();
        this.categoryTitle = category.getTitle();
        this.brandId = brand.getId();
        this.brandTitle = brand.getTitle();
    }

    public ProductDao(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        if (product.getCategory() != null) {
            this.categoryId = product.getCategory().getId();
            this.categoryTitle = product.getCategory().getTitle();
        }
        if (product.getBrand() != null) {
            this.brandId = product.getBrand().getId();
            this.brandTitle = product.getBrand().getTitle();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBrandTitle() {
        return brandTitle;
    }

    public void setBrandTitle(String brandTitle) {
        this.brandTitle = brandTitle;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
