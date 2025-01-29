package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    //THIS METHODS ARE UNIQUE FOR USER DAO
    boolean isUniqueEmail(String email) throws SQLException, ClassNotFoundException;
}
