package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.IssueTableDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.TempBookIssueTm;
import edu.ijse.gdse.libarymanagementsystem.entity.BookIdAndQty;
import edu.ijse.gdse.libarymanagementsystem.entity.IssueTable;
import edu.ijse.gdse.libarymanagementsystem.entity.tm.TempBookIssueTmEnt;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookIssueDAO extends CrudDAO<BookIdAndQty> {
    ArrayList<BookIdAndQty> getPopularBooks() throws SQLException, ClassNotFoundException;
}
