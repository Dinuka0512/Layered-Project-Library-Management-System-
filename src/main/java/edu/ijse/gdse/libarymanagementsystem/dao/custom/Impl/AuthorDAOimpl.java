package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.AuthorDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.AuthorDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Author;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorDAOimpl implements AuthorDAO {
    @Override
    public boolean isEmailUnique(String email, String id) throws SQLException, ClassNotFoundException {
        String sql = "select email FROM Author WHERE Email = ? AND Author_Id != ?";
        ResultSet res = CrudUtil.execute(sql, email,id);
        if(res.next()){
            //EMAIL IS UNIQUE
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Author dto) throws SQLException, ClassNotFoundException {
        String sql = " update author set name = ?, Email = ?, address = ?, contact = ? where Author_Id = ?";
        boolean isUpdate = CrudUtil.execute(
                sql,
                dto.getName(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getContract(),
                dto.getAuhorId()
        );

        return isUpdate;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "delete from Author where Author_Id = ?";
        boolean isDelete = CrudUtil.execute(sql,id);
        return isDelete;
    }

    @Override
    public ArrayList<Author> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from Author";
        ResultSet res = CrudUtil.execute(sql);

        ArrayList<Author> author = new ArrayList<>();
        while(res.next()){
            Author dto = new Author(
                    res.getString("Author_Id"),
                    res.getString("name"),
                    res.getString("Email"),
                    res.getString("address"),
                    res.getString("Contact")
            );
            author.add(dto);
        }

        return author;
    }

    @Override
    public boolean saveNewAuthor(AuthorDto dto) throws SQLException, ClassNotFoundException {
        String sql = "insert into Author values (?,?,?,?,?)";
        boolean res = CrudUtil.execute(
                sql,
                dto.getAuhorId(),
                dto.getName(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getContract()
        );

        return res;
    }

    @Override
    public String getAuthorIds(String name) throws ClassNotFoundException, SQLException {
        String sql = "select Author_Id from Author where  name = ?";
        ResultSet res = CrudUtil.execute(sql, name);
        if(res.next()){
            return  res.getString("Author_Id");
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllAuthorIds() throws SQLException, ClassNotFoundException {
        String sql = "select Author_Id from Author";
        ResultSet res = CrudUtil.execute(sql);

        ArrayList<String> dto =new ArrayList<>();
        while(res.next()){
            String name = res.getString("Author_Id");
            dto.add(name);
        }
        return dto;
    }

    @Override
    public String getAuthorName(String authorId) throws SQLException, ClassNotFoundException {
        String sql = "select * from Author where Author_Id = ?";
        ResultSet res = CrudUtil.execute(sql,authorId);
        if(res.next()){
            String name = res.getString("name");
            return name;
        }
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String sql = "select Author_Id from Author Order by Author_Id desc limit 1";
        ResultSet res = CrudUtil.execute(sql);
        if(res.next()){
            String lastId = res.getString("Author_Id"); //A001
            String subString = lastId.substring(1);

            /*
             *  ------->   A001
             *  0 index -> A
             *  1 index -> 0
             *  2 index -> 0
             *  3 index -> 1
             *
             * -----> lastId.subString(1);
             *       (That means Starting at ist Index)
             *       So the output is
             *       001
             * */
            int i = Integer.parseInt(subString);
            /*
             * CONVERT THE STRING TO INTEGER
             * ==============================
             *
             * (String) 001 ----> Convert To Integer
             * 001 ---(AFTER CONVERT)--> 1
             * */
            i = i +1; // ADD
            String newId = String.format("A%03d",i);

            /*
             * "A%03d"
             *
             * --> A - Character
             * --> %d - formats the number as a decimal integer.
             * --> 0 - specifies that the integer should be padded
             * --> 3 -  if 'i' have fewer than 3 digits, it will be padded with leading zeros.
             * */
            return newId;
        }
        return "A001";
    }

    @Override
    public boolean save(Author dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Author search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
