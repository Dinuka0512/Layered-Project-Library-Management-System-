package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.SectionBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.SectionDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.SectionDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Section;

import java.sql.SQLException;
import java.util.ArrayList;

public class SectionBOimpl implements SectionBO {
    private SectionDAO sectionDAO = (SectionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SECTION);
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return sectionDAO.generateNewId();
    }
    @Override
    public String getSectionName(String sectionId) throws SQLException, ClassNotFoundException {
        return sectionDAO.getSectionName(sectionId);
    }
    @Override
    public ArrayList<String> getAllSectionIds() throws SQLException, ClassNotFoundException {
        return sectionDAO.getAllSectionIds();
    }
    @Override
    public boolean saveSection(SectionDto dto) throws SQLException, ClassNotFoundException {
        return sectionDAO.save(new Section(dto.getSection_Id(), dto.getName()));
    }
}
