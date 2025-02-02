package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.UserDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.UserDto;
import edu.ijse.gdse.libarymanagementsystem.entity.User;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public UserDto getUserDetails(String userEmail) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE email = ?";

        ResultSet res = CrudUtil.execute(sql,userEmail);
        res.next();

        UserDto dto = new UserDto(
                res.getString("User_Id"),
                res.getString("name"),
                res.getString("address"),
                res.getString("password"),
                res.getString("email")
        );

        return dto;
    }

    @Override
    public String getUserId(String email){
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

    //USER UNIQUE METHOD
    //HERE CHECK THE

    @Override
    public boolean isUniqueEmail(String email) throws SQLException, ClassNotFoundException {
        String sql = "select email from User where email = ?";

        ResultSet res = CrudUtil.execute(sql,email);
        if(res.next()){
            //Email eka allredy thiyenawa
            return false;
        }

        //email eka na
        return true;
    }


    //USER SAVING DONE
    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        String sql = "Insert Into User Values (?,?,?,?,?,?)";
        boolean res = CrudUtil.execute(
                sql,
                dto.getUserId(),
                dto.getBranchId(),
                dto.getName(),
                dto.getAddress(),
                dto.getPassword(),
                dto.getEmail()
        );
        return res;
    }

    //HERE GENERATING THE USER ID
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
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


    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user SET name = ? , password = ? WHERE email = ?";
        boolean res = CrudUtil.execute(sql,dto.getName(),dto.getPassword(),dto.getEmail());
        return res;
    }

    /*
    * THESE METHODS ARE NOT USED YET
    * ##############################
    * */

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
