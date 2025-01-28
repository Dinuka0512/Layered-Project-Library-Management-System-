package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCategoryModel {
    public String getCategoryId(String bookId) throws  ClassNotFoundException, SQLException {
        String sql = "select Category_Id from Book_Category where Book_Id = ?";
        ResultSet res = CrudUtil.execute(sql,bookId);
        if(res.next()){
            return res.getString("Category_Id");
        }
        return null;
    }
}
