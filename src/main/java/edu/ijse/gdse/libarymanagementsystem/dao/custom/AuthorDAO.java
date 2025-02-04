package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.AuthorDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Author;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AuthorDAO extends CrudDAO<Author> {
    ArrayList<String> getAllAuthorIds() throws SQLException, ClassNotFoundException;
    String getAuthorName(String authorId) throws SQLException,ClassNotFoundException;
    boolean saveNewAuthor(AuthorDto dto) throws SQLException, ClassNotFoundException;
    String getAuthorIds(String name) throws  ClassNotFoundException, SQLException;
    boolean isEmailUnique(String email, String id) throws SQLException, ClassNotFoundException;
}
