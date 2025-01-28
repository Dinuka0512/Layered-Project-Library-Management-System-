package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.UserDto;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class LogInModel {
    public UserDto checkEmail(String email) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE email = ?";
        ResultSet res = CrudUtil.execute(sql,email);
        if(res.next()){
            UserDto dto = new UserDto(
                    res.getString("User_Id"),
                    res.getString("name"),
                    res.getString("address"),
                    res.getString("password"),
                    res.getString("email")
            );

            return dto;
        }
        return null;
    }
}
