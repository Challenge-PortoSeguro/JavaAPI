package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.Cliente;
import org.example.models.Colaborador;
import org.example.models.repositories.ColaboradorRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ColaboradorService {
    private ColaboradorRepository repository = new ColaboradorRepository();

    public Response getAllService() throws SQLException {
        List<Colaborador> colaboradores = repository.findAll();
        if (colaboradores.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma colaborador cadastrado no banco de dados!").build();
        }
        return Response.status(Response.Status.OK).entity(colaboradores).build();
    }


    public Response getByIdService(int id) throws SQLException {
        Colaborador colaborador = repository.find(id).orElse(null);
        if (colaborador == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("O colaborador não encontrado no banco de dados").build();
        }
        return Response.status(Response.Status.OK).entity(colaborador).build();
    }

    public Response LoginService(Colaborador credenciais) throws SQLException {
        Colaborador colaborador = repository.findByEmail(credenciais.getEmail_colab()).orElse(null);

        if (colaborador == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Email não encontrado! Tente novamente.").build();
        } else {
            if (credenciais.getSenha_colab().equals(colaborador.getSenha_colab())) {
                return Response.status(Response.Status.ACCEPTED).entity(colaborador).build();
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity("Senha incorreta! Tente novamente.").build();
            }
        }
    }


    public Response insertService(Colaborador colaborador) throws SQLException {
        if (colaborador == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua request.").build();
        } else {
            long idInserido = repository.add(colaborador);
            colaborador.setId_colab(Math.toIntExact(idInserido));
            return Response.status(Response.Status.CREATED).entity(colaborador).build();
        }
    }


    public Response updateService(int id, Colaborador colaborador) throws SQLException {
        if (repository.find(id).isPresent()) {
            colaborador.setId_colab(id);
            repository.update(colaborador);
            Optional<Colaborador> colaboradorAtualizado = repository.find(id);
            return Response.status(Response.Status.OK).entity(colaboradorAtualizado).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Colaborador não encontrado!").build();
    }


    public Response deleteService(int id) throws SQLException {
        if (repository.find(id).isPresent()) {
            repository.delete(id);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Colaborador não encontrado!").build();
    }
}
