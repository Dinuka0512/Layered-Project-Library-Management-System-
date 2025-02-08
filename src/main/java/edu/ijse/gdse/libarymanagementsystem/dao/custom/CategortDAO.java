package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CategortDAO extends SuperDAO, CrudDAO<Category> {
    ArrayList<String> getAllCategoryIds() throws SQLException, ClassNotFoundException;
    String getCateName(String categoryId) throws SQLException,ClassNotFoundException;
    String getCategoryId(String name) throws  ClassNotFoundException, SQLException;
}
