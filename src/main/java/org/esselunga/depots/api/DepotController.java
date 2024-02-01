package org.esselunga.depots.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.esselunga.depots.dto.DepotDTO;
import org.esselunga.depots.service.DepotServiceImpl;
import org.esselunga.utils.exception.ApplicationException;
import org.esselunga.utils.exception.ServiceException;

import static jakarta.ws.rs.core.Response.ok;

@Path("/depot")
public class DepotController {

    @Inject
    DepotServiceImpl service;

    @GET
    @Path("/getDepot/{depotId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepotById(@PathParam("depotId") String depotId) throws ApplicationException {
        try {
            return ok(service.getDepotById(depotId)).build();

        } catch (ServiceException ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }

    @POST
    @Path("/insertDepot")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertDepot(DepotDTO depotDTO) throws ApplicationException {
        try {
            return ok(service.insertDepot(depotDTO)).build();

        } catch (ServiceException ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAll() throws ApplicationException {
        try {
            service.deleteAll();
            return ok().build();

        } catch (ServiceException ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }
}
