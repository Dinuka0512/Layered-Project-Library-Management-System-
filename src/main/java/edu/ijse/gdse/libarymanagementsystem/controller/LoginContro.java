package edu.ijse.gdse.libarymanagementsystem.controller;

import edu.ijse.gdse.libarymanagementsystem.dto.UserDto;
import edu.ijse.gdse.libarymanagementsystem.model.LogInModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginContro {

    //create Login model object
    private LogInModel logInModel = new LogInModel();

    @FXML
    private ImageView bgImageView;

    @FXML
    private Button btnCreateAccount;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField txtEmail;


    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label labelError;

    @FXML
    private AnchorPane body;

    public static UserDto dto;

    @FXML
    void UserLogIn(ActionEvent event)throws ClassNotFoundException, SQLException , IOException{
        dto = logInModel.checkEmail(txtEmail.getText());
        if(dto != null){
            //HAVE THE USER
            if(dto.getEmail().equals(txtEmail.getText())){
                //EMAIL IS CORRECT
                if(dto.getPassword().equals(txtPassword.getText())){
                    //EMAIL AND PASSWORD CORRECT HERE
                    DashBoardContro.setIsAdminLogin(false);
                    DashBoardContro.setUserEmail(dto.getEmail());

                    //CLEAR THE TEXTS
                    clearText();

                    //HERE OPEN DASHBOARD
                    openDashboard();

                }else{
                    labelError.setText("Invalid Password, Please Try again...!");
                }
            }else{
                labelError.setText("Something Went Wrong");
            }
        }else{
            labelError.setText("Try Again, Invalid Email!");
        }
    }

    private void clearText(){
        txtEmail.setText("");
        txtPassword.setText("");
    }

    private void openDashboard() throws IOException {
        try{
            body.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
            body.getChildren().add(load);
            Stage stage = (Stage) body.getScene().getWindow();
            stage.setTitle("Gnanapradeepa Library");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
