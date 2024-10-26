import java.util.List;
import java.util.Scanner;

import DAO.AlunoDAO;
import DAO.PessoaDAO;
import DAO.ProfessorDAO;
import DAO.TurmaDAO;
import entidade.Aluno;
import entidade.Pessoa;
import entidade.Professor;
import entidade.Turma;

public class Main {
    public static void adicionarPessoa(Scanner ler, int option){
        while (option != 0) {
            Pessoa pessoa = new Pessoa();
            System.out.println("Digite o nome: ");
            pessoa.setNome(ler.nextLine());
            System.out.println("Digite a idade: ");
            pessoa.setIdade(ler.nextInt());
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.createPessoa(pessoa);
                System.out.println("\n DESEJA CADASTRAR MAIS PESSOAS? \n 0 - NÃO\n 1 - SIM");
                option = ler.nextInt();
                ler.nextLine();    
        }
    }
    public static void adicionarProfessor(Scanner ler, int option){
        while (option != 0) {
            Professor professor = new Professor();
            System.out.println("Digite o nome: ");
            professor.setNome(ler.nextLine());
            System.out.println("Digite a idade: ");
            professor.setIdade(ler.nextInt());
            System.out.println("Digite o salário: ");
            professor.setSalario(ler.nextDouble());
            ProfessorDAO professorDAO = new ProfessorDAO();
            professorDAO.createProfessor(professor);
                System.out.println("\n DESEJA CADASTRAR MAIS PROFESSORES? \n 0 - NÃO\n 1 - SIM");
                option = ler.nextInt();
                ler.nextLine();    
        }
    }
    public static void exibirProfessores() {
        List<Professor> professores = ProfessorDAO.listarProfessores();
            System.out.println("Professores cadastrados: ");
                for (Professor p : professores) {
                    System.out.println("ID: " + p.getIdProfessor() + ", Nome: " + p.getNome() +", Idade: " + p.getIdade());
                }
    }
    public static void adicionarTurma(Scanner ler, int option, int idx) {
        while (option != 0) {
            Turma turma = new Turma();
            System.out.println("Digite o nome da Turma: ");
            turma.setNome_Turma(ler.nextLine());
                    System.out.println("Digite o ID do Professor que você quer na Turma: ");
                    exibirProfessores();
                    idx = ler.nextInt();
                        if (ProfessorDAO.searchProfessor(idx)) {
                            turma.setProfessor_id_Professor(idx);
                            TurmaDAO turmaDAO = new TurmaDAO();
                            turmaDAO.createTurma(turma);
                        } else {
                            System.out.println("ID Inexistente!");
                        }
                System.out.println("\n DESEJA CADASTRAR MAIS TURMAS? \n 0 - NÃO\n 1 - SIM");
                option = ler.nextInt();
                ler.nextLine();   
        }
    }
    public static void showTurmas(){
        List<Turma> turmas = TurmaDAO.listarTurmas();
            System.out.println("Turmas Cadastradas: ");
            for (Turma t : turmas) {
                System.out.println("ID: " + t.getIdTurma() + " Nome: " + t.getNome_Turma());
            }
    }
    public static void adicionarAluno(Scanner ler, int option, int idx){
        while (option != 0) {
            Aluno aluno = new Aluno();
            System.out.println("Digite o nome: ");
            aluno.setNome(ler.nextLine());
            System.out.println("Digite a idade: ");
            aluno.setIdade(ler.nextInt());
            System.out.println("Digite a nota: ");
            aluno.setNota(ler.nextDouble());
            System.out.println("Escolha uma Turma e digite o respectivo ID: ");        
            showTurmas();
                idx = ler.nextInt();
                if (TurmaDAO.buscarTurma(idx)) {
                    aluno.setTurma_id_Turma(idx);
                    AlunoDAO alunoDAO = new AlunoDAO();
                    alunoDAO.createAluno(aluno);
                } else {
                    System.out.println("ID Inexistente!");    
                }
                System.out.println("\n DESEJA CADASTRAR MAIS ALUNOS? \n 0 - NÃO\n 1 - SIM");
                    option = ler.nextInt();
                    ler.nextLine();    
        }
    }
    public static void exibirAlunos(){
        List<Aluno> alunos = AlunoDAO.listarAlunos();
            System.out.println("\nAlunos cadastrados:");
        for (Aluno a : alunos) {
            System.out.println("ID: " + a.getIdAluno() + ", Nome: " + a.getNome() +", Idade: " + a.getIdade() + ", Nota: " + a.getNota());
        }
    }
    public static void exibirAlunosTurma(int idx){
        List<Aluno> alunos = AlunoDAO.listarAlunosTurma(idx);
            System.out.println("\nAlunos cadastrados:");
        for (Aluno a : alunos) {
            System.out.println("ID: " + a.getIdAluno() + ", Nome: " + a.getNome() +", Idade: " + a.getIdade() + ", Nota: " + a.getNota());
        }
    }
    public static void exibirProfessorTurma(int idx){
        Professor professor = TurmaDAO.buscaProfessorTruma(idx);
        System.out.println("\nProfessor: ");
        System.out.println("ID: " + professor.getIdProfessor() + " Nome: " + professor.getNome() + " Idade: " + professor.getIdade());

    }
    public static void exibirTurma(Scanner ler, int idx){
        showTurmas();
        System.out.println("\n Qual turma deseja ver? (Digite o ID):");
        idx = ler.nextInt();
            if (TurmaDAO.buscarTurma(idx)) {
                exibirProfessorTurma(idx);
                exibirAlunosTurma(idx);
            } else {
                System.out.println("Turma Inexistente");
            }
    }
    public static void main(String[] args) {
        int option = -1;
        Scanner ler = new Scanner(System.in);
        int idx = 0;

        while (option != 0) {
            System.out.println(" ESCOLHA UMA DAS OPÇÕES \n\n 0 - Sair\n 1 - Adicionar Pessoa\n 2 - Adicionar Professor"+//
            "\n 3 - Exibir Professores\n 4 - Adicionar Turma\n 5 - Adicionar Aluno\n 6 - Exibir Alunos\n 7 - Exibir Turma");
            option = ler.nextInt();
            ler.nextLine();
            switch (option) {
                case 0:
                    break;
                case 1:
                    adicionarPessoa(ler, option);
                    break;
                case 2:
                    adicionarProfessor(ler, option);
                    break;
                case 3:
                    exibirProfessores();
                    break;
                case 4:
                    adicionarTurma(ler, option, idx);
                    break;
                case 5:
                    adicionarAluno(ler, option, idx);
                    break;
                case 6:
                    exibirAlunos();
                    break;
                case 7:
                    exibirTurma(ler, idx);
                    break;
                default:
                    System.out.println("Opção Inexistente!");
            }
        }
    }
}