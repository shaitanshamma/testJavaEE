package ru.shamma.service;

import ru.shamma.dao.BrandDao;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface BrandService {

    void insert(BrandDao brandDao);

    void update(BrandDao brandDao);

    void delete(long id);

    Optional<BrandDao> findById(long id);

    public List<BrandDao> findAll();
}
