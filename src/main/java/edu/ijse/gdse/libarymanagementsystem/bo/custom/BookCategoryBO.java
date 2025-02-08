package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;

import java.sql.SQLException;

public interface BookCategoryBO extends SuperBO {
    String getCategoryId(String bookId) throws  ClassNotFoundException, SQLException;
}
