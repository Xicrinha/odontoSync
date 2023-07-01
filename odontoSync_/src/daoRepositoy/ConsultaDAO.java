package daoRepositoy;

import db.Database;
import model.Consulta;

import java.sql.*;

public class ConsultaDAO {


    public void salvarConsulta(Consulta consulta) throws SQLException {

        Connection connection = Database.getConnection();

        String sql = "INSERT INTO consultas (nome, sobrenome, data_nascimento, endereco, genero, data, hora) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";



        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, consulta.getNome());
            statement.setString(2, consulta.getSobrenome());
            statement.setDate(3, Date.valueOf(consulta.getDataNascimento()));
            statement.setString(4, consulta.getEndereco());
            statement.setString(5, consulta.getGenero().toString());
            statement.setDate(6, Date.valueOf(consulta.getData()));
            statement.setTime(7, Time.valueOf(consulta.getHora()));

            statement.executeUpdate();
        }finally {
            Database.closeConnection();
        }
    }
}
