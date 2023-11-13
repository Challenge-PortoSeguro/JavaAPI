package org.example.models.repositories;

import org.example.infrastructure.database.DataBaseFactory;
import org.example.models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepository {
    public List<Cliente> findAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<Cliente>();
        String query = "SELECT * FROM T_PA_CLIENTE ORDER BY 1 ASC";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                Cliente cliente = new Cliente(
                        rs.getInt("ID_CLIENTE"),
                        rs.getString("NM_CLIENTE"),
                        rs.getLong("CPF_CLIENTE"),
                        rs.getString("GENERO_CLIENTE"),
                        rs.getLong("TELEFONE_CLIENTE"),
                        rs.getString("DT_NASC_CLIENTE"),
                        rs.getString("ENDERECO_CLIENTE"),
                        rs.getString("EMAIL_CLIENTE"),
                        rs.getString("SENHA_CLIENTE")
                );

                clientes.add(cliente);
            }

            return clientes;
        }
        catch (SQLException e) {
            if(e.getErrorCode() == 1017) {
                throw new SQLException("Falha de autenticação ao conectar ao banco de dados.", e);
            } else if(e.getErrorCode() == 904) {
                throw new SQLException("A query contém uma coluna inválida.", e);
            } else {
                throw new SQLException("Erro ao executar a query.", e);
            }
        }
    }

    public Optional<Cliente> find(int id) throws SQLException {
        String query = "SELECT * FROM T_PA_CLIENTE WHERE ID_CLIENTE = ?";

        try(Connection connection = DataBaseFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("ID_CLIENTE"),
                            rs.getString("NM_CLIENTE"),
                            rs.getLong("CPF_CLIENTE"),
                            rs.getString("GENERO_CLIENTE"),
                            rs.getLong("TELEFONE_CLIENTE"),
                            rs.getString("DT_NASC_CLIENTE"),
                            rs.getString("ENDERECO_CLIENTE"),
                            rs.getString("EMAIL_CLIENTE"),
                            rs.getString("SENHA_CLIENTE")
                    );

                    return Optional.ofNullable(cliente);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        catch (SQLException e) {
            if(e.getErrorCode() == 1017) { // Erro de login/senha inválido
                throw new SQLException("Falha de autenticação ao conectar ao banco de dados.", e);
            } else if(e.getErrorCode() == 904) { // Erro de coluna inválida
                throw new SQLException("A query contém uma coluna inválida.", e);
            } else {
                throw new SQLException("Erro ao executar a query.", e);
            }
        }
        return Optional.empty();
    }

    public Optional<Cliente> findByEmail(String email) throws SQLException {
        String query = "SELECT * FROM T_PA_CLIENTE WHERE EMAIL_CLIENTE = ?";

        try(Connection connection = DataBaseFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setString(1, email);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("ID_CLIENTE"),
                            rs.getString("NM_CLIENTE"),
                            rs.getLong("CPF_CLIENTE"),
                            rs.getString("GENERO_CLIENTE"),
                            rs.getLong("TELEFONE_CLIENTE"),
                            rs.getString("DT_NASC_CLIENTE"),
                            rs.getString("ENDERECO_CLIENTE"),
                            rs.getString("EMAIL_CLIENTE"),
                            rs.getString("SENHA_CLIENTE")
                    );
                    return Optional.of(cliente);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        catch (SQLException e) {
            if(e.getErrorCode() == 1017) { // Erro de login/senha inválido
                throw new SQLException("Falha de autenticação ao conectar ao banco de dados.", e);
            } else if(e.getErrorCode() == 904) { // Erro de coluna inválida
                throw new SQLException("A query contém uma coluna inválida.", e);
            } else {
                throw new SQLException("Erro ao executar a query.", e);
            }
        }
        return Optional.empty();
    }

    public long add(Cliente cliente) throws SQLException {
        String query = "INSERT INTO T_PA_CLIENTE " +
                "(ID_CLIENTE, NM_CLIENTE, CPF_CLIENTE, GENERO_CLIENTE, TELEFONE_CLIENTE, DT_NASC_CLIENTE, ENDERECO_CLIENTE, EMAIL_CLIENTE, SENHA_CLIENTE, DT_CADASTRO, NM_USUARIO) " +
                "VALUES (SQ_PA_CLIENTE.nextval, ?, ?, ?, ?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, SYSDATE, USER)";
        String selectQuery = "SELECT SQ_PA_CLIENTE.CURRVAL FROM DUAL";
        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             PreparedStatement selectPs = connection.prepareStatement(selectQuery)) {

            ps.setString(1, cliente.getNm_cliente());
            ps.setLong(2, cliente.getCpf_cliente());
            ps.setString(3, cliente.getGenero_cliente());
            ps.setLong(4, cliente.getTelefone_cliente());
            ps.setString(5, cliente.getDt_nasc_cliente());
            ps.setString(6, cliente.getEmail_cliente());
            ps.setString(7, cliente.getEmail_cliente());
            ps.setString(8, cliente.getSenha_cliente());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Falha ao inserir o veículo, nenhuma linha afetada.");
            }

            try (ResultSet resultSet = selectPs.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                } else {
                    throw new SQLException("Falha ao obter o ID gerado após a inserção.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void update(Cliente cliente) throws SQLException {
        String query = "UPDATE T_PA_CLIENTE SET " +
                "NM_CLIENTE = ?, " +
                "CPF_CLIENTE = ?, " +
                "GENERO_CLIENTE = ?, " +
                "TELEFONE_CLIENTE = ?, " +
                "DT_NASC_CLIENTE = TO_DATE(?, 'DD/MM/YYYY'), " +
                "ENDERECO_CLIENTE = ?, " +
                "EMAIL_CLIENTE = ?, " +
                "SENHA_CLIENTE = ? " +
                "WHERE ID_CLIENTE = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, cliente.getNm_cliente());
            ps.setLong(2, cliente.getCpf_cliente());
            ps.setString(3, cliente.getGenero_cliente());
            ps.setLong(4, cliente.getTelefone_cliente());
            ps.setString(5, cliente.getDt_nasc_cliente());
            ps.setString(6, cliente.getEndereco_cliente());
            ps.setString(7, cliente.getEmail_cliente());
            ps.setString(8, cliente.getSenha_cliente());
            ps.setInt(9, cliente.getId_cliente());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM T_PA_CLIENTE WHERE ID_CLIENTE = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
