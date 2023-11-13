package org.example.models.repositories;

import org.example.infrastructure.database.DataBaseFactory;
import org.example.models.ModalColab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModalColabRepository {
    private ModalRepository modalRepository = new ModalRepository();
    private ColaboradorRepository colaboradorRepository = new ColaboradorRepository();

    public List<ModalColab> findAll() throws SQLException {
        List<ModalColab> modaisColabs = new ArrayList<ModalColab>();
        String query = "SELECT * FROM T_PA_MODAL_COLAB ORDER BY 1 ASC";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                ModalColab modalColab = new ModalColab(
                        rs.getInt("ID_MODAL_COLAB"),
                        modalRepository.find(rs.getInt("ID_MODAL")).orElse(null),
                        colaboradorRepository.find(rs.getInt("ID_COLAB")).orElse(null)
                );
                modaisColabs.add(modalColab);
            }
            return modaisColabs;
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

    public Optional<ModalColab> find(int id) throws SQLException {
        String query = "SELECT * FROM T_PA_MODAL_COLAB WHERE ID_COLAB = ?";
        try(Connection connection = DataBaseFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    ModalColab modalColab = new ModalColab(
                            rs.getInt("ID_MODAL_COLAB"),
                            modalRepository.find(rs.getInt("ID_MODAL")).orElse(null),
                            colaboradorRepository.find(rs.getInt("ID_COLAB")).orElse(null)
                    );
                    return Optional.of(modalColab);
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

    public void add(ModalColab modalColab) throws SQLException {
        String query = "INSERT INTO T_PA_MODAL_COLAB (ID_MODAL_COLAB, ID_COLAB, ID_MODAL) VALUES (SQ_PA_MODAL_COLAB.nextval, ?, ?)";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, modalColab.getId_colab().getId_colab());
            ps.setInt(2, modalColab.getId_modal().getId_modal());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void update(ModalColab modalColab) throws SQLException {
        String query = "UPDATE T_PA_MODAL_COLAB SET " +
                "ID_MODAL = ?, " +
                "ID_COLAB = ? " +
                "WHERE ID_MODAL_COLAB = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, modalColab.getId_colab().getId_colab());
            ps.setInt(2, modalColab.getId_modal().getId_modal());
            ps.setInt(3, modalColab.getId_modal_colab());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM T_PA_MODAL_COLAB WHERE ID_MODAL_COLAB = ?";
        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
