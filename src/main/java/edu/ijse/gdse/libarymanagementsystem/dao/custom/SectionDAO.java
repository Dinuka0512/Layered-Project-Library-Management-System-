package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dao.SuperDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.Section;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SectionDAO extends SuperDAO , CrudDAO<Section> {
    String getSectionName(String sectionId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSectionIds() throws SQLException, ClassNotFoundException;
}
