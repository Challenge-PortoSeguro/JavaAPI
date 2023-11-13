package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.VeicCliente;
import org.example.models.repositories.VeicClienteRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VeicClienteService {
    private VeicClienteRepository repository = new VeicClienteRepository();

    public Response getAllService() throws SQLException {
        List<VeicCliente> veicsClientes = repository.findAll();
        if (veicsClientes.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma informação encontrada!").build();
        }
        return Response.status(Response.Status.OK).entity(veicsClientes).build();
    }


    public Response getByIdService(int id) throws SQLException {
        VeicCliente veicCliente = repository.find(id).orElse(null);
        if (veicCliente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("A informação solicitada não foi encontrada!").build();
        }
        return Response.status(Response.Status.OK).entity(veicCliente).build();
    }


    public Response insertService(VeicCliente veicCliente) throws SQLException {
        if (veicCliente == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua solicitação.").build();
        } else {
            repository.add(veicCliente);
            return Response.status(Response.Status.CREATED).build();
        }
    }


    public Response updateService(int id, VeicCliente veicCliente) throws SQLException {
        if (repository.find(id).isPresent()) {
            veicCliente.getId_cliente().setId_cliente(id);
            repository.update(veicCliente);
            Optional<VeicCliente> veicClienteAtualizado = repository.find(id);
            return Response.status(Response.Status.OK).entity(veicClienteAtualizado).build();
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
