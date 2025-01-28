package edu.ijse.gdse.libarymanagementsystem.util;

public class Validation {
    public static boolean isValidName(String name){
        return name.matches("^[A-Z a-z][a-zA-Z '.-]*[A-Za-z][^-]$");
    }

    public static boolean isValidEmail(String email){
        return email.matches(".+\\@.+\\..+");
    }

    public static boolean isValidPassword(String password){
        return password.matches("^[a-zA-Z0-9]{4,30}$");
    }

    public static boolean isValidMobileNumber(String mobilenNumber){
        return mobilenNumber.matches("^[0]{1}[7]{1}[01245678]{1}[0-9]{7}$");
    }

    public static boolean isValidInteger(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch (NumberFormatException e1){
            return false;
        }
    }

    public static boolean isValidDouble(String value){
        try{
            Double.parseDouble(value);
            return true;
        }catch (NumberFormatException e1){
            return false;
        }
    }
}
