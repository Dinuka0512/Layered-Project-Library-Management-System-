package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.BranchBO;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.BranchDAO;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl.BranchDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class BranchBOimpl implements BranchBO {
    private BranchDAO branchDAO = new BranchDAOImpl();
    @Override
    public ArrayList<String> getAllBranchIds() throws SQLException,ClassNotFoundException{
        return branchDAO.getAllBranchIds();
    }

    @Override
    public String getSelectedBranchName(String branchId) throws ClassNotFoundException, SQLException {
        return branchDAO.getSelectedBranchName(branchId);
    }
}
