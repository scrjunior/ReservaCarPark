package consultas.model;

public class Medico {
    private int idMedico;
    
    private String especialidade;
    
    // Construtor
    public Medico(int idMedico, String especialidade) {
        this.idMedico = idMedico;
        
        this.especialidade = especialidade;
    }
    
    // Getters e Setters
    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}

