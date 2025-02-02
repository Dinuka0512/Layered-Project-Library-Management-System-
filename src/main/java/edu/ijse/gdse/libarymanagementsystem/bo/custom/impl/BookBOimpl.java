package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.BookBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookBOimpl implements BookBO {
    BookDAO bookDAO = (BookDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOK);

    //HERE GET THE BOOK'S DETAILS
    @Override
    public BookDto getBookDetails(String bookId) throws ClassNotFoundException, SQLException {
        Book book =  bookDAO.getBookDetails(bookId);
        return new BookDto(book.getBookId(),book.getName(), book.getQty(),book.getPrice(),book.getBookShelfId());
    }

    //HERE GET THE ALL BOOKS DETAILS
    @Override
    public ArrayList<BookDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Book> bookArray = bookDAO.getAll();
        ArrayList<BookDto> bookDtos = new ArrayList<>();
        for(Book book :bookArray){
            BookDto bookDto = new BookDto(
                    book.getBookId(),
                    book.getName(),
                    book.getQty(),
                    book.getPrice(),
                    book.getBookShelfId()
            );

            bookDtos.add(bookDto);
        }

        return bookDtos;
    }

    //HERE GENERATE ID
    @Override
    public String getNextBookId() throws SQLException, ClassNotFoundException {
        return bookDAO.generateNewId();
    }

    @Override
    public boolean deleteBook(String book_Id) throws SQLException, ClassNotFoundException {
        return bookDAO.delete(book_Id);
    }

    @Override
    public String getBookShelfId(String bookId) throws SQLException, ClassNotFoundException {
        return bookDAO.getBookShelfId(bookId);
    }

    @Override
    public ArrayList<String> getAllBookIds() throws ClassNotFoundException, SQLException {
        return bookDAO.getAllBookIds();
    }

    @Override
    public ArrayList<BookDto> searchBook(String name) throws SQLException, ClassNotFoundException {
        ArrayList<BookDto> bookDtoList = new ArrayList<>();
        ArrayList<Book> bookList = bookDAO.searchBook(name);
        for(Book book: bookList){
            BookDto dto = new BookDto(
                    book.getBookId(),
                    book.getName(),
                    book.getQty(),
                    book.getPrice(),
                    book.getBookShelfId()
            );

            bookDtoList.add(dto);
        }

        return bookDtoList;
    }
}
