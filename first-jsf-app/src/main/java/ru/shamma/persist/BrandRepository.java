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
public class BrandRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(BrandRepository.class);

    @PersistenceContext(name = "ds")
    private EntityManager entityManager;

    public BrandRepository() {
    }

    @Transactional
    public void insert(Brand brand) {
        entityManager.persist(brand);
    }

    @Transactional
    public void update(Brand brand) {
        entityManager.merge(brand);
    }

    @Transactional
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

