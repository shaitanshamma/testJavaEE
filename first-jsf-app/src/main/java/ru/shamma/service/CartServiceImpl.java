package ru.shamma.service;

import ru.shamma.dao.ProductDao;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Stateful
public class CartServiceImpl implements CartService, Serializable {

    public CartServiceImpl() {
    }

    private List<ProductDao> cart = new ArrayList<>();

    @Override
    public void add(ProductDao productDao) {
        cart.add(productDao);
    }

    @Override
    public void delete(ProductDao productDao) {
        cart.remove(productDao);
    }

    @Override
    public List<ProductDao> getAll() {
        return cart;
    }
}
