package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.bo.custom.UserBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.UserDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.UserDto;
import edu.ijse.gdse.libarymanagementsystem.entity.User;

import java.sql.SQLException;

public class UserBOimpl implements UserBO {
    //CREATED INSTANCE
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean saveUser(UserDto dto, String branchID) throws ClassNotFoundException, SQLException {
        return userDAO.save(new User(dto.getUserId(),dto.getName(),dto.getAddress(),dto.getPassword(),dto.getEmail(),branchID));
    }

    @Override
    public String genarateId() throws SQLException, ClassNotFoundException {
        return userDAO.generateNewId();
    }

    @Override
    public boolean isUniqueEmail(String email) throws SQLException, ClassNotFoundException {
        return userDAO.isUniqueEmail(email);
    }
}
