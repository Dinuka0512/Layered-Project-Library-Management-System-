package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.dto.SectionDto;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SectionModel {
    public ArrayList<String> getAllSectionIds() {
        try {
            String sql = "select section_Id from section";
            ResultSet res = CrudUtil.execute(sql);
            ArrayList<String> dto = new ArrayList<>();
            while (res.next()) {
                dto.add(res.getString("section_Id"));
            }
            return dto;

        }catch (SQLException e1){
            System.out.println("SQL EXCEPTION");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not found Exception");
            e2.printStackTrace();
        }
        return null;
    }

    public String getSectionName(String sectionId) throws SQLException, ClassNotFoundException{
        String sql = "select name from section where Section_Id = ?";
        ResultSet res = CrudUtil.execute(sql,sectionId);
        if(res.next()){
            return res.getString("name");
        }
        return null;
    }

    public String generateNextId() throws SQLException, ClassNotFoundException{
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

    public boolean saveSection(SectionDto dto) throws SQLException, ClassNotFoundException{
        String sql = "insert into section values (?, ?)";
        boolean isSaved = CrudUtil.execute(
                sql,
                dto.getSection_Id(),
                dto.getName()
        );

        return isSaved;
    }
}
