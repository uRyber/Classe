package DAO;

import entidade.Professor;
import entidade.Turma;
import util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {
    public static Turma createTurma(Turma turma) {
        try{

            //abrindo conexão com o banco
            Connection conn = ConnectionMysql.openConnection();

            String sql = "insert into turma (nome_Turma, Professor_id_Professor) values (?,?)";
            PreparedStatement statementTurma = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statementTurma.setString(1, turma.getNome_Turma());
            statementTurma.setInt(2, turma.getProfessor_id_Professor());
            statementTurma.executeUpdate();

            ResultSet rs = statementTurma.getGeneratedKeys();
            if (rs.next()) {
                int turmaId = rs.getInt(1);
                turma.setIdTurma(turmaId);
            
                System.out.println("Turma adicionada");

                ConnectionMysql.closeConnection();
            }

        } catch (SQLException e) {
            System.out.println("Problemas ao verificar as Turmas " + e.getMessage());
        }
        return turma;
    }
    public static List<Turma> listarTurmas(){
        List<Turma> turmas = new ArrayList<>();

            try {
                Connection conn = ConnectionMysql.openConnection();

                String sql = "SELECT * FROM mydb.turma";

                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                
                while (resultSet.next()) {
                    Turma turma = new Turma();
                    turma.setIdTurma(resultSet.getInt("id_Turma"));
                    turma.setNome_Turma(resultSet.getString("nome_Turma"));
                    turma.setProfessor_id_Professor(resultSet.getInt("Professor_id_Professor"));
                    turmas.add(turma);
                }

                ConnectionMysql.openConnection();

            } catch (SQLException e) {
                System.out.println("Problemas ao Listar as Turmas: " + e.getMessage());
            }

        return turmas;
    }
    public static boolean buscarTurma(int idx){
        boolean existe = false;
            try {
                Connection conn = ConnectionMysql.openConnection();

                String sql = "SELECT id_Turma FROM turma WHERE id_Turma = ?";

                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, idx);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    existe = true;
                }
            } catch (SQLException e) {
                System.out.println("Problemas ao Buscar a Turma: " + e.getMessage());
            }
        return existe;
    }
    public static Professor buscaProfessorTruma(int idx) {
        Professor professor = null;
        try {
            Connection conn = ConnectionMysql.openConnection();

            String sql = "select id_Professor, nome, idade from mydb.turma ";
                sql +="\n inner join mydb.professor on professor.id_Professor=turma.Professor_id_Professor";
                sql +="\n inner join mydb.pessoa on pessoa.id_Pessoa=professor.Pessoa_id_Pessoa";
                sql +="\n where turma.id_Turma = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idx);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                professor = new Professor(); 
                professor.setIdProfessor(resultSet.getInt("id_Professor"));
                professor.setNome(resultSet.getString("nome"));
                professor.setIdade(resultSet.getInt("idade"));
            }
            ConnectionMysql.closeConnection();

        } catch (SQLException e) {
            System.out.println("Problemas ao consultar Professor: " + e.getMessage());
        }
        return professor;
    }
}
