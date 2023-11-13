package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.Veiculo;
import org.example.models.repositories.VeiculoRepository;
import org.example.services.VeiculoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/veiculo")
public class VeiculoResource {
    private VeiculoService service = new VeiculoService();

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
    public Response insert(Veiculo veiculo) throws SQLException {
        return service.insertService(veiculo);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Veiculo veiculo) throws SQLException {
        return service.updateService(id, veiculo);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws SQLException {
        return service.deleteService(id);
    }
}
