package consultas.model;

import java.time.LocalDateTime;

public class DisponibilidadeMedico {
    private int idDisponibilidade;
    private int idMedico;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    
    // Construtor
    public DisponibilidadeMedico(int idDisponibilidade, int idMedico, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        this.idDisponibilidade = idDisponibilidade;
        this.idMedico = idMedico;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
    }
    
    // Getters e Setters
    public int getIdDisponibilidade() {
        return idDisponibilidade;
    }

    public void setIdDisponibilidade(int idDisponibilidade) {
        this.idDisponibilidade = idDisponibilidade;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }
}
