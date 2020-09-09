package ru.shamma.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class BrandRepository {

    private static final Logger logger = LoggerFactory.getLogger(BrandRepository.class);

    @PersistenceContext(name = "ds")
    private EntityManager entityManager;

    public BrandRepository() {
    }

    @TransactionAttribute
    public void insert(Brand brand) {
        entityManager.persist(brand);
    }

    @TransactionAttribute
    public void update(Brand brand) {
        entityManager.merge(brand);
    }

    @TransactionAttribute
    public void delete(long id) {
        Brand brand = entityManager.find(Brand.class, id);
        entityManager.remove(brand);
    }

    public Optional<Brand> findById(long id) {
        Brand brand = entityManager.find(Brand.class, id);
        if (brand != null) {
            return Optional.of(brand);
        }
        return Optional.empty();
    }

    public List<Brand> findAll() {
        return entityManager.createQuery("from Brand", Brand.class).getResultList();
    }
}

