package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.dto.BookSupplyNameAndQtyDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookSupplyBO {
    ArrayList<BookSupplyNameAndQtyDto> getSupplierNameAndAllBookSuppliedQty() throws SQLException,ClassNotFoundException;
}
