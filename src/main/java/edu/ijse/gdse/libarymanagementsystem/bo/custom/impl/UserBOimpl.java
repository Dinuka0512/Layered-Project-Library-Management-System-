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
    public UserDto getUserDetails(String userEmail) throws SQLException, ClassNotFoundException {
        return userDAO.getUserDetails(userEmail);
    }

    @Override
    public boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(dto.getUserId(),dto.getName(),dto.getAddress(),dto.getPassword(),dto.getEmail(),""));
    }

    @Override
    public String getUserId(String email){
        return userDAO.getUserId(email);
    }

    @Override
    public UserDto checkEmail(String email) throws SQLException, ClassNotFoundException {
        User user = userDAO.checkEmail(email);
        return (user != null)?  new UserDto(user.getUserId(),user.getName(),user.getAddress(),user.getPassword(),user.getEmail()) : null;
    }

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
