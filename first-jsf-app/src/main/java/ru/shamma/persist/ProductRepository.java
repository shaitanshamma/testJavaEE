package ru.shamma.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
@Named
public class ProductRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(name = "ds")
    private EntityManager entityManager;

    public ProductRepository() {
    }

    @Transactional
    public void insert(Product product){
        entityManager.persist(product);
    }

    @Transactional
    public void update(Product product) {
        entityManager.merge(product);
    }
    @Transactional
    public void delete(long id)  {
        Product product= entityManager.find(Product.class, id);
        entityManager.remove(product);
    }

    public Optional<Product> findById(long id)  {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public List<Product> findAll() {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

}
