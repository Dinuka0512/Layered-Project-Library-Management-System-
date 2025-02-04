package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.IssueTableDto;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.LineChartDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IssueTableBO extends SuperBO {
    String getNextIssueId() throws SQLException, ClassNotFoundException;
    ArrayList<IssueTableDto> getAllData() throws SQLException, ClassNotFoundException;
    int getTodaYIssueBookCounts(String date) throws SQLException, ClassNotFoundException;
    ArrayList<LineChartDto> getDataToAddLineChart() throws ClassNotFoundException, SQLException;
}
