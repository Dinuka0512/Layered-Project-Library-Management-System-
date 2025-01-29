package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl.BranchDAOImpl;
import edu.ijse.gdse.libarymanagementsystem.entity.Branch;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BranchDAO extends SuperDAO {
    ArrayList<String> getAllBranchIds();
    String getSelectedBranchName(String branchId);
}
