package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.BarchartDto;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.LineChartDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReturnBookBO extends SuperBO {
    ArrayList<LineChartDto> getDataToAddLineChart() throws SQLException, ClassNotFoundException;
    ArrayList<BarchartDto> getBarchartValues() throws SQLException, ClassNotFoundException;
    String getBookRetunId() throws SQLException, ClassNotFoundException;
    int getTodayBookReturns(String date) throws SQLException, ClassNotFoundException;
    int getCashonHandToday(String date) throws SQLException, ClassNotFoundException;
}
