package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.ModalColab;
import org.example.models.repositories.ModalColabRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ModalColabService {
    private ModalColabRepository repository = new ModalColabRepository();

    public Response getAllService() throws SQLException {
        List<ModalColab> modaisColabs = repository.findAll();
        if (modaisColabs.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma informação encontrada!").build();
        }
        return Response.status(Response.Status.OK).entity(modaisColabs).build();
    }


    public Response getByIdService(int id) throws SQLException {
        ModalColab modalColab = repository.find(id).orElse(null);
        if (modalColab == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("A informação solicitada não foi encontrada!").build();
        }
        return Response.status(Response.Status.OK).entity(modalColab).build();
    }


    public Response insertService(ModalColab modalColab) throws SQLException {
        if (modalColab == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua solicitação.").build();
        } else {
            repository.add(modalColab);
            return Response.status(Response.Status.CREATED).build();
        }
    }


    public Response updateService(int id, ModalColab modalColab) throws SQLException {
        if (repository.find(id).isPresent()) {
            modalColab.getId_colab().setId_colab(id);
            repository.update(modalColab);
            Optional<ModalColab> modalColabAtualizado = repository.find(id);
            return Response.status(Response.Status.OK).entity(modalColabAtualizado).build();
        }

        return Response.status(Response.Status.NOT_FOUND).entity("Informação não encontrada!").build();
    }



    public Response deleteService(int id) throws SQLException {
        if (repository.find(id).isPresent()) {
            repository.delete(id);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Informação não encontrada!").build();
    }
}
