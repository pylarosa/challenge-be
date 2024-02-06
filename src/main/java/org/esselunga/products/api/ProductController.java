package org.esselunga.products.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.esselunga.products.dto.ProductDTO;
import org.esselunga.products.service.ProductServiceImpl;
import org.esselunga.utils.exception.ApplicationException;
import org.esselunga.utils.exception.ServiceException;

import static jakarta.ws.rs.core.Response.ok;

@Path("/products")
public class ProductController {

    @Inject
    ProductServiceImpl service;

    @GET
    @Path("/getProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() throws ApplicationException {
        try {
            return ok(service.getAllProducts()).build();

        } catch (ServiceException ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }

    @POST
    @Path("/insertProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertProduct(ProductDTO productDTO) throws ApplicationException {
        try {
            return ok(service.insertProduct(productDTO)).build();

        } catch (ServiceException ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }

    @GET
    @Path("/getProduct/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByOrderId(@PathParam("orderId") String orderId) throws ApplicationException {
        try {
            return ok(service.getProductById(orderId)).build();

        } catch (ServiceException ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }

    @DELETE
    public Response deleteAll() throws ApplicationException {
        try {
            service.deleteAll();
            return ok().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }
}
