package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAOimpl implements BookDAO {

    //--------------->>>
    @Override
    public ArrayList<Book> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

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
