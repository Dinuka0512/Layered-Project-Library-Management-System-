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
    public boolean returnBook(String id, BookDto bookDetails, String issueId , double fee) throws SQLException, ClassNotFoundException {
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

                LocalTime now = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
                String formattedTime = now.format(formatter).toLowerCase();

                //NOW NEED TO BOOK RETURNING TABLE INSERT
                String bookReturningsql = "INSERT INTO return_Book VALUES (?,?,?,?,?,?)";
                boolean isSaved = CrudUtil.execute(
                        bookReturningsql,
                        id,
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
