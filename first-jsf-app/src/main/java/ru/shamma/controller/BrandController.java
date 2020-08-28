package ru.shamma.controller;


import ru.shamma.persist.Brand;
import ru.shamma.persist.BrandRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@SessionScoped
@Named
public class BrandController implements Serializable {

    @EJB
    private BrandRepository brandRepository;

    private Brand brand;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    public String editBrand(Brand brand) {
        this.brand = brand;
        return "/brand.xhtml?faces-redirect=true";
    }

    public void deleteBrand(Brand brand)  {
        brandRepository.delete(brand.getId());
    }

    public String createBrand() {
        this.brand = new Brand();
        return "/brand.xhtml?faces-redirect=true";
    }

    public String saveBrand()  {
        if (brand.getId() != null) {
            brandRepository.update(brand);
        } else {
            brandRepository.insert(brand);
        }
        return "/brands.xhtml?faces-redirect=true";
    }
}
