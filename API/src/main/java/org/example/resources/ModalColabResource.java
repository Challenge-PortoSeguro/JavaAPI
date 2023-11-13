package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.ModalColab;
import org.example.models.repositories.ModalColabRepository;
import org.example.services.ModalColabService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/modal-colaborador")
public class ModalColabResource {
    private ModalColabService service = new ModalColabService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws SQLException {
        return service.getAllService();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) throws SQLException {
        return service.getByIdService(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(ModalColab modalColab) throws SQLException {
        return service.insertService(modalColab);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, ModalColab modalColab) throws SQLException {
        return service.updateService(id, modalColab);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws SQLException {
        return service.deleteService(id);
    }
}
