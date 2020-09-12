package ru.shamma.service;

import ru.shamma.dao.ProductDao;
import ru.shamma.persist.*;
import ru.shamma.rest.ProductServiceRest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@WebService(endpointInterface = "ru.shamma.service.ProductServiceWs", serviceName = "ProductService")
public class ProductServiceImpl implements ProductService, ProductServiceWs, ProductServiceRest {

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
        Product product = productRepository.findById(productDao.getId()).get();
        product.setName(productDao.getName());
        product.setBrand(brand);
        product.setCategory(category);
        product.setDescription(productDao.getDescription());
        product.setPrice(productDao.getPrice());
        productRepository.update(product);
    }

    @Override
    public ProductDao findByIdRest(Long id) {
        return findById(id).get();
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

    @Override
    public ProductDao findByIdWs(Long id) {
        return findById(id).get();
    }

    @Override
    public ProductDao findByName(String name) {
        return productRepository.findByName(name).map(ProductDao::new).get();
    }

    @Override
    public List<ProductDao> findByCategoryId(long id) {
        return productRepository.findByCategoryId(id).stream().map(ProductDao::new).collect(Collectors.toList());
    }
}
