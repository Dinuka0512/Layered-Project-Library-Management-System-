package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;

import java.sql.SQLException;

public interface BookCategoryDAO extends SuperDAO {
    String getCategoryId(String bookId) throws  ClassNotFoundException, SQLException;
}
