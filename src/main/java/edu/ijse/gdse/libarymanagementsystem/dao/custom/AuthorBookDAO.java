package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;

import java.sql.SQLException;

public interface AuthorBookDAO extends SuperDAO {
    String getAuthorId(String bookId) throws SQLException, ClassNotFoundException;
}
