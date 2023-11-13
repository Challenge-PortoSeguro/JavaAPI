package org.example.models.repositories;

import org.example.infrastructure.database.DataBaseFactory;
import org.example.models.Chamada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChamadaRepository {
    private final ModalColabRepository modalColabRepository = new ModalColabRepository();
    private final VeicClienteRepository veicClienteRepository = new VeicClienteRepository();

    public List<Chamada> findAll() throws SQLException {
        List<Chamada> chamadas = new ArrayList<Chamada>();
        String query = "SELECT * FROM T_PA_CHAMADA ORDER BY 1 ASC";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                Chamada chamada = new Chamada(
                        rs.getInt("ID_CHAMADA"),
                        modalColabRepository.find(rs.getInt("ID_MODAL_COLAB")).orElse(null),
                        veicClienteRepository.find(rs.getInt("ID_VEIC_CLIENT")).orElse(null),
                        rs.getString("DT_INICIO_CHAMADA"),
                        rs.getString("DT_FIM_CHAMADA"),
                        rs.getString("LOCAL_CHAMADA"),
                        rs.getString("DESTINO_CHAMADA"),
                        rs.getString("DS_PROB_CHAMADA")
                );
                chamadas.add(chamada);
            }
            return chamadas;
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


    public Optional<Chamada> find(int id) throws SQLException {
        String query = "SELECT * FROM T_PA_CHAMADA WHERE ID_CHAMADA = ?";
        try(Connection connection = DataBaseFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Chamada chamada = new Chamada(
                            rs.getInt("ID_CHAMADA"),
                            modalColabRepository.find(rs.getInt("ID_MODAL_COLAB")).orElse(null),
                            veicClienteRepository.find(rs.getInt("ID_VEIC_CLIENT")).orElse(null),
                            rs.getString("DT_INICIO_CHAMADA"),
                            rs.getString("DT_FIM_CHAMADA"),
                            rs.getString("LOCAL_CHAMADA"),
                            rs.getString("DESTINO_CHAMADA"),
                            rs.getString("DS_PROB_CHAMADA")
                    );
                    return Optional.of(chamada);
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


    public long add(Chamada chamada) throws SQLException {
        String query = "INSERT INTO T_PA_CHAMADA " +
                "(ID_CHAMADA, " +
                "ID_MODAL_COLAB, " +
                "ID_VEIC_CLIENT, " +
                "DT_INICIO_CHAMADA, " +
                "DT_FIM_CHAMADA, " +
                "LOCAL_CHAMADA, " +
                "DESTINO_CHAMADA, " +
                "DS_PROB_CHAMADA, " +
                "DT_CADASTRO, " +
                "NM_USUARIO) " +
                "VALUES (SQ_PA_CHAMADA.nextval, ?, ?, TO_DATE(?, 'DD/MM/YYYY'), TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, SYSDATE, USER)";
        String selectQuery = "SELECT SQ_PA_CHAMADA.CURRVAL FROM DUAL";
        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             PreparedStatement selectPs = connection.prepareStatement(selectQuery)) {

            ps.setInt(1, chamada.getId_modal_colab().getId_modal_colab());
            ps.setInt(2, chamada.getId_veic_client().getId_veic_client());
            ps.setString(3, chamada.getDt_inicio_chamada());
            ps.setString(4, chamada.getDt_fim_chamada());
            ps.setString(5, chamada.getLocal_chamada());
            ps.setString(6, chamada.getDestino_chamada());
            ps.setString(7, chamada.getDs_prob_chamada());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Falha ao inserir a chamada, nenhuma linha afetada.");
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

    public void update(Chamada chamada) throws SQLException {
        String query = "UPDATE T_PA_CHAMADA SET " +
                "ID_MODAL_COLAB = ?, " +
                "ID_VEIC_CLIENT = ?, " +
                "DT_INICIO_CHAMADA = TO_DATE(?, 'DD/MM/YYYY'), " +
                "DT_FIM_CHAMADA = TO_DATE(?, 'DD/MM/YYYY'), " +
                "LOCAL_CHAMADA = ?, " +
                "DESTINO_CHAMADA = ?, " +
                "DS_PROB_CHAMADA = ? " +
                "WHERE ID_CHAMADA = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, chamada.getId_modal_colab().getId_modal_colab());
            ps.setInt(2, chamada.getId_veic_client().getId_veic_client());
            ps.setString(3, chamada.getDt_inicio_chamada());
            ps.setString(4, chamada.getDt_fim_chamada());
            ps.setString(5, chamada.getLocal_chamada());
            ps.setString(6, chamada.getDestino_chamada());
            ps.setString(7, chamada.getDs_prob_chamada());
            ps.setInt(8, chamada.getId_chamada());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM T_PA_CHAMADA WHERE ID_CHAMADA = ?";
        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
