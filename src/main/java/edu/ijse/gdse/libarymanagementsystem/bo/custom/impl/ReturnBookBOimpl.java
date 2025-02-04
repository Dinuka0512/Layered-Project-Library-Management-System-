package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.ReturnBookBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.ReturnBookDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.BarchartDto;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.LineChartDto;
import edu.ijse.gdse.libarymanagementsystem.entity.shortCuts.Barchart;
import edu.ijse.gdse.libarymanagementsystem.entity.shortCuts.Linechart;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnBookBOimpl implements ReturnBookBO {
    private ReturnBookDAO returnBookDAO = (ReturnBookDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RETURNBOOK);
    @Override
    public ArrayList<LineChartDto> getDataToAddLineChart() throws SQLException, ClassNotFoundException {
        ArrayList<Linechart> linecharts = returnBookDAO.getDataToAddLineChart();
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

    @Override
    public ArrayList<BarchartDto> getBarchartValues() throws SQLException, ClassNotFoundException {
        ArrayList<Barchart> barchart = returnBookDAO.getBarchartValues();
        ArrayList<BarchartDto> barchartDtos = new ArrayList<>();
        for(Barchart dto: barchart){
            BarchartDto barchartDto = new BarchartDto(
                    dto.getDate(),
                    dto.getAmount()
            );

            barchartDtos.add(barchartDto);
        }
        return barchartDtos;
    }

    @Override
    public String getBookRetunId() throws SQLException, ClassNotFoundException {
        return returnBookDAO.getBookRetunId();
    }

    @Override
    public int getTodayBookReturns(String date) throws SQLException, ClassNotFoundException {
        return returnBookDAO.getTodayBookReturns(date);
    }

    @Override
    public int getCashonHandToday(String date) throws SQLException, ClassNotFoundException {
        return returnBookDAO.getCashonHandToday(date);
    }
}
