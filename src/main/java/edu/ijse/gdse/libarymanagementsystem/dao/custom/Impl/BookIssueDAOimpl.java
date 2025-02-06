package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookIssueDAO;
import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.IssueTableDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.TempBookIssueTm;
import edu.ijse.gdse.libarymanagementsystem.entity.BookIdAndQty;
import edu.ijse.gdse.libarymanagementsystem.entity.IssueTable;
import edu.ijse.gdse.libarymanagementsystem.entity.tm.TempBookIssueTmEnt;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookIssueDAOimpl implements BookIssueDAO {
    @Override
    public ArrayList<BookIdAndQty> getPopularBooks() throws SQLException, ClassNotFoundException {
        String sql = "select Book_Id,sum(qty) as qty from Book_Issue Group by Book_Id limit 4";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<BookIdAndQty> dtos = new ArrayList<>();
        while(res.next()){
            BookIdAndQty dto = new BookIdAndQty(
                    res.getString("Book_Id"),
                    res.getInt("qty")
            );

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public ArrayList<BookIdAndQty> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(BookIdAndQty dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(BookIdAndQty dto) throws SQLException, ClassNotFoundException {
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
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public BookIdAndQty search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
