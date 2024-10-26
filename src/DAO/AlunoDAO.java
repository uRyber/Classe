package DAO;

import entidade.Aluno;
import util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public static Aluno createAluno(Aluno aluno) {
        try{

            //abrindo conexão com o banco
            Connection conn = ConnectionMysql.openConnection();

            //query a ser executada
            String sqlPessoa = "INSERT INTO pessoa (nome, idade) VALUES (?, ?)";

            //statement - responsável por executar a query
            PreparedStatement statementPessoa = conn.prepareStatement(sqlPessoa);
            statementPessoa.setString(1, aluno.getNome());
            statementPessoa.setInt(2, aluno.getIdade());
            statementPessoa.executeUpdate();

            //buscando id Pessoa
            String sqlPessoaFind = "SELECT id_pessoa FROM pessoa WHERE nome =? and idade =? LIMIT 1";
            PreparedStatement statementPessoaFind = conn.prepareStatement(sqlPessoaFind);
            statementPessoaFind.setString(1, aluno.getNome());
            statementPessoaFind.setInt(2, aluno.getIdade());
            ResultSet resultSet = statementPessoaFind.executeQuery();

            int idPessoa = -1;
            while (resultSet.next()) {
                idPessoa = resultSet.getInt("id_Pessoa");
            }

            String sql = "INSERT INTO aluno (nota, Pessoa_id_Pessoa, Turma_id_Turma) VALUES (?,?,?)";

            //statement - responsável por executar a query
            PreparedStatement statementAluno = conn.prepareStatement(sql);
            statementAluno.setDouble(1, aluno.getNota());
            statementAluno.setInt(2, idPessoa); //ID pessoa
            statementAluno.setInt(3, aluno.getTurma_id_Turma());
            statementAluno.executeUpdate();    

            System.out.println("Aluno adicionado");

            ConnectionMysql.closeConnection();

        } catch (SQLException e) {
            System.out.println("Problemas ao verificar os Alunos " + e.getMessage());
        }
        return aluno;
    }
    
    public static List<Aluno> listarAlunos(){
        List<Aluno> alunos = new ArrayList<>();

        try {
            //OPEN CONNECTION
            Connection conn = ConnectionMysql.openConnection();
            //QUERY
            String sqlBuscaAlunos = "SELECT a.id_aluno, p.nome, p.idade, a.nota, a.Turma_id_Turma FROM aluno a INNER JOIN pessoa p ON a.Pessoa_id_Pessoa = p.id_pessoa";
            //EXECUTE QUERY
            PreparedStatement statementBuscaAlunos = conn.prepareStatement(sqlBuscaAlunos);
            ResultSet resultSet = statementBuscaAlunos.executeQuery();

            while (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setIdAluno(resultSet.getInt("id_Aluno"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setIdade(resultSet.getInt("idade"));
                aluno.setNota(resultSet.getDouble("nota"));
                aluno.setTurma_id_Turma(resultSet.getInt("Turma_id_Turma"));
                alunos.add(aluno);
            } 
            ConnectionMysql.closeConnection();
        } catch (SQLException e) {
            System.out.println("Problemas ao listar os Alunos " + e.getMessage());
        }
        return alunos;
 
    }
    public static List<Aluno> listarAlunosTurma(int idx){
        List<Aluno> alunos = new ArrayList<>();

        try {
            //OPEN CONNECTION
            Connection conn = ConnectionMysql.openConnection();
            //QUERY
            String sqlBuscaAlunos = "SELECT a.id_aluno, a.nota, p.nome, p.idade FROM aluno a INNER JOIN pessoa p ON a.Pessoa_id_Pessoa = p.id_pessoa \n" + //
                                "WHERE a.Turma_id_Turma = ?";
            //EXECUTE QUERY
            PreparedStatement statementBuscaAlunos = conn.prepareStatement(sqlBuscaAlunos);
            statementBuscaAlunos.setInt(1, idx);
            ResultSet resultSet = statementBuscaAlunos.executeQuery();

            while (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setIdAluno(resultSet.getInt("id_Aluno"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setIdade(resultSet.getInt("idade"));
                aluno.setNota(resultSet.getDouble("nota"));
                alunos.add(aluno);
            } 
            ConnectionMysql.closeConnection();
        } catch (SQLException e) {
            System.out.println("Problemas ao listar os Alunos " + e.getMessage());
        }
        return alunos;
    }
}
