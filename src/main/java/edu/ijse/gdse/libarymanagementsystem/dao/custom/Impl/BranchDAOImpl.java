package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.BranchDAO;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BranchDAOImpl implements BranchDAO {

    @Override
    public ArrayList<String> getAllBranchIds(){
        //HERE GET ALL BRANCH IDS
        try{
            String sql = "select Branch_Id from Branch";
            ResultSet res = CrudUtil.execute(sql);
            ArrayList<String> ids = new ArrayList<>();

            while(res.next()){
                ids.add(res.getString("Branch_Id"));
            }

            return ids;

        }catch (SQLException e1){
            System.out.println("SQL EXCEPTION");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("CLASS NOT FOUND EXCEPTION");
            e2.printStackTrace();
        }
        return null;
    }

    @Override
    public String getSelectedBranchName(String branchId) {
        try{
            String sql = "select name from Branch where Branch_Id = ?";
            ResultSet res = CrudUtil.execute(sql,branchId);
            if(res.next()){
                return res.getString("name");
            }
        }catch (ClassNotFoundException e1){
            System.out.println("Class not found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
        return null;
    }
}
