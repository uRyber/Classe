package entidade;

public class Aluno extends Pessoa {
    private int idAluno;
    private double nota;
    private int Turma_id_Turma; //Foreign key

    public Aluno() {
    }

    public Aluno(int id, int idAluno, String nome, int idade, double nota, int Turma_id_Turma) {
        super(id, nome, idade);
        this.idAluno = idAluno;
        this.nota = nota;
        this.Turma_id_Turma = Turma_id_Turma;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    public int getTurma_id_Turma() {
        return Turma_id_Turma;
    }

    public void setTurma_id_Turma(int turma_id_Turma) {
        Turma_id_Turma = turma_id_Turma;
    }

}
