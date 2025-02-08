package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.AuthorBookDAO;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorBookDAOimpl implements AuthorBookDAO {
    @Override
    public String getAuthorId(String bookId) throws SQLException, ClassNotFoundException {
        String sql = "select Author_Id from Author_Book where Book_Id = ?";
        ResultSet res = CrudUtil.execute(sql,bookId);
        if(res.next()){
            return res.getString("Author_Id");
        }
        return null;
    }
}
