package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.shortCuts.Barchart;
import edu.ijse.gdse.libarymanagementsystem.entity.shortCuts.Linechart;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReturnBookDAO extends SuperDAO {
    ArrayList<Linechart> getDataToAddLineChart() throws SQLException, ClassNotFoundException;
    ArrayList<Barchart> getBarchartValues() throws SQLException, ClassNotFoundException;
    String getBookRetunId() throws SQLException, ClassNotFoundException;
    int getTodayBookReturns(String date) throws SQLException, ClassNotFoundException;
    int getCashonHandToday(String date) throws SQLException, ClassNotFoundException;
}
