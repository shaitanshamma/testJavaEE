package ru.shamma.service;

import ru.shamma.dao.ProductDao;
import ru.shamma.persist.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @EJB
    private BrandRepository brandRepository;

    @Override
    @TransactionAttribute
    public void insert(ProductDao productDao) {
        Category category = categoryRepository.findById(productDao.getCategoryId()).get();
        Brand brand = brandRepository.findById(productDao.getBrandId()).get();
        Product product = new Product(
                productDao.getName(),
                productDao.getDescription(),
                productDao.getPrice(),
                category,
                brand);
        productRepository.insert(product);
    }

    @Override
    @TransactionAttribute
    public void update(ProductDao productDao) {
        Category category = categoryRepository.findById(productDao.getCategoryId()).orElse(null);
        Brand brand = brandRepository.findById(productDao.getBrandId()).orElse(null);
        Product product = new Product(
                productDao.getName(),
                productDao.getDescription(),
                productDao.getPrice(),
                category,
                brand);
        productRepository.update(product);
    }

    @Override
    @TransactionAttribute
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public Optional<ProductDao> findById(Long id) {
        return productRepository.findById(id).map(ProductDao::new);
    }

    @Override
    public List<ProductDao> findAll() {
        return productRepository.findAll().stream().map(ProductDao::new).collect(Collectors.toList());
    }
}
