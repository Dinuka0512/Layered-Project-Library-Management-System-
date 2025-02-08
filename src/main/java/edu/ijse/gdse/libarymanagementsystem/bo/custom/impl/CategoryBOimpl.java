package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.CategoryBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.CategortDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.CategoryDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryBOimpl implements CategoryBO {
    private CategortDAO categortDAO = (CategortDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CATEGORY);

    @Override
    public String generateCategoryID() throws ClassNotFoundException, SQLException {
        return categortDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllCategoryIds() throws SQLException, ClassNotFoundException {
        return categortDAO.getAllCategoryIds();
    }

    @Override
    public String getCateName(String categoryId) throws SQLException, ClassNotFoundException {
        return categortDAO.getCateName(categoryId);
    }

    @Override
    public boolean saveNewCategory(CategoryDto categoryDto) throws ClassNotFoundException, SQLException {
        return categortDAO.save(new Category(categoryDto.getCategoryId(),categoryDto.getName()));
    }

    @Override
    public String getCategoryId(String name) throws ClassNotFoundException, SQLException {
        return categortDAO.getCategoryId(name);
    }
}
