package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.ReturnBookDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.shortCuts.Barchart;
import edu.ijse.gdse.libarymanagementsystem.entity.shortCuts.Linechart;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnBookDAOimpl implements ReturnBookDAO {
    @Override
    public ArrayList<Linechart> getDataToAddLineChart() throws SQLException, ClassNotFoundException {
        String sql = "select count(bi.Book_Id) as count, r.date from Return_book r JOIN Issue i ON r.Issue_Id = i.Issue_Id JOIN book_Issue bi on i.Issue_Id = bi.Issue_Id group by r.date;";
        ResultSet res = CrudUtil.execute(sql);

        ArrayList<Linechart> linecharts = new ArrayList<>();
        while(res.next()){
            Linechart linechart = new Linechart(
                    res.getString("Date"),
                    res.getInt("count")
            );

            linecharts.add(linechart);
        }

        return linecharts;
    }

    @Override
    public ArrayList<Barchart> getBarchartValues() throws SQLException, ClassNotFoundException {
        String sql = "select Date, sum(Amount) as amount from Return_Book Group by Date";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<Barchart> dtos = new ArrayList<>();
        while(res.next()){
            Barchart dto = new Barchart(
                    res.getString("Date"),
                    res.getDouble("amount")
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public String getBookRetunId() throws SQLException, ClassNotFoundException {
        String sql = "select Return_Id from return_Book Order by Return_Id desc limit 1";
        ResultSet res = CrudUtil.execute(sql);
        if(res.next()){
            String lastId = res.getString("Return_Id"); // BR001
            String subString = lastId.substring(2); // 001
            int i = Integer.parseInt(subString); // 1
            i = i + 1;
            String newId = String.format("BR%03d", i);
            return newId;
        }
        return "BR001";
    }

    @Override
    public int getTodayBookReturns(String date) throws SQLException, ClassNotFoundException {
        String sql = "select count(Return_Id) as count from Return_Book where Date = ?";
        ResultSet res = CrudUtil.execute(sql,date);
        if(res.next()){
            return res.getInt("count");
        }
        return 0;
    }

    @Override
    public int getCashonHandToday(String date) throws SQLException, ClassNotFoundException {
        String sql = "select Sum(Amount) as amount from Return_Book where Date = ?";
        ResultSet res = CrudUtil.execute(sql, date);
        if(res.next()){
            return res.getInt("amount");
        }
        return 0;
    }
}
