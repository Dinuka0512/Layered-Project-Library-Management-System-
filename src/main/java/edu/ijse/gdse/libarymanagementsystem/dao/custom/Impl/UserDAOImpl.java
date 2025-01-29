package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.UserDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.User;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
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


    /*
    * THESE METHODS ARE NOT USED YET
    * ##############################
    * */

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
