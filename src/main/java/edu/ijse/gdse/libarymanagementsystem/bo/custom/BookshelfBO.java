package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookshelfDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookshelfBO extends SuperBO {
    boolean saveBokShelf(BookshelfDto dto) throws SQLException, ClassNotFoundException;
    String generateNextId() throws SQLException, ClassNotFoundException;
    String getBookShelfLocation(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllBookShelfIds() throws SQLException, ClassNotFoundException;
}
