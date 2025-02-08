package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookShelfDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.Bookshelf;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookShelfDAOimpl implements BookShelfDAO {
    @Override
    public String getBookShelfLocation(String id) throws SQLException, ClassNotFoundException {
        String sql = "select location from bookShelf where bookshelf_Id = ?";
        ResultSet res = CrudUtil.execute(sql, id);
        if(res.next()){
            return res.getString("location");
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllBookShelfIds() throws SQLException, ClassNotFoundException {
        String sql = "select bookshelf_Id from bookShelf";
        ResultSet res = CrudUtil.execute(sql);

        ArrayList<String> dto = new ArrayList<>();
        while(res.next()){
            String id = res.getString("bookshelf_Id");
            dto.add(id);
        }

        return dto;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String sql = "select bookshelf_Id from BookShelf order by bookshelf_Id desc limit 1";
        ResultSet res = CrudUtil.execute(sql);
        if(res.next()){
            String lastId = res.getString("bookshelf_Id"); // S002
            String subString = lastId.substring(2); //002
            int i = Integer.parseInt(subString); // 2
            i = i + 1;
            String newId = String.format("BS%03d",i);
            return newId;
        }
        return "BS001";
    }

    @Override
    public boolean save(Bookshelf dto) throws SQLException, ClassNotFoundException {
        String sql = "insert into bookshelf values(?, ?, ?)";
        boolean isSaved = CrudUtil.execute(
                sql,
                dto.getBookshelfId(),
                dto.getLocation(),
                dto.getSectionId()
        );
        return isSaved;
    }

    @Override
    public ArrayList<Bookshelf> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Bookshelf dto) throws SQLException, ClassNotFoundException {
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
    public Bookshelf search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
