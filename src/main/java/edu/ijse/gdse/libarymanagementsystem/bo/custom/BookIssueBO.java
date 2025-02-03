package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.BookIdAndQty;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookIssueBO extends SuperBO {
    ArrayList<BookIdAndQty> getPopularBooks() throws SQLException, ClassNotFoundException;
}
