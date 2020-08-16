package ru.shamma.persist;

public class Brand {
    private Long id;
    private String title;
    private String country;

    public Brand(Long id, String title, String country) {
        this.id = id;
        this.title = title;
        this.country = country;
    }

    public Brand(String title, String country) {
        this.title = title;
        this.country = country;
    }

    public Brand() {
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

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
