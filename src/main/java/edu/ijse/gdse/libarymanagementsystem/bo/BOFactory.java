package edu.ijse.gdse.libarymanagementsystem.bo;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.impl.*;

public class BOFactory {
    //HETE I JAVE USEING FACTORY DESIGN
    //meeken kaaranne object creation eka hide karana eka
    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        if(boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOType{
        BRANCH,USER,BOOK,MEMBER,BOOKSUPPLY,BOOKISSUE,ISSUE,RETURNBOOK,AUTHOR,RETURNINGBOOK,AUTHORBOOK,BOOKCATEGORY,CATEGORY, BOOKSHELF,SECTION,SUPPLIER
    }

    public SuperBO getBO(BOType type){
        switch (type){
            case BRANCH -> {
                return new BranchBOimpl();
            }
            case USER -> {
                return new UserBOimpl();
            }
            case BOOK -> {
                return new BookBOimpl();
            }
            case MEMBER -> {
                return new MemberBOimpl();
            }
            case BOOKSUPPLY -> {
                return new BookSupplyBOimpl();
            }
            case BOOKISSUE -> {
                return new BookIssueBOimpl();
            }
            case ISSUE -> {
                return new IssueTableBOimpl();
            }
            case RETURNBOOK -> {
                return new ReturnBookBOimpl();
            }
            case AUTHOR -> {
                return new AuthorBOimpl();
            }
            case RETURNINGBOOK -> {
                return new BookReturningBOimpl();
            }
            case AUTHORBOOK -> {
                return new AuthorBookBOimpl();
            }
            case BOOKCATEGORY -> {
                return new BookCategoryBOimpl();
            }
            case CATEGORY -> {
                return new CategoryBOimpl();
            }
            case BOOKSHELF -> {
                return new BookShelfBOimpl();
            }
            case SECTION -> {
                return new SectionBOimpl();
            }
            case SUPPLIER -> {
                return new SupplierBOimpl();
            }
            default -> {
                return null;
            }
        }
    }
}
