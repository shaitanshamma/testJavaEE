package ru.shamma.controller;

import ru.shamma.dao.ProductDao;
import ru.shamma.service.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    CartService cartService;

    public List<ProductDao> getAllProducts()  {
        return cartService.getAll();
    }

    public void add(ProductDao productDao){
        cartService.add(productDao);
    }

    public void delete(ProductDao productDao){
        cartService.delete(productDao);
    }
}
