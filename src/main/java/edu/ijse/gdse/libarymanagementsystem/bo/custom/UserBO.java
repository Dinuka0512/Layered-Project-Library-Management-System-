package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.UserDto;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDto dto, String branchID) throws ClassNotFoundException, SQLException;
    String genarateId() throws SQLException, ClassNotFoundException;
    boolean isUniqueEmail(String email) throws SQLException, ClassNotFoundException;
    UserDto getUserDetails(String userEmail) throws SQLException, ClassNotFoundException;
}
