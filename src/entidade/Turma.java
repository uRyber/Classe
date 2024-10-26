package entidade;

public class Turma {
    private int idTurma;
    private String nome_Turma;
    private int Professor_id_Professor; //FOREIGN KEY

    public Turma() {
    }

    public Turma(int idTurma,String nome_Turma,int Professor_id_Professor) {
        this.idTurma = idTurma;
        this.nome_Turma = nome_Turma;
        this.Professor_id_Professor = Professor_id_Professor;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getNome_Turma() {
        return nome_Turma;
    }

    public void setNome_Turma(String nome_Turma) {
        this.nome_Turma = nome_Turma;
    }

    public int getProfessor_id_Professor() {
        return Professor_id_Professor;
    }

    public void setProfessor_id_Professor(int professor_id_Professor) {
        Professor_id_Professor = professor_id_Professor;
    }
}
