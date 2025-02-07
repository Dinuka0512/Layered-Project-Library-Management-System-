package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.BookReturningTm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookReturningBO extends SuperBO {
    ArrayList<BookReturningTm> loadTabel() throws SQLException,ClassNotFoundException;
}
