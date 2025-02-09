package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;
import edu.ijse.gdse.libarymanagementsystem.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<Supplier>, SuperDAO {
    ArrayList<Book> getAllBooks(String supplierId) throws SQLException, ClassNotFoundException;
}
