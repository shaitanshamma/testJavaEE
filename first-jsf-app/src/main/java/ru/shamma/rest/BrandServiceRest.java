package ru.shamma.rest;

import ru.shamma.dao.BrandDao;
import ru.shamma.dao.CategoryDao;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/brands")
public interface BrandServiceRest {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(BrandDao brandDao);

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") Long id);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(BrandDao brandDao);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    BrandDao findByIdRest(@PathParam("id") Long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<BrandDao> findAll();

}
