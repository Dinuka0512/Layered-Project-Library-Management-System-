package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAOimpl implements BookDAO {
    //HERE GET THE ALL BOOK DETAILS
    @Override
    public ArrayList<Book> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from Book";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<Book> dtos =  new ArrayList<>();
        while(res.next()){
            Book dto = new Book(
                    res.getString("Book_Id"),
                    res.getString("name"),
                    res.getInt("qty"),
                    res.getDouble("price"),
                    res.getString("bookshelf_Id")
            );

            dtos.add(dto);
        }
        return dtos;
    }

    //HERE GET THE BOOK DETAILS
    @Override
    public Book getBookDetails(String bookId) throws ClassNotFoundException, SQLException {
        String sql = "select * from Book where Book_Id = ?";
        ResultSet res = CrudUtil.execute(
                sql,
                bookId
        );

        if(res.next()){
            Book dto = new Book(
                    res.getString("Book_Id"),
                    res.getString("name"),
                    res.getInt("qty"),
                    res.getDouble("price"),
                    res.getString("bookshelf_Id")
            );
            return dto;
        }
        return null;
    }

    //--------------->>>

    @Override
    public boolean save(Book dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Book dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Book search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
