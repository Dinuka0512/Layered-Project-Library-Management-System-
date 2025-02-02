package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;

import java.sql.SQLException;

public interface BookDAO extends CrudDAO<Book> {
    Book getBookDetails(String bookId) throws ClassNotFoundException, SQLException;
}
