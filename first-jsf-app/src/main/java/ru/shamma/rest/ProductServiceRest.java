package ru.shamma.rest;

import ru.shamma.dao.ProductDao;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/products")
public interface ProductServiceRest {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductDao productDao);

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") Long id);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductDao productDao);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDao findByIdRest(@PathParam("id") Long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDao> findAll();

}
