package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.dto.CategoryDto;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryModel {
    public String generateCategoryID() throws ClassNotFoundException, SQLException{
        String sql = "select Category_Id from Category Order By Category_Id desc limit 1";
        ResultSet res = CrudUtil.execute(sql);
        if(res.next()){
            String lastId = res.getString("Category_Id"); //Cate001
            String subString = lastId.substring(4); //001
            int i = Integer.parseInt(subString); //1
            i = i + 1; //2
            String newId = String.format("Cate%03d",i); // Cate002

            return newId;
        }

        return "Cate001";
    }

    public ArrayList<String> getAllCategoryIds(){
        try{
            String sql = "select Category_Id from Category";
            ResultSet res = CrudUtil.execute(sql);

            ArrayList<String> dto =new ArrayList<>();
            while(res.next()){
                String name = res.getString("Category_Id");
                dto.add(name);
            }
            return dto;

        }catch (SQLException e1){
            System.out.println("SQL Exception");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not Found");
            e2.printStackTrace();
        }

        return null;
    }

    public String getCateName(String categoryId) throws SQLException,ClassNotFoundException{
        String sql = "select * from category where Category_Id = ?";
        ResultSet res = CrudUtil.execute(sql,categoryId);
        if(res.next()){
            String name = res.getString("name");
            return name;
        }
        return null;
    }

    public boolean saveNewCategory(CategoryDto categoryDto) throws ClassNotFoundException, SQLException {
        String sql = "insert into Category values (?,?)";
        boolean isSaved = CrudUtil.execute(
                sql,
                categoryDto.getCategoryId(),
                categoryDto.getName()
        );

        return isSaved;
    }

    public String getCategoryId(String name) throws  ClassNotFoundException, SQLException{
        String sql = "select Category_Id from Category where  name = ?";
        ResultSet res = CrudUtil.execute(sql, name);
        if(res.next()){
            return  res.getString("Category_Id");
        }
        return null;
    }
}
