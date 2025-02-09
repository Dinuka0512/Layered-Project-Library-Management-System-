package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.SectionDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.Section;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SectionDAOimpl implements SectionDAO {
    @Override
    public boolean save(Section dto) throws SQLException, ClassNotFoundException {
        String sql = "insert into section values (?, ?)";
        boolean isSaved = CrudUtil.execute(
                sql,
                dto.getSection_Id(),
                dto.getName()
        );

        return isSaved;
    }
    @Override
    public String getSectionName(String sectionId) throws SQLException, ClassNotFoundException {
        String sql = "select name from section where Section_Id = ?";
        ResultSet res = CrudUtil.execute(sql,sectionId);
        if(res.next()){
            return res.getString("name");
        }
        return null;
    }
    @Override
    public ArrayList<String> getAllSectionIds() throws SQLException, ClassNotFoundException {
        String sql = "select section_Id from section";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<String> dto = new ArrayList<>();
        while (res.next()) {
            dto.add(res.getString("section_Id"));
        }
        return dto;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String sql = "select Section_Id from section order by Section_Id desc limit 1";
        ResultSet res = CrudUtil.execute(sql);
        if(res.next()){
            String lastId = res.getString("Section_Id"); // S002
            String subString = lastId.substring(1); //002
            int i = Integer.parseInt(subString); // 2
            i = i + 1;
            String newId = String.format("S%03d",i);
            return newId;
        }
        return "S001";
    }


    @Override
    public Section search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Section> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Section dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
