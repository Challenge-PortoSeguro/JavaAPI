package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.Cliente;
import org.example.models.Colaborador;
import org.example.models.repositories.ColaboradorRepository;
import org.example.services.ColaboradorService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Path("/colaborador")
public class ColaboradorResource {
    private ColaboradorService service = new ColaboradorService();

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
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Colaborador colaborador) throws SQLException {
        return service.LoginService(colaborador);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Colaborador colaborador) throws SQLException {
        return service.insertService(colaborador);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Colaborador colaborador) throws SQLException {
        return service.updateService(id, colaborador);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws SQLException {
        return service.deleteService(id);
    }
}
