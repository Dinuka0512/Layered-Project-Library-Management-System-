package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.BookReturningTm;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ManageBookReturningModel {
    private final ReturnBookModel returnBookModel = new ReturnBookModel();
    public ArrayList<BookReturningTm> loadTabel() throws SQLException,ClassNotFoundException {
        String sql = "select i.Issue_Id, bi.Book_Id, m.Member_Id, m.name AS Member_Name, m.email, b.name AS Book_Name, i.Date, i.Time FROM Issue i JOIN Book_Issue bi ON i.Issue_Id = bi.Issue_Id JOIN Member m ON i.Member_Id = m.Member_Id JOIN Book b ON bi.Book_Id = b.Book_Id WHERE i.isCompleted = 0 ORDER BY i.Issue_Id;\n";
        ArrayList<BookReturningTm> bookReturningTms = new ArrayList<>();
        ResultSet res = CrudUtil.execute(sql);

        while(res.next()){
            BookReturningTm bookReturningTm = new BookReturningTm(
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



    public boolean returnBook(BookDto bookDetails, String issueId , double fee) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        try{
            con.setAutoCommit(false);

            //UPDATE BOOK RETURNING
            String updateSql = "UPDATE Issue i JOIN Book_Issue bi ON i.Issue_Id = bi.Issue_Id SET i.isCompleted = true WHERE i.Issue_Id = ? AND bi.Book_Id = ?";
            boolean isSupdated = CrudUtil.execute(
                    updateSql,
                    issueId,
                    bookDetails.getBookId()
            );

            if(isSupdated){
                String date = String.valueOf(LocalDate.now());
                String retunId = returnBookModel.getBookRetunId();

                LocalTime now = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
                String formattedTime = now.format(formatter).toLowerCase();

                //NOW NEED TO BOOK RETURNING TABLE INSERT
                String bookReturningsql = "INSERT INTO return_Book VALUES (?,?,?,?,?,?)";
                boolean isSaved = CrudUtil.execute(
                        bookReturningsql,
                        retunId,
                        issueId,
                        1,
                        date,
                        formattedTime,
                        fee
                );

                if(isSaved){
                    con.commit();
                    return true;
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
