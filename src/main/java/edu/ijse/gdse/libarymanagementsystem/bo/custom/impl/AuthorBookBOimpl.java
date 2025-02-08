package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.bo.custom.AuthorBookBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.AuthorBookDAO;

import java.sql.SQLException;

public class AuthorBookBOimpl implements AuthorBookBO {

    private AuthorBookDAO authorBookDAO = (AuthorBookDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.AUTHORBOOK);
    @Override
    public String getAuthorId(String bookId) throws SQLException, ClassNotFoundException {
        return authorBookDAO.getAuthorId(bookId);
    }
}
