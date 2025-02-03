package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.UserDto;
import edu.ijse.gdse.libarymanagementsystem.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    //THIS METHODS ARE UNIQUE FOR USER DAO
    boolean isUniqueEmail(String email) throws SQLException, ClassNotFoundException;
    UserDto getUserDetails(String userEmail) throws SQLException, ClassNotFoundException;
    String getUserId(String email);
    User checkEmail(String email) throws SQLException, ClassNotFoundException;
}
