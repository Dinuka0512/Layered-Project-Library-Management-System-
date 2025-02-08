package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.CategoryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoryBO extends SuperBO {
    String generateCategoryID() throws ClassNotFoundException, SQLException;
    ArrayList<String> getAllCategoryIds() throws SQLException, ClassNotFoundException;
    String getCateName(String categoryId) throws SQLException,ClassNotFoundException;
    boolean saveNewCategory(CategoryDto categoryDto) throws ClassNotFoundException, SQLException;
    String getCategoryId(String name) throws  ClassNotFoundException, SQLException;
}
