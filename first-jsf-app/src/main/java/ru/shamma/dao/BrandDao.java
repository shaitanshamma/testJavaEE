package ru.shamma.dao;


import ru.shamma.persist.Brand;

public class BrandDao {

    private Long id;

    private String title;

    private String country;

    public BrandDao(Long id, String title, String country) {
        this.id = id;
        this.title = title;
        this.country = country;
    }

    public BrandDao(String title, String country) {
        this.title = title;
        this.country = country;
    }

    public BrandDao(Brand brand) {
        this.id = brand.getId();
        this.country = brand.getCountry();
        this.title = brand.getTitle();
    }

    public BrandDao() {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
