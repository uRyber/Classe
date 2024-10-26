// package DAO;

// import entidade.Professor;
// import util.ConnectionMysql;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class ProfessorDAO {
//     public static Professor createProfessor(Professor professor) {
//         Connection conn = null;
//         PreparedStatement statementPessoa = null;
//         PreparedStatement statementProfessor = null;

//         try {
//             // Abrindo conexão com o banco
//             conn = ConnectionMysql.openConnection();

//             // Inserindo a pessoa e solicitando a chave gerada
//             String sqlPessoa = "INSERT INTO pessoa (nome, idade) VALUES (?, ?)";
//             statementPessoa = conn.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS);
//             statementPessoa.setString(1, professor.getNome());
//             statementPessoa.setInt(2, professor.getIdade());
//             statementPessoa.executeUpdate();

//             // Obtendo o ID da pessoa inserida
//             ResultSet generatedKeys = statementPessoa.getGeneratedKeys();
//             int idPessoa = -1;
//             if (generatedKeys.next()) {
//                 idPessoa = generatedKeys.getInt(1);
//             }

//             // Inserindo o professor
//             String sqlProfessor = "INSERT INTO professor (salario, Pessoa_id_Pessoa) VALUES (?, ?)";
//             statementProfessor = conn.prepareStatement(sqlProfessor, PreparedStatement.RETURN_GENERATED_KEYS);
//             statementProfessor.setDouble(1, professor.getSalario());
//             statementProfessor.setInt(2, idPessoa);
//             statementProfessor.executeUpdate();

//             System.out.println("Professor adicionado");

//         } catch (SQLException e) {
//             System.out.println("Problemas ao inserir o Professor: " + e.getMessage());
//         } finally {
//             // Fechando os recursos
//             try {
//                 if (statementProfessor != null) statementProfessor.close();
//                 if (statementPessoa != null) statementPessoa.close();
//                 if (conn != null) ConnectionMysql.closeConnection();
//             } catch (SQLException e) {
//                 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
//             }
//         }
//         return professor;
//     }
//}
            // ResultSet generatedKeys = statementPessoa.getGeneratedKeys();
            // int idPessoa = -1;
            // if (generatedKeys.next()) {
            //     idPessoa = generatedKeys.getInt(1);
            // }

package DAO;

import entidade.Professor;
import util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
    public static Professor createProfessor(Professor professor) {
        try{

            //abrindo conexão com o banco
            Connection conn = ConnectionMysql.openConnection();

            //query a ser executada
            String sqlPessoa = "INSERT INTO pessoa (nome, idade) VALUES (?, ?)";

            //statement - responsável por executar a query
            PreparedStatement statementPessoa = conn.prepareStatement(sqlPessoa);
            statementPessoa.setString(1, professor.getNome());
            statementPessoa.setInt(2, professor.getIdade());
            statementPessoa.executeUpdate();

            //buscando id Pessoa
            String sqlPessoaFind = "SELECT id_pessoa FROM pessoa WHERE nome =? and idade=? LIMIT 1";
            PreparedStatement statementPessoaFind = conn.prepareStatement(sqlPessoaFind);
            statementPessoaFind.setString(1, professor.getNome());
            statementPessoaFind.setInt(2, professor.getIdade());
            ResultSet resultSet = statementPessoaFind.executeQuery();

            int idPessoa = 0;
            while (resultSet.next()) {
                idPessoa = resultSet.getInt("id_Pessoa");
            }

            //Criando o professor
            String sqlProfessor = "INSERT INTO professor (salario, Pessoa_id_Pessoa)" +
                    "VALUES (?,?)";
            PreparedStatement statementProfessor = conn.prepareStatement(sqlProfessor);
            statementProfessor.setDouble(1, professor.getSalario());
            statementProfessor.setInt(2, idPessoa);
            statementProfessor.executeUpdate();

            System.out.println("Professor adicionado");

            ConnectionMysql.closeConnection();

        } catch (SQLException e) {
            System.out.println("Problemas ao verificar os Professores " + e.getMessage());
        }
        return professor;
    }
    public static List<Professor> listarProfessores() {
        List<Professor> professores = new ArrayList<>();

            try {
                Connection conn = ConnectionMysql.openConnection();
                //QUERY
                String sqlBuscaProfessores = "SELECT pr.id_Professor, p.nome, p.idade, pr.salario FROM professor pr INNER JOIN pessoa p ON pr.Pessoa_id_Pessoa = p.id_pessoa";
                PreparedStatement statementBuscaProfessores = conn.prepareStatement(sqlBuscaProfessores);
                ResultSet resultSet = statementBuscaProfessores.executeQuery();

                while (resultSet.next()) {
                    Professor professor = new Professor();
                    professor.setIdProfessor(resultSet.getInt("id_Professor"));
                    professor.setNome(resultSet.getString("nome"));
                    professor.setIdade(resultSet.getInt("idade"));
                    professor.setSalario(resultSet.getDouble("salario"));
                    professores.add(professor);
                }

                ConnectionMysql.closeConnection();
            } catch (SQLException e) {
                System.out.println("Problemas ao Buscar Professores: " + e.getMessage());
            }
        return professores;
    }
    public static boolean searchProfessor(int idx){
    boolean existe = false;
        try {
            Connection conn = ConnectionMysql.openConnection();
                //QUERY
            String sql = "select id_Professor from professor where id_Professor = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idx);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                existe = true;
            }            
        } catch (SQLException e) {
            System.out.println("Problemas ao Buscar Professores: " + e.getMessage());
        }
        return existe;
    }
}
