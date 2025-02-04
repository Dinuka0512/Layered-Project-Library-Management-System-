package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.AuthorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AuthorBO extends SuperBO {
    String genarateAuthorId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllAuthorIds() throws SQLException, ClassNotFoundException;
    String getAuthorName(String authorId) throws SQLException,ClassNotFoundException;
    boolean saveNewAuthor(AuthorDto dto) throws SQLException, ClassNotFoundException;
    String getAuthorIds(String name) throws  ClassNotFoundException, SQLException;
    ArrayList<AuthorDto> getAllAuthorDetails() throws ClassNotFoundException, SQLException;
    boolean updateAuthor(AuthorDto dto) throws ClassNotFoundException, SQLException;
    boolean deleteAuthor(String authorId) throws SQLException, ClassNotFoundException;
    boolean isEmailUnique(String email, String id) throws SQLException, ClassNotFoundException;
}
