package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.Veiculo;
import org.example.models.repositories.VeiculoRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VeiculoService {

    private VeiculoRepository repository = new VeiculoRepository();

    public Response getAllService() throws SQLException {
        List<Veiculo> veiculos = repository.findAll();
        if (veiculos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum veiculo foi encontrado no banco de dados!").build();
        }
        return Response.status(Response.Status.OK).entity(veiculos).build();
    }


    public Response getByIdService(int id) throws SQLException {
        Veiculo veiculo = repository.find(id).orElse(null);
        if (veiculo == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("O veiculo não encontrado no banco de dados!").build();
        }
        return Response.status(Response.Status.OK).entity(veiculo).build();
    }


    public Response insertService(Veiculo veiculo) throws SQLException {
        if (veiculo == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua request.").build();
        } else {
            long idInserido = repository.add(veiculo);
            veiculo.setId_veiculo(Math.toIntExact(idInserido));
            return Response.status(Response.Status.CREATED).entity(veiculo).build();
        }
    }


    public Response updateService(int id, Veiculo veiculo) throws SQLException {
        if (repository.find(id).isPresent()) {
            veiculo.setId_veiculo(id);
            repository.update(veiculo);
            Optional<Veiculo> veiculoAtualizado = repository.find(id);
            return Response.status(Response.Status.OK).entity(veiculoAtualizado).build();
        }

        return Response.status(Response.Status.NOT_FOUND).entity("O veiculo não encontrado no banco de dados!").build();
    }


    public Response deleteService(int id) throws SQLException {
        if (repository.find(id).isPresent()) {
            repository.delete(id);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("O veiculo não encontrado no banco de dados!").build();
    }
}
