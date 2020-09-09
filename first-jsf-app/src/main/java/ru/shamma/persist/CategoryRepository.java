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
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(name = "ds")
    private EntityManager entityManager;

    public CategoryRepository() {
    }

    @TransactionAttribute
    public void insert(Category category) {
        entityManager.persist(category);
    }

    @TransactionAttribute
    public void update(Category category) {
        entityManager.merge(category);
    }

    @TransactionAttribute
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

