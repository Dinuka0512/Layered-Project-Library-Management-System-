package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BranchDAO extends SuperDAO {
    ArrayList<String> getAllBranchIds();
    String getSelectedBranchName(String branchId);
}
