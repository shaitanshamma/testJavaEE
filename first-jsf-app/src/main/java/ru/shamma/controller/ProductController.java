package ru.shamma.controller;


import ru.shamma.dao.ProductDao;
import ru.shamma.persist.Category;
import ru.shamma.persist.CategoryRepository;
import ru.shamma.persist.Product;
import ru.shamma.persist.ProductRepository;
import ru.shamma.service.ProductService;
import ru.shamma.service.ProductServiceImpl;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    @EJB
    private CategoryRepository categoryRepository;

    private ProductDao productDao;

    public ProductDao getProduct() {
        return productDao;
    }

    public void setProduct(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<ProductDao> getAllProducts()  {
        return productService.findAll();
    }

    public String editProduct(ProductDao productDao) {
        this.productDao = productDao;
        return "/product.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductDao productDao) {
        productService.delete(productDao.getId());
    }

    public String createProduct() {
        this.productDao = new ProductDao();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveProduct(){
        if (productDao.getId() != null) {
            productService.update(productDao);
        } else {
            productService.insert(productDao);
        }
        return "/index.xhtml?faces-redirect=true";
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
