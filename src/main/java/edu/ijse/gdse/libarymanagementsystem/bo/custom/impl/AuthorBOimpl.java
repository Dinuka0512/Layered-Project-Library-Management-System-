package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.AuthorBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.AuthorDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.AuthorDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Author;

import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorBOimpl implements AuthorBO {

    private AuthorDAO authorDAO = (AuthorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.AUTHOR);
    @Override
    public String genarateAuthorId() throws SQLException, ClassNotFoundException {
        return authorDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllAuthorIds() throws SQLException, ClassNotFoundException {
        return authorDAO.getAllAuthorIds();
    }

    @Override
    public String getAuthorName(String authorId) throws SQLException, ClassNotFoundException {
        return authorDAO.getAuthorName(authorId);
    }

    @Override
    public boolean saveNewAuthor(AuthorDto dto) throws SQLException, ClassNotFoundException {
        return authorDAO.saveNewAuthor(dto);
    }

    @Override
    public String getAuthorIds(String name) throws ClassNotFoundException, SQLException {
        return authorDAO.getAuthorIds(name);
    }

    @Override
    public ArrayList<AuthorDto> getAllAuthorDetails() throws ClassNotFoundException, SQLException {
        ArrayList<Author> authors = authorDAO.getAll();
        ArrayList<AuthorDto> authorDtos = new ArrayList<>();
        for(Author author : authors){
            AuthorDto authorDto = new AuthorDto(
                    author.getAuhorId(),
                    author.getName(),
                    author.getEmail(),
                    author.getAddress(),
                    author.getContract()
            );

            authorDtos.add(authorDto);
        }

        return authorDtos;
    }

    @Override
    public boolean updateAuthor(AuthorDto dto) throws ClassNotFoundException, SQLException {
        return authorDAO.update(new Author(dto.getAuhorId(), dto.getName(), dto.getEmail(), dto.getAddress(), dto.getContract()));
    }

    @Override
    public boolean deleteAuthor(String authorId) throws SQLException, ClassNotFoundException {
        return authorDAO.delete(authorId);
    }

    @Override
    public boolean isEmailUnique(String email, String id) throws SQLException, ClassNotFoundException {
        return authorDAO.isEmailUnique(email,id);
    }
}
