package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookBO extends SuperBO {
    BookDto getBookDetails(String bookId) throws ClassNotFoundException, SQLException;
    ArrayList<BookDto> getAll() throws SQLException, ClassNotFoundException;
    String getNextBookId() throws SQLException, ClassNotFoundException;
    boolean deleteBook(String book_Id) throws  SQLException, ClassNotFoundException;
    String getBookShelfId(String bookId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllBookIds() throws ClassNotFoundException, SQLException;
    ArrayList<BookDto> searchBook(String name) throws SQLException, ClassNotFoundException;
}
