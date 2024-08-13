package test;

import dao.impl.OdontologoDAOH2;
import model.OdontologoModel;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OdontologoH2Test {

    static final Logger logger = Logger.getLogger(OdontologoH2Test.class);
   OdontologoService OdontologoService = new OdontologoService(new OdontologoDAOH2());

    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./odontologo;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Testear guardar y obtener la lista de veterinarios")
    void caso1(){

        OdontologoModel odontologo = new OdontologoModel(300,"Lina", "Suarez");
        OdontologoModel odontologo2 = new OdontologoModel(301,"Juan", "Suarez");
        OdontologoModel odontologo3 = new OdontologoModel(302,"Luis", "Suarez");

        OdontologoService.guardar(odontologo);
        OdontologoService.guardar(odontologo2);
        OdontologoService.guardar(odontologo3);


        List<OdontologoModel> odontologos = OdontologoService.listar();

        assertEquals(3, odontologos.size());

    }
}
