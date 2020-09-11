package ru.shamma.service;


import ru.shamma.dao.BrandDao;
import ru.shamma.persist.Brand;
import ru.shamma.persist.BrandRepository;
import ru.shamma.rest.BrandServiceRest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class BrandServiceImpl implements BrandService, BrandServiceRest {

    @EJB
    private BrandRepository brandRepository;


    @Override
    public void insert(BrandDao brandDao) {
        Brand brand = new Brand(brandDao.getId(),
                                brandDao.getTitle(),
                                brandDao.getCountry());
        brandRepository.insert(brand);
    }

    @Override
    public void delete(Long id) {
        brandRepository.delete(id);
    }

    @Override
    public void update(BrandDao brandDao) {
        Brand brand = new Brand(brandDao.getId(),
                brandDao.getTitle(),
                brandDao.getCountry());
        brandRepository.insert(brand);
    }

    @Override
    public BrandDao findByIdRest(Long id) {
        return findById(id).get();
    }

    @Override
    public void delete(long id) {
        brandRepository.delete(id);
    }

    @Override
    public Optional<BrandDao> findById(long id) {
        return brandRepository.findById(id).map(BrandDao::new);
    }

    @Override
    public List<BrandDao> findAll() {
        return brandRepository.findAll().stream().map(BrandDao::new).collect(Collectors.toList());
    }
}
