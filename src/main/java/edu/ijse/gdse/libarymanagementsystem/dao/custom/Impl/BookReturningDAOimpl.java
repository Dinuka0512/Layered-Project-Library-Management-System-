package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.BookReturningBO;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookReturningDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.BookReturningTm;
import edu.ijse.gdse.libarymanagementsystem.entity.tm.BookReturningTmEnt;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookReturningDAOimpl implements BookReturningDAO {

    @Override
    public ArrayList<BookReturningTmEnt> loadTabel() throws SQLException, ClassNotFoundException {
        String sql = "select i.Issue_Id, bi.Book_Id, m.Member_Id, m.name AS Member_Name, m.email, b.name AS Book_Name, i.Date, i.Time FROM Issue i JOIN Book_Issue bi ON i.Issue_Id = bi.Issue_Id JOIN Member m ON i.Member_Id = m.Member_Id JOIN Book b ON bi.Book_Id = b.Book_Id WHERE i.isCompleted = 0 ORDER BY i.Issue_Id;\n";
        ArrayList<BookReturningTmEnt> bookReturningTms = new ArrayList<>();
        ResultSet res = CrudUtil.execute(sql);

        while(res.next()){
            BookReturningTmEnt bookReturningTm = new BookReturningTmEnt(
                    res.getString("Issue_Id"),
                    res.getString("Book_Id"),
                    res.getString("Member_Id"),
                    res.getString("Member_Name"),
                    res.getString("email"),
                    res.getString("Book_Name"),
                    res.getString("Date"),
                    res.getString("Time")
            );

            bookReturningTms.add(bookReturningTm);
        }

        return bookReturningTms;
    }
}