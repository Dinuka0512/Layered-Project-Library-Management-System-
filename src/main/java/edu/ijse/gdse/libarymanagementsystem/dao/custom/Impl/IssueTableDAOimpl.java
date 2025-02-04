package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.IssueTableDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.IssueTableDto;
import edu.ijse.gdse.libarymanagementsystem.entity.IssueTable;
import edu.ijse.gdse.libarymanagementsystem.entity.Linechart;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IssueTableDAOimpl implements IssueTableDAO {
    @Override
    public ArrayList<Linechart> getDataToAddLineChart() throws ClassNotFoundException, SQLException {
        String sql = "select Date, count(*) as count  from Issue Group by Date";
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
    public int getTodaYIssueBookCounts(String date) throws SQLException, ClassNotFoundException {
        String sql = "select count(bi.Book_Id) as count from issue i JOIN book_issue bi on i.Issue_Id = bi.Issue_Id where i.Date = ?";
        ResultSet res = CrudUtil.execute(sql,date);
        if(res.next()){
            return res.getInt("count");
        }
        return 0;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String sql = "select Issue_Id from Issue order by Issue_Id desc limit 1";
        ResultSet res = CrudUtil.execute(sql);
        if(res.next()){
            String lastId = res.getString("Issue_Id"); //B001
            String subString = lastId.substring(1);  //001
            int i = Integer.parseInt(subString); //1
            int newIndex = i + 1; //2
            return String.format("I%03d",newIndex); //B002
        }

        return "I001";
    }

    @Override
    public ArrayList<IssueTable> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from Issue";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<IssueTable> dtos = new ArrayList<>();
        while(res.next()){
            IssueTable dto =new IssueTable(
                    res.getString("Issue_Id"),
                    res.getString("Member_Id"),
                    res.getString("User_Id"),
                    res.getString("Date"),
                    res.getString("Time"),
                    res.getBoolean("isCompleted")
            );

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public boolean save(IssueTable dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(IssueTable dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public IssueTable search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
