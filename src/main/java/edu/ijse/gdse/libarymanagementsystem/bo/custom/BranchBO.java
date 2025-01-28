package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BranchBO extends SuperBO {
    ArrayList<String> getAllBranchIds() throws ClassNotFoundException, SQLException;
    String getSelectedBranchName(String branchId) throws ClassNotFoundException,SQLException;
}
