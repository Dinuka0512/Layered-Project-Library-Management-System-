package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.BookshelfBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookShelfDAO;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl.BookShelfDAOimpl;
import edu.ijse.gdse.libarymanagementsystem.dto.BookshelfDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Bookshelf;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookShelfBOimpl implements BookshelfBO {
    private BookShelfDAO bookShelfDAO = (BookShelfDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOKSHELF);

    @Override
    public boolean saveBokShelf(BookshelfDto dto) throws SQLException, ClassNotFoundException {
        return bookShelfDAO.save(new Bookshelf(dto.getBookshelfId(), dto.getLocation(), dto.getSectionId()));
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return bookShelfDAO.generateNewId();
    }

    @Override
    public String getBookShelfLocation(String id) throws SQLException, ClassNotFoundException {
        return bookShelfDAO.getBookShelfLocation(id);
    }

    @Override
    public ArrayList<String> getAllBookShelfIds() throws SQLException, ClassNotFoundException {
        return bookShelfDAO.getAllBookShelfIds();
    }
}
