package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.BookIssueBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookIssueDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.BookIdAndQty;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookIssueBOimpl implements BookIssueBO {
    private BookIssueDAO bookIssueDAO = (BookIssueDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOKISSUE);
    @Override
    public ArrayList<BookIdAndQty> getPopularBooks() throws SQLException, ClassNotFoundException {
        ArrayList<edu.ijse.gdse.libarymanagementsystem.entity.BookIdAndQty> bookIdAndQtyArrayList =  bookIssueDAO.getPopularBooks();
        ArrayList<BookIdAndQty> dtos = new ArrayList<>();
        for(edu.ijse.gdse.libarymanagementsystem.entity.BookIdAndQty bookIdAndQty : bookIdAndQtyArrayList){
            BookIdAndQty dto = new BookIdAndQty(
                    bookIdAndQty.getBook_Id(),
                    bookIdAndQty.getQty()
            );

            dtos.add(dto);
        }

        return dtos;
    }
}
