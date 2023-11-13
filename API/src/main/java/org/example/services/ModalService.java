package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.Modal;
import org.example.models.repositories.ModalRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ModalService {

    private ModalRepository repository = new ModalRepository();

    public Response getAllService() throws SQLException {
        List<Modal> modais = repository.findAll();
        if (modais.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum modal foi encontrado no banco de dados!").build();
        }
        return Response.status(Response.Status.OK).entity(modais).build();
    }


    public Response getByIdService(int id) throws SQLException {
        Modal modal = repository.find(id).orElse(null);
        if (modal == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Modal não foi encontrado no banco de dados!").build();
        }
        return Response.status(Response.Status.OK).entity(modal).build();
    }


    public Response insertService(Modal modal) throws SQLException {
        if (modal == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua request.").build();
        } else {
            long idInserido = repository.add(modal);
            modal.setId_modal(Math.toIntExact(idInserido));
            return Response.status(Response.Status.CREATED).entity(modal).build();
        }
    }


    public Response updateService(int id, Modal modal) throws SQLException {
        if (repository.find(id).isPresent()) {
            modal.setId_modal(id);
            repository.update(modal);
            Optional<Modal> modalAtualizado = repository.find(id);
            return Response.status(Response.Status.OK).entity(modalAtualizado).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Modal não encontrado no banco de dados!").build();
    }


    public Response deleteService(int id) throws SQLException {
        if (repository.find(id).isPresent()) {
            repository.delete(id);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Modal não encontrado no banco de dados!").build();
    }
}
