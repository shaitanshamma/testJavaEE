package ru.shamma.controller;


import ru.shamma.dao.BrandDao;
import ru.shamma.service.BrandService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@SessionScoped
@Named
public class BrandController implements Serializable {

    @EJB
    private BrandService brandService;

    private BrandDao brandDao;

    public BrandDao getBrand() {
        return brandDao;
    }

    public void setBrand(BrandDao brand) {
        this.brandDao = brand;
    }

    public List<BrandDao> getAllBrand() {
        return brandService.findAll();
    }

    public String editBrand(BrandDao brandDao) {
        this.brandDao = brandDao;
        return "/brand.xhtml?faces-redirect=true";
    }

    public void deleteBrand(BrandDao brandDao)  {
        brandService.delete(brandDao.getId());
    }

    public String createBrand() {
        this.brandDao = new BrandDao();
        return "/brand.xhtml?faces-redirect=true";
    }

    public String saveBrand()  {
        if (brandDao.getId() != null) {
            brandService.update(brandDao);
        } else {
            brandService.insert(brandDao);
        }
        return "/brands.xhtml?faces-redirect=true";
    }
}
