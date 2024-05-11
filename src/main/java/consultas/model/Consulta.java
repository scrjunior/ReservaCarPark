package consultas.model;

import java.util.Date;

public class Consulta {
    private int idConsulta;
    private String nomePaciente;
    private String apelidoPaciente;
    private String telefonePaciente;
    private String placaCarro;
    private String vaga;
    private Date dataConsulta;
    private int idVaga;

    // Construtor
    public Consulta() {
    }

    // Getters e Setters
    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getApelidoPaciente() {
        return apelidoPaciente;
    }

    public void setApelidoPaciente(String apelidoPaciente) {
        this.apelidoPaciente = apelidoPaciente;
    }

    public String getTelefonePaciente() {
        return telefonePaciente;
    }

    public void setTelefonePaciente(String telefonePaciente) {
        this.telefonePaciente = telefonePaciente;
    }
    
    public String getPlaca() {
        return placaCarro;
    }

    public void setPlaca(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    

    public String getVagas() {
        return vaga;
    }

    public void setVagas(String vaga) {
        this.vaga = vaga;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public int getIdVaga() {
        return idVaga;
    }

    public void setIdMedico(int idVaga) {
        this.idVaga = idVaga;
    }
}
