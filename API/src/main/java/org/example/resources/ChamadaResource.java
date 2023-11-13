package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.Chamada;
import org.example.models.repositories.ChamadaRepository;
import org.example.services.ChamadaService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/chamada")
public class ChamadaResource {
    private ChamadaService service = new ChamadaService();

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
    public Response insert(Chamada chamada) throws SQLException {
        return service.insertService(chamada);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Chamada chamada) throws SQLException {
        return service.updateService(id, chamada);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws SQLException {
        return service.deleteService(id);
    }
}
