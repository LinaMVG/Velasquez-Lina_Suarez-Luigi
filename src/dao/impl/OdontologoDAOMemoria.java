package dao.impl;

import dao.IDao;
import model.OdontologoModel;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOMemoria implements IDao<OdontologoModel> {

    public static final Logger logger = Logger.getLogger(OdontologoDAOMemoria.class);
    private List<OdontologoModel>odontologos = new ArrayList<>();

    @Override
    public OdontologoModel guardar(OdontologoModel odontologoModel) {
        odontologoModel.setId(odontologos.size()+1);
        odontologos.add(odontologoModel);
        logger.info("Odontologo guardado en memoria " + odontologoModel);

        return odontologoModel;
    }

    @Override
    public List<OdontologoModel> listar() {

        logger.info("Cantidad de odontologos listados: "+odontologos.size());
        return odontologos;
    }
}
