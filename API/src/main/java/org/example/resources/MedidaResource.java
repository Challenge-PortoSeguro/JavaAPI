package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.Medida;
import org.example.models.repositories.MedidaRepository;
import org.example.services.MedidaService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/medida")
public class MedidaResource {
    private MedidaService service = new MedidaService();

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
    public Response insert(Medida medida) throws SQLException {
        return service.insertService(medida);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Medida medida) throws SQLException {
        return service.updateService(id, medida);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws SQLException {
        return service.deleteService(id);
    }
}
