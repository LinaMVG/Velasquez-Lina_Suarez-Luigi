package test;

import dao.impl.OdontologoDAOMemoria;
import model.OdontologoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OdontologoMemoriaTest {

    OdontologoService OdontologoService = new OdontologoService( new OdontologoDAOMemoria());

    @Test
    @DisplayName("Testear que guarde y liste los odontologos")
    void caso1(){
        //Dado
        Integer id = 1;
        //cuando
        OdontologoModel vet = new OdontologoModel(1, 300, "Lina", "Suarez");
        OdontologoModel vet2 = new OdontologoModel(1, 301, "Juan", "Suarez");
        OdontologoModel vet3 = new OdontologoModel(1, 302, "Luis", "Suarez");


        //cuando
        OdontologoModel odontologoDesdeDb = OdontologoService.guardar(vet);
        OdontologoModel odontologoDesdeDb2 = OdontologoService.guardar(vet2);
        OdontologoModel odontologoDesdeDb3 = OdontologoService.guardar(vet3);

        List<OdontologoModel> listDesdeDb = OdontologoService.listar();

        // entonces
        assertEquals(3, listDesdeDb.size());
    }



}
