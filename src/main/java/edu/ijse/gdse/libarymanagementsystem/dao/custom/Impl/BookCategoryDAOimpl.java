package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.BookCategoryBO;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCategoryDAOimpl implements BookCategoryDAO {
    @Override
    public String getCategoryId(String bookId) throws ClassNotFoundException, SQLException {
        String sql = "select Category_Id from Book_Category where Book_Id = ?";
        ResultSet res = CrudUtil.execute(sql,bookId);
        if(res.next()){
            return res.getString("Category_Id");
        }
        return null;
    }
}
