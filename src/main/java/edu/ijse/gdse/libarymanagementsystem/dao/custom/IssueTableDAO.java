package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.IssueTable;
import edu.ijse.gdse.libarymanagementsystem.entity.Linechart;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IssueTableDAO extends CrudDAO<IssueTable> {
    int getTodaYIssueBookCounts(String date) throws SQLException, ClassNotFoundException;
    ArrayList<Linechart> getDataToAddLineChart() throws ClassNotFoundException, SQLException;
}
