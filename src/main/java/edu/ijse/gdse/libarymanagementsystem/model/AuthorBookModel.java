package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorBookModel {
    public String getAuthorId(String bookId) throws SQLException, ClassNotFoundException {
        String sql = "select Author_Id from Author_Book where Book_Id = ?";
        ResultSet res = CrudUtil.execute(sql,bookId);
        if(res.next()){
            return res.getString("Author_Id");
        }
        return null;
    }
}
