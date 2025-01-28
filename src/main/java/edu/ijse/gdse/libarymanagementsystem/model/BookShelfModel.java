package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.dto.BookshelfDto;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookShelfModel {
    public ArrayList<String> getAllBookShelfIds(){
        try {
            String sql = "select bookshelf_Id from bookShelf";
            ResultSet res = CrudUtil.execute(sql);

            ArrayList<String> dto = new ArrayList<>();
            while(res.next()){
                String id = res.getString("bookshelf_Id");
                dto.add(id);
            }

            return dto;

        }catch (ClassNotFoundException e1){
            System.out.println("Class not Found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQLException");
            e2.printStackTrace();
        }
        return null;
    }

    public String getBookShelfLocation(String id) throws SQLException, ClassNotFoundException{
        String sql = "select location from bookShelf where bookshelf_Id = ?";
        ResultSet res = CrudUtil.execute(sql, id);
        if(res.next()){
            return res.getString("location");
        }
        return null;
    }

    public String generateNextId() throws SQLException, ClassNotFoundException{
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

    public boolean saveBokShelf(BookshelfDto dto) throws SQLException, ClassNotFoundException{
        String sql = "insert into bookshelf values(?, ?, ?)";
        boolean isSaved = CrudUtil.execute(
                sql,
                dto.getBookshelfId(),
                dto.getLocation(),
                dto.getSectionId()
        );
        return isSaved;
    }
}
