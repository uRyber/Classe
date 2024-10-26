package entidade;

public class Professor extends Pessoa{
    private int idProfessor;
    private double salario;

    public Professor() {
    }

    public Professor(int id, int idProfessor, String nome, int idade, double salario) {
        super(id, nome, idade);
        this.idProfessor = idProfessor;
        this.salario = salario;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

}
