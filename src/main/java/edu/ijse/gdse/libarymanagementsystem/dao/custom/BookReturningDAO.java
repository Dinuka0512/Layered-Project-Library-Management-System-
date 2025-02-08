package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.BookReturningTm;
import edu.ijse.gdse.libarymanagementsystem.entity.tm.BookReturningTmEnt;
import edu.ijse.gdse.libarymanagementsystem.entity.tm.ReturnBookQueree;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookReturningDAO extends SuperDAO {
    ArrayList<BookReturningTmEnt> loadTabel() throws SQLException,ClassNotFoundException;
    boolean returnBook(ReturnBookQueree returnBookQueree) throws SQLException, ClassNotFoundException;
}