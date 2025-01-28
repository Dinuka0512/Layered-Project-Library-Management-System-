package edu.ijse.gdse.libarymanagementsystem.bo;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.impl.BranchBOimpl;
import edu.ijse.gdse.libarymanagementsystem.bo.custom.impl.UserBOimpl;

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
        BRANCH,USER
    }

    public SuperBO getBO(BOType type){
        switch (type){
            case BRANCH -> {
                return new BranchBOimpl();
            }
            case USER -> {
                return new UserBOimpl();
            }
            default -> {
                return null;
            }
        }
    }
}
