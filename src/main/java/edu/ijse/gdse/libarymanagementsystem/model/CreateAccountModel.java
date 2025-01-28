package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.UserDto;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CreateAccountModel{
    public String saveUser(UserDto dto, String branchID) throws SQLException,ClassNotFoundException{
        String sql = "Insert Into User Values (?,?,?,?,?,?)";
        boolean res = CrudUtil.execute(
                sql,
                dto.getUserId(),
                branchID,
                dto.getName(),
                dto.getAddress(),
                dto.getPassword(),
                dto.getEmail()
        );
        return res == true? "Added Successfully" : "Added Failed";
    }

    public String genarateId() throws SQLException, ClassNotFoundException {
        String sql = "select User_Id from User Order by User_Id desc limit 1";
        ResultSet res = CrudUtil.execute(sql);

        if(res.next()){
            String id = res.getString("User_Id");
            String subid = id.substring(1);//001
            int lastId = Integer.parseInt(subid);
            int genaratedId = lastId + 1;
            String newId = String.format("U%03d", genaratedId);
            return newId;
        }else{
            return "U001";
        }
    }

    public boolean isUniqueEmail(String email) throws SQLException, ClassNotFoundException{
        String sql = "select email from User where email = ?";

        ResultSet res = CrudUtil.execute(sql,email);
        if(res.next()){
            //Email eka allredy thiyenawa
            return false;
        }

        //email eka na
        return true;
    }
}
