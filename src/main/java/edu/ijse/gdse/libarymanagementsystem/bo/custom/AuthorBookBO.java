package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;

import java.sql.SQLException;

public interface AuthorBookBO extends SuperBO {
    String getAuthorId(String bookId) throws SQLException, ClassNotFoundException;
}
