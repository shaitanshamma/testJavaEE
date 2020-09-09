package ru.shamma.service;

import ru.shamma.dao.ProductDao;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {

    void add(ProductDao productDao);

    void delete(ProductDao productDao);

    List<ProductDao> getAll();
}
