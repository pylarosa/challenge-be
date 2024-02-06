package org.esselunga.orders.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.dto.OrderFilter;
import org.esselunga.orders.dto.OrderPatchDTO;
import org.esselunga.orders.service.OrderServiceImpl;
import org.esselunga.utils.exception.ApplicationException;
import org.esselunga.utils.exception.ServiceException;

import static jakarta.ws.rs.core.Response.ok;

@Path("/orders")
public class OrderController {

    @Inject
    OrderServiceImpl service;

    @GET
    @Path("/getOrders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() throws ApplicationException {
        try {
            return ok(service.getAllOrders()).build();

        } catch (ServiceException ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }

    @POST
    @Path("/insertOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertOrder(OrderDTO orderDTO) throws ApplicationException {
        try {
            OrderDTO response = service.insertOrder(orderDTO);
            return ok(response).build();

        } catch (ServiceException ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }

    @PATCH
    @Path("/update-order")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(OrderPatchDTO orderPatch) throws ApplicationException {
        try {
            orderPatch.setUpdated(true);
            return Response.ok(service.updateOrder(orderPatch)).build();

        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }

    @POST
    @Path("/get-filtered-orders")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilteredOrders(OrderFilter orderFilter) throws ApplicationException {
        try {
            return Response.ok(service.getFilteredOrders(orderFilter)).build();

        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }

    @DELETE
    @Path("/deleteAll")
    public Response deleteAll() throws ApplicationException {
        try {
            service.deleteAll();
            return ok().build();

        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
    }

    // TODO getOrderById
}
