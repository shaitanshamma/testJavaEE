package ru.shamma.service;

import ru.shamma.dao.ProductDao;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductServiceWs {

    @WebMethod
    List<ProductDao> findAll();

    @WebMethod
    ProductDao findByIdWs(Long id);

    @WebMethod
    ProductDao findByName(String name);
}
