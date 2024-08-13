package dao.impl;

import dao.IDao;
import db.H2Connection;
import model.OdontologoModel;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<OdontologoModel> {

    public static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);
    public static final String INSERT = "INSERT INTO ODONTOLOGOS VALUES(DEFAULT,?,?,? )";
    public static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";


    @Override
    public OdontologoModel guardar(OdontologoModel odontologo) {
        Connection connection = null;
        OdontologoModel odontologoARetornar = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,odontologo.getNoMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                odontologoARetornar = new OdontologoModel(id, odontologo.getNoMatricula(), odontologo.getNombre(),
                        odontologo.getApellido());
            }
            logger.info("Odontologo "+ odontologoARetornar);

        }catch (Exception e){
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error(e.getMessage());
                } finally {
                    try {
                        connection.setAutoCommit(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologoARetornar;
    }


    @Override
    public List<OdontologoModel> listar() {
        List<OdontologoModel> odontologos = new ArrayList<>();
        Connection connection = null;

        try{
            connection = H2Connection.getConnection();
            //connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL, Statement.RETURN_GENERATED_KEYS);
            //connection.commit();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer idBD = resultSet.getInt(1);
                Integer idMatricula = resultSet.getInt(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);

                OdontologoModel odontologoAuxiliar =new OdontologoModel(idBD,idMatricula, nombre, apellido);

                odontologos.add(odontologoAuxiliar);

            }
            logger.info("Cantidad de odontologos listados: "+ odontologos.size());

        }catch (Exception e){
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error(e.getMessage());
                } finally {
                    try {
                        connection.setAutoCommit(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologos;
    }

}
