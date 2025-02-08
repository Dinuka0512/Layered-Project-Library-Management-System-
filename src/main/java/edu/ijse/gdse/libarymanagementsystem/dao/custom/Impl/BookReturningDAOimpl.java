package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookReturningDAO;
import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.entity.tm.BookReturningTmEnt;
import edu.ijse.gdse.libarymanagementsystem.entity.tm.ReturnBookQueree;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.Connection;
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

    @Override
    public boolean returnBook(ReturnBookQueree returnBookQueree) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        try{
            con.setAutoCommit(false);

            //UPDATE BOOK RETURNING
            String updateSql = "UPDATE Issue i JOIN Book_Issue bi ON i.Issue_Id = bi.Issue_Id SET i.isCompleted = true WHERE i.Issue_Id = ? AND bi.Book_Id = ?";
            boolean isSupdated = CrudUtil.execute(
                    updateSql,
                    returnBookQueree.getIssueId(),
                    returnBookQueree.getBookId()
            );

            if(isSupdated){
                //NOW NEED TO BOOK RETURNING TABLE INSERT
                String bookReturningsql = "INSERT INTO return_Book VALUES (?,?,?,?,?,?)";
                boolean isSaved = CrudUtil.execute(
                        bookReturningsql,
                        returnBookQueree.getId(),
                        returnBookQueree.getIssueId(),
                        1,
                        returnBookQueree.getDate(),
                        returnBookQueree.getFormattedTime(),
                        returnBookQueree.getFee()
                );

                if(isSaved){
                    String updateBook = " update book set qty = qty+1 where Book_Id = ?";
                    boolean isUpdated = CrudUtil.execute(updateBook,returnBookQueree.getBookId());
                    if(isUpdated){
                        con.commit();
                        return true;
                    }
                }
            }
            return false;

        }catch (Exception e){
            con.rollback();
            System.out.println("Somthing Went wrong");
            e.printStackTrace();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }
}