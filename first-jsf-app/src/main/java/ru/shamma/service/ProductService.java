package ru.shamma.service;

import ru.shamma.dao.ProductDao;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface ProductService {

    void insert(ProductDao productDao);

    void update(ProductDao productDao);

    void delete(Long id);

    Optional<ProductDao> findById(Long id);

    List<ProductDao> findAll();
}
