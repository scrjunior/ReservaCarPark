package consultas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import consultas.model.Medico;
import consultas.util.DBConnect; // Importe sua classe DBConnect aqui

public class MedicoDAO {
    // Método para obter a lista de médicos
    public List<Medico> listarMedicos() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM Vagas";
        
        try (Connection conn = new DBConnect().connector(); // Use sua classe DBConnect para obter a conexão
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                int idMedico = rs.getInt("ID_Vaga");

                String especialidade = rs.getString("Vaga");
                Medico medico = new Medico(idMedico,especialidade);
                medicos.add(medico);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return medicos;
    }

 // Método para obter a lista de datas de consulta para um médico específico
    public List<LocalDateTime> listarDatasConsulta(int idMedico) {
        List<LocalDateTime> datasConsulta = new ArrayList<>();
        String sql = "SELECT DataHoraInicio FROM DisponibilidadeMedico WHERE ID_Vaga = ?";
        
        try (Connection conn = new DBConnect().connector();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idMedico);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                LocalDateTime dataHoraInicio = rs.getTimestamp("DataHoraInicio").toLocalDateTime();
                datasConsulta.add(dataHoraInicio);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return datasConsulta;
    }
    
    
    
}
