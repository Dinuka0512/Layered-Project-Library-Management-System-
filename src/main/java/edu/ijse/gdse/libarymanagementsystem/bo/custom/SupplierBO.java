package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.SupplierDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.TempBookTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<BookDto> getAllBooks(String supplierId) throws SQLException, ClassNotFoundException;
    boolean save(SupplierDto dto, ArrayList<TempBookTM> arr) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDto dto, ArrayList<TempBookTM> arr) throws SQLException , ClassNotFoundException;
}
