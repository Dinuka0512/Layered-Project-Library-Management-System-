package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDAO extends CrudDAO<Book> {
    Book getBookDetails(String bookId) throws ClassNotFoundException, SQLException;
    String getBookShelfId(String bookId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllBookIds() throws ClassNotFoundException, SQLException;
    ArrayList<Book> searchBook(String name) throws SQLException, ClassNotFoundException;
}
