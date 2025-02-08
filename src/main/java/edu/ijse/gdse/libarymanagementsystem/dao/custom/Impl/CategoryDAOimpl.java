package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.CategortDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.Category;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAOimpl implements CategortDAO {
    @Override
    public String getCategoryId(String name) throws ClassNotFoundException, SQLException {
        String sql = "select Category_Id from Category where  name = ?";
        ResultSet res = CrudUtil.execute(sql, name);
        if(res.next()){
            return  res.getString("Category_Id");
        }
        return null;
    }

    @Override
    public boolean save(Category dto) throws SQLException, ClassNotFoundException {
        String sql = "insert into Category values (?,?)";
        boolean isSaved = CrudUtil.execute(
                sql,
                dto.getCategoryId(),
                dto.getName()
        );

        return isSaved;
    }

    @Override
    public ArrayList<String> getAllCategoryIds() throws SQLException, ClassNotFoundException {
        String sql = "select Category_Id from Category";
        ResultSet res = CrudUtil.execute(sql);

        ArrayList<String> dto =new ArrayList<>();
        while(res.next()){
            String name = res.getString("Category_Id");
            dto.add(name);
        }
        return dto;
    }

    @Override
    public String getCateName(String categoryId) throws SQLException, ClassNotFoundException {
        String sql = "select * from category where Category_Id = ?";
        ResultSet res = CrudUtil.execute(sql,categoryId);
        if(res.next()){
            String name = res.getString("name");
            return name;
        }
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
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

    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Category dto) throws SQLException, ClassNotFoundException {
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

    @Override
    public Category search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
