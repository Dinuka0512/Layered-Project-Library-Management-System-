package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.SectionDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SectionBO extends SuperBO {
    String generateNextId() throws SQLException, ClassNotFoundException;
    String getSectionName(String sectionId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSectionIds() throws SQLException, ClassNotFoundException;
    boolean saveSection(SectionDto dto) throws SQLException, ClassNotFoundException;
}
