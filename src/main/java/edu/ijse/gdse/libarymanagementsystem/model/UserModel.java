package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.dto.UserDto;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public boolean updateUser(String userName, String password,String email) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE user SET name = ? , password = ? WHERE email = ?";
        boolean res = CrudUtil.execute(sql,userName,password,email);
        return res;
    }


    public String getUserId(String email) {
        try{
            String sql = "select User_Id from user where email = ?";
            ResultSet res = CrudUtil.execute(sql, email);
            if(res.next()){
                return res.getString("User_Id");
            }
            return null;
        }catch (SQLException e1){
            System.out.println("SQL Exception");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not found Exception");
            e2.printStackTrace();
        }
        return null;
    }
}
