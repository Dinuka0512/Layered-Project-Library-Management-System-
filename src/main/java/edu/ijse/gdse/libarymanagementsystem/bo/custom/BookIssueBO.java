package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.IssueTableDto;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.BookIdAndQty;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.TempBookIssueTm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookIssueBO extends SuperBO {
    ArrayList<BookIdAndQty> getPopularBooks() throws SQLException, ClassNotFoundException;
    boolean issueNow(IssueTableDto dto, ArrayList<TempBookIssueTm> tempDetails) throws SQLException, ClassNotFoundException;
}
