package org.example.models.repositories;

import org.example.infrastructure.database.DataBaseFactory;
import org.example.models.Medida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedidaRepository {
    public List<Medida> findAll() throws SQLException {
        List<Medida> medidas = new ArrayList<Medida>();
        String query = "SELECT * FROM T_PA_MEDIDA ORDER BY 1 ASC";
        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                Medida medida = new Medida(
                        rs.getInt("ID_MEDIDA"),
                        rs.getDouble("ALTURA"),
                        rs.getDouble("LARGURA"),
                        rs.getDouble("COMPRIMENTO"),
                        rs.getDouble("PESO"),
                        rs.getDouble("PESO_SUPORTADO")
                );
                medidas.add(medida);
            }
            return medidas;
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

    public Optional<Medida> find(int id) throws SQLException {
        String query = "SELECT * FROM T_PA_MEDIDA WHERE ID_MEDIDA = ?";
        try(Connection connection = DataBaseFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Medida medida = new Medida(
                            rs.getInt("ID_MEDIDA"),
                            rs.getDouble("ALTURA"),
                            rs.getDouble("LARGURA"),
                            rs.getDouble("COMPRIMENTO"),
                            rs.getDouble("PESO"),
                            rs.getDouble("PESO_SUPORTADO")
                    );
                    return Optional.of(medida);
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

    public Long add(Medida medida) throws SQLException {
        String insertQuery = "INSERT INTO T_PA_MEDIDA (ID_MEDIDA, ALTURA, LARGURA, COMPRIMENTO, PESO, PESO_SUPORTADO, DT_CADASTRO, NM_USUARIO) VALUES (SQ_PA_MEDIDA.nextval, ?, ?, ?, ?, ?, SYSDATE, USER)";
        String selectQuery = "SELECT SQ_PA_MEDIDA.CURRVAL FROM DUAL";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement insertPs = connection.prepareStatement(insertQuery);
             PreparedStatement selectPs = connection.prepareStatement(selectQuery)) {

            insertPs.setDouble(1, medida.getAltura());
            insertPs.setDouble(2, medida.getLargura());
            insertPs.setDouble(3, medida.getComprimento());
            insertPs.setDouble(4, medida.getPeso());
            insertPs.setDouble(5, medida.getPeso_suportado());
            int rowsAffected = insertPs.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Falha ao inserir a medida, nenhuma linha afetada.");
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


    public void update(Medida medida) throws SQLException {
        String query = "UPDATE T_PA_MEDIDA SET ALTURA = ?, LARGURA = ?, COMPRIMENTO = ?, PESO = ?, PESO_SUPORTADO = ? WHERE ID_MEDIDA = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setDouble(1, medida.getAltura());
            ps.setDouble(2, medida.getLargura());
            ps.setDouble(3, medida.getComprimento());
            ps.setDouble(4, medida.getPeso());
            ps.setDouble(5, medida.getPeso_suportado());
            ps.setInt(6, medida.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM T_PA_MEDIDA WHERE ID_MEDIDA = ?";
        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
