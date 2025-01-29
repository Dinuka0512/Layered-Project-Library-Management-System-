package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.UserDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.User;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
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

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
