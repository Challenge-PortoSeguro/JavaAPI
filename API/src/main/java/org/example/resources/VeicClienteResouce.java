package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.VeicCliente;
import org.example.models.repositories.VeicClienteRepository;
import org.example.services.VeicClienteService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/veiculo-cliente")
public class VeicClienteResouce {
    private VeicClienteService service = new VeicClienteService();

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
    public Response insert(VeicCliente veicCliente) throws SQLException {
        return service.insertService(veicCliente);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, VeicCliente veicCliente) throws SQLException {
        return service.updateService(id, veicCliente);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws SQLException {
        return service.deleteService(id);
    }
}
