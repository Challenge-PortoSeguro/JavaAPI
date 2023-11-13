package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.Medida;
import org.example.models.repositories.MedidaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MedidaService {

    private MedidaRepository repository = new MedidaRepository();

    public Response getAllService() throws SQLException {
        List<Medida> medidas = repository.findAll();

        if (medidas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma medida foi cadastrada no banco de dados!").build();
        }
        return Response.status(Response.Status.OK).entity(medidas).build();
    }


    public Response getByIdService(int id) throws SQLException {
        Medida medida = repository.find(id).orElse(null);
        if (medida == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Medida não encontrada no banco de dados").build();
        }
        return Response.status(Response.Status.OK).entity(medida).build();
    }


    public Response insertService(Medida medida) throws SQLException {
        if (medida == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua request.").build();
        } else {
            Long idInserido = repository.add(medida);
            medida.setId(Math.toIntExact(idInserido));
            return Response.status(Response.Status.CREATED).entity(medida).build();
        }
    }



    public Response updateService(int id, Medida medida) throws SQLException {
        if (repository.find(id).isPresent()) {
            medida.setId(id);
            repository.update(medida);
            Optional<Medida> medidaAtualizada = repository.find(id);
            return Response.status(Response.Status.OK).entity(medidaAtualizada).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Medida não encontrada no banco de dados!").build();
    }


    public Response deleteService(int id) throws SQLException {
        if (repository.find(id).isPresent()) {
            repository.delete(id);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Medida não encontrada no banco de dados!").build();
    }
}
