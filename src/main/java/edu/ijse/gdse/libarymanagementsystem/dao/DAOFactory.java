package edu.ijse.gdse.libarymanagementsystem.dao;

import edu.ijse.gdse.libarymanagementsystem.bo.BOFactory;
import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.bo.custom.impl.BookBOimpl;
import edu.ijse.gdse.libarymanagementsystem.bo.custom.impl.BranchBOimpl;
import edu.ijse.gdse.libarymanagementsystem.bo.custom.impl.UserBOimpl;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOType{
        BRANCH,USER,BOOK,MEMBER,BOOKSUPPLY,BOOKISSUE,ISSUE,RETURNBOOK,AUTHOR,BOOKRETURNIG,AUTHORBOOK,BOOKCATEGORY,CATEGORY,BOOKSHELF
    }

    public SuperDAO getDAO(DAOFactory.DAOType type){
        switch (type){
            case BRANCH -> {
                return new BranchDAOImpl();
            }
            case USER -> {
                return new UserDAOImpl();
            }
            case BOOK -> {
                return new BookDAOimpl();
            }
            case MEMBER -> {
                return new MemberDAOimpl();
            }
            case BOOKSUPPLY -> {
                return new BookSupplyDAOimpl();
            }
            case BOOKISSUE -> {
                return new BookIssueDAOimpl();
            }
            case ISSUE -> {
                return new IssueTableDAOimpl();
            }
            case RETURNBOOK -> {
                return new ReturnBookDAOimpl();
            }
            case AUTHOR -> {
                return new AuthorDAOimpl();
            }
            case BOOKRETURNIG -> {
                return new BookReturningDAOimpl();
            }
            case AUTHORBOOK -> {
                return new AuthorBookDAOimpl();
            }
            case BOOKCATEGORY -> {
                return new BookCategoryDAOimpl();
            }
            case CATEGORY -> {
                return new CategoryDAOimpl();
            }
            case BOOKSHELF -> {
                return new BookShelfDAOimpl();
            }
            default -> {
                return null;
            }
        }
    }
}
