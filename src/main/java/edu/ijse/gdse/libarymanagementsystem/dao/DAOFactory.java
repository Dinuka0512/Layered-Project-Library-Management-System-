package edu.ijse.gdse.libarymanagementsystem.dao;

import edu.ijse.gdse.libarymanagementsystem.bo.BOFactory;
import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.bo.custom.impl.BranchBOimpl;
import edu.ijse.gdse.libarymanagementsystem.bo.custom.impl.UserBOimpl;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl.BranchDAOImpl;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl.UserDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOType{
        BRANCH,USER
    }

    public SuperDAO getDAO(DAOFactory.DAOType type){
        switch (type){
            case BRANCH -> {
                return new BranchDAOImpl();
            }
            case USER -> {
                return new UserDAOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
