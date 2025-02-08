package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;
import edu.ijse.gdse.libarymanagementsystem.entity.Bookshelf;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookShelfDAO extends CrudDAO<Bookshelf>,SuperDAO {
    String getBookShelfLocation(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllBookShelfIds() throws SQLException, ClassNotFoundException;
}
