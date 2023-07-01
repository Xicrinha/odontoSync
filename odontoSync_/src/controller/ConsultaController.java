package controller;

import daoRepositoy.ConsultaDAO;
import model.Consulta;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ConsultaController {
    private ConsultaDAO consultaDAO;

    public ConsultaController() {
        consultaDAO = new ConsultaDAO();
    }

    public void salvarConsulta(Consulta consulta) throws SQLException {
        consultaDAO.salvarConsulta(consulta);
    }

}

