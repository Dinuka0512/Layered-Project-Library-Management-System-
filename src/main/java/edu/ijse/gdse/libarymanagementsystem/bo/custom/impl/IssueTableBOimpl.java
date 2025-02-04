package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.IssueTableBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.IssueTableDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.IssueTableDto;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.LineChartDto;
import edu.ijse.gdse.libarymanagementsystem.entity.IssueTable;
import edu.ijse.gdse.libarymanagementsystem.entity.Linechart;

import java.sql.SQLException;
import java.util.ArrayList;

public class IssueTableBOimpl implements IssueTableBO {
    private IssueTableDAO issueTableDAO = (IssueTableDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ISSUE);
    @Override
    public String getNextIssueId() throws SQLException, ClassNotFoundException {
        return issueTableDAO.generateNewId();
    }

    @Override
    public ArrayList<IssueTableDto> getAllData() throws SQLException, ClassNotFoundException {
        ArrayList<IssueTable> issueTable = issueTableDAO.getAll();
        ArrayList<IssueTableDto> issueTableDtos = new ArrayList<>();
        for(IssueTable issue : issueTable){
            IssueTableDto issueTableDto = new IssueTableDto(
                    issue.getIssueId(),
                    issue.getMemId(),
                    issue.getUserId(),
                    issue.getDate(),
                    issue.getTime(),
                    issue.isCompleted()
            );

            issueTableDtos.add(issueTableDto);
        }

        return issueTableDtos;
    }

    @Override
    public int getTodaYIssueBookCounts(String date) throws SQLException, ClassNotFoundException {
        return issueTableDAO.getTodaYIssueBookCounts(date);
    }

    @Override
    public ArrayList<LineChartDto> getDataToAddLineChart() throws ClassNotFoundException, SQLException {
        ArrayList<Linechart> linecharts = issueTableDAO.getDataToAddLineChart();
        ArrayList<LineChartDto> dtos = new ArrayList<>();
        for(Linechart linechart : linecharts){
            LineChartDto dto = new LineChartDto(
                    linechart.getDate(),
                    linechart.getCount()
            );

            dtos.add(dto);
        }
        return dtos;
    }
}
