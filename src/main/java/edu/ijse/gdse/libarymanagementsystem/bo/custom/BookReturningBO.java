package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.BookReturningTm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookReturningBO extends SuperBO {
    ArrayList<BookReturningTm> loadTabel() throws SQLException,ClassNotFoundException;
    boolean returnBook(String id, BookDto bookDetails, String issueId , double fee) throws SQLException, ClassNotFoundException;
}
