package ru.shamma.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shamma.dao.ProductDao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Stateless
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(name = "ds")
    private EntityManager entityManager;

    public ProductRepository() {
    }


    public void insert(Product product){
        entityManager.persist(product);
    }


    public void update(Product product) {
        entityManager.merge(product);
    }

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

    public Optional<Product> findByName(String name) {
        Product product = entityManager.createQuery("from Product p where p.name LIKE ?1", Product.class).setParameter(1, name).getSingleResult();
        if (product != null) {
            return Optional.of(product);
        }
        return Optional.empty();
    }
}
