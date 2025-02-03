package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookSuplyDto;
import edu.ijse.gdse.libarymanagementsystem.dto.BookSupplyNameAndQtyDto;
import edu.ijse.gdse.libarymanagementsystem.entity.BookSuply;
import edu.ijse.gdse.libarymanagementsystem.entity.BookSupplyNameAndQty;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookSupplyDAO extends CrudDAO<BookSuply> {
    ArrayList<BookSupplyNameAndQty> getSupplierNameAndAllBookSuppliedQty() throws SQLException, ClassNotFoundException;
}
