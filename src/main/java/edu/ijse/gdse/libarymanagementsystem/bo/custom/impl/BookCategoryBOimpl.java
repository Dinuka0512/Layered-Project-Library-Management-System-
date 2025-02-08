package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.BookCategoryBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookCategoryDAO;

import java.sql.SQLException;

public class BookCategoryBOimpl implements BookCategoryBO {
    private BookCategoryDAO bookCategoryDAO = (BookCategoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOKCATEGORY);

    @Override
    public String getCategoryId(String bookId) throws ClassNotFoundException, SQLException {
        return bookCategoryDAO.getCategoryId(bookId);
    }
}
