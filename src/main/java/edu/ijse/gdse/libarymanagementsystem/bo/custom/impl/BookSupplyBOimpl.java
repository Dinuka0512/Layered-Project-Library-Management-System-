package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.bo.custom.BookSupplyBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookSupplyDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookSupplyNameAndQtyDto;
import edu.ijse.gdse.libarymanagementsystem.entity.BookSuply;
import edu.ijse.gdse.libarymanagementsystem.entity.BookSupplyNameAndQty;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookSupplyBOimpl implements BookSupplyBO, SuperBO {
    private BookSupplyDAO bookSupplyDAO = (BookSupplyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOKSUPPLY);
    @Override
    public ArrayList<BookSupplyNameAndQtyDto> getSupplierNameAndAllBookSuppliedQty() throws SQLException, ClassNotFoundException {
        ArrayList<BookSupplyNameAndQty> bookSupplyNameAndQtyList = bookSupplyDAO.getSupplierNameAndAllBookSuppliedQty();
        ArrayList<BookSupplyNameAndQtyDto> bookSupplyNameAndQtyDtos = new ArrayList<>();
        for(BookSupplyNameAndQty bookSupplyNameAndQty : bookSupplyNameAndQtyList){
            BookSupplyNameAndQtyDto dto = new BookSupplyNameAndQtyDto(
                    bookSupplyNameAndQty.getSupplierName(),
                    bookSupplyNameAndQty.getQty()
            );

            bookSupplyNameAndQtyDtos.add(dto);
        }

        return bookSupplyNameAndQtyDtos;
    }
}
