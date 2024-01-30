package org.esselunga.orders.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.esselunga.exception.ApplicationException;
import org.esselunga.exception.ServiceException;
import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.service.OrderServiceImpl;

import static jakarta.ws.rs.core.Response.ok;

@Path("/orders")
public class OrderController {

    @Inject
    OrderServiceImpl service;

    @POST
    @Path("/insert-order")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertOrder(OrderDTO orderDTO) throws ApplicationException {
        try {
            return ok(service.insertOrder(orderDTO)).build();

        } catch (ServiceException ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }
}
