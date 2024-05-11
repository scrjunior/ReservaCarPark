package consultas.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

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

}
