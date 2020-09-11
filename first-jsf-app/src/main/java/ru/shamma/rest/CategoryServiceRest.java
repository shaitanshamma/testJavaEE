package ru.shamma.rest;

import ru.shamma.dao.CategoryDao;
import ru.shamma.dao.ProductDao;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/categories")
public interface CategoryServiceRest {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(CategoryDao categoryDao);

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") Long id);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(CategoryDao categoryDao);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    CategoryDao findByIdRest(@PathParam("id") Long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryDao> findAll();

}
