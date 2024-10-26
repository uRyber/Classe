package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entidade.Pessoa;
import util.ConnectionMysql;

public class PessoaDAO {

    public static Pessoa createPessoa(Pessoa pessoa) {

        try{

            //abrindo conexão com o banco
            Connection conn = ConnectionMysql.openConnection();

            //query a ser executada
            String sql = "INSERT INTO pessoa (nome, idade) VALUES (?,?)";

            //statement - responsável por executar a query
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, pessoa.getNome());
            statement.setInt(2, pessoa.getIdade());

            statement.executeUpdate();

            System.out.println("Pessoa Adicionada!");

            ConnectionMysql.closeConnection();

        } catch (SQLException e) {
            System.out.println("Problemas ao verificar as Pessoas " + e.getMessage());
        }
        return pessoa;
    }
}
