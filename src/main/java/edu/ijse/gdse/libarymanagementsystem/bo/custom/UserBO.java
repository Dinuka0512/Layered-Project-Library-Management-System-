package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.UserDto;
import edu.ijse.gdse.libarymanagementsystem.entity.User;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDto dto, String branchID) throws ClassNotFoundException, SQLException;
    String genarateId() throws SQLException, ClassNotFoundException;
    boolean isUniqueEmail(String email) throws SQLException, ClassNotFoundException;
    UserDto getUserDetails(String userEmail) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException;
    String getUserId(String email);
    UserDto checkEmail(String email) throws SQLException, ClassNotFoundException;
}
