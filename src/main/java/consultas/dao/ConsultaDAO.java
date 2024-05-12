package consultas.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import consultas.model.Consulta;
import consultas.util.DBConnect;

public class ConsultaDAO {
    private static final String INSERIR_CONSULTA_SQL = "INSERT INTO consultas (Nome, Apelido, Telefone, Placa, Vaga, Data_Consulta, ID_Vaga) VALUES (?, ?, ?, ?, ?, ?, ?);";

    public void inserirConsulta(Consulta consulta) {
        try (Connection connection = new DBConnect().connector();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_CONSULTA_SQL)) {
            preparedStatement.setString(1, consulta.getNomePaciente());
            preparedStatement.setString(2, consulta.getApelidoPaciente());
            preparedStatement.setString(3, consulta.getTelefonePaciente());
            preparedStatement.setString(4, consulta.getPlaca());          
            preparedStatement.setString(5, consulta.getVagas());
            
            // Verificar se a data de consulta não é nula antes de acessá-la
            if (consulta.getDataConsulta() != null) {
                preparedStatement.setTimestamp(6, new java.sql.Timestamp(consulta.getDataConsulta().getTime()));
            } else {
                preparedStatement.setNull(6, Types.TIMESTAMP); // ou ajuste para o tipo apropriado, se necessário
            }
            
            preparedStatement.setInt(7, consulta.getIdVaga());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static final String SELECT_CONSULTAS_SQL = "SELECT c.Nome, c.Apelido, c.Telefone, c.Placa, v.Vaga, c.Data_Consulta, c.ID_Vaga FROM consultas c JOIN vagas v ON c.ID_Vaga = v.ID_Vaga";

    public List<Consulta> getAllConsultas() {
        List<Consulta> consultas = new ArrayList<>();
        try (Connection connection = new DBConnect().connector();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONSULTAS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Consulta consulta = new Consulta();
                consulta.setNomePaciente(resultSet.getString("Nome"));
                consulta.setApelidoPaciente(resultSet.getString("Apelido"));
                consulta.setTelefonePaciente(resultSet.getString("Telefone"));
                consulta.setPlaca(resultSet.getString("Placa"));
                consulta.setVagas(resultSet.getString("Vaga"));
                consulta.setDataConsulta(resultSet.getTimestamp("Data_Consulta"));
                consulta.setIdMedico(resultSet.getInt("ID_Vaga"));

                consultas.add(consulta);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return consultas;
    }
    
    private static final String DELETE_CONSULTA_BY_PLACA_SQL = "DELETE FROM consultas WHERE Placa = ?";

    public void deleteConsultaByPlaca(String placa) {
        try (Connection connection = new DBConnect().connector();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONSULTA_BY_PLACA_SQL)) {
            preparedStatement.setString(1, placa);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., throw a custom DAOException)
        }
    }

}
