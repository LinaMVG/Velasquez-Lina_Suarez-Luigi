package service;

import dao.IDao;
import model.OdontologoModel;

import java.util.List;

public class OdontologoService {
    private IDao<OdontologoModel> OdontologoIDao;

    public OdontologoService(IDao<OdontologoModel> odontologoIDao) {
        this.OdontologoIDao=odontologoIDao;
    }

    public OdontologoModel guardar(OdontologoModel odontologo){
        return OdontologoIDao.guardar(odontologo);
    }

    public List<OdontologoModel> listar( ){
        return OdontologoIDao.listar();
    }

}
