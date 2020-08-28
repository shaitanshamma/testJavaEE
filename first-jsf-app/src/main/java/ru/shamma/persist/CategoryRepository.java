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
public class CategoryRepository implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(name = "ds")
    private EntityManager entityManager;

    public CategoryRepository() {
    }

    @Transactional
    public void insert(Category category) {
        entityManager.persist(category);
    }

    @Transactional
    public void update(Category category) {
        entityManager.merge(category);
    }

    @Transactional
    public void delete(long id) {
        Category category = entityManager.find(Category.class, id);
        entityManager.remove(category);
    }

    public Optional<Category> findById(long id) {
        Category category = entityManager.find(Category.class, id);
        if (category != null) {
            return Optional.of(category);
        }
        return Optional.empty();
    }

    public List<Category> findAll() {
        return entityManager.createQuery("from Category", Category.class).getResultList();
    }
}

