package edu.ijse.gdse.libarymanagementsystem.controller;

import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.UserDto;
import edu.ijse.gdse.libarymanagementsystem.util.BaseController;
import edu.ijse.gdse.libarymanagementsystem.model.BookIssueModel;
import edu.ijse.gdse.libarymanagementsystem.model.BookModel;
import edu.ijse.gdse.libarymanagementsystem.model.UserModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashBoardContro extends BaseController implements Initializable {
    @Getter
    public static UserDto dto;
    //CAN GET ALL USERS DETAILS



    //IS ADMIN LOGIN------
    @Getter
    private static boolean isAdminLogin;

    public static void setIsAdminLogin(boolean isAdminLogin) {
        DashBoardContro.isAdminLogin = isAdminLogin;
    }
    //-----



    private UserModel userModel = new UserModel();

    @Getter
    private static String userEmail;

    public static void setUserEmail(String userEmail) {
        DashBoardContro.userEmail = userEmail;
    }

    @FXML
    private AnchorPane btnNamesAnchor;

    @FXML
    private AnchorPane anchorAuthor;

    @FXML
    private AnchorPane anchorBook;

    @FXML
    private AnchorPane anchorBookIssue;

    @FXML
    private AnchorPane anchorBookReturn;

    @FXML
    private AnchorPane anchorBtnBody;

    @FXML
    private AnchorPane anchorDash;

    @FXML
    private AnchorPane anchorEmployee;

    @FXML
    private AnchorPane anchorMember;

    @FXML
    private AnchorPane anchorSupplier;
    @FXML
    private Label lblWellcome;

    @FXML
    public Label userGmail;

    @FXML
    private Label lblLogout;

    @FXML
    public AnchorPane dashboardBody;

    @FXML
    private AnchorPane workSpace;

    @FXML
    private AnchorPane body;


    //ADD USER --------
    @FXML
    private Button btnAddNewUser;

    @FXML
    void addNewUser(ActionEvent event) {
        try{
            Parent load = FXMLLoader.load(getClass().getResource("/view/CreateAccount.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.setTitle("Create Account");
            stage.show();
        }catch (IOException e1){
            System.out.println("Unable to open create Account page");
            e1.printStackTrace();
        }
    }
    //------



    //SIDE NAME BAR SHOWINGS -----------
    @FXML
    void openNavigationNames(MouseEvent event) {
        btnNamesAnchor.setVisible(true);
    }

    @FXML
    void closeNavigationNames(MouseEvent event) {
        btnNamesAnchor.setVisible(false);
    }
    //---------




//    BOOK MANAGE -------
    @FXML
    void showName(MouseEvent event) {
        btnNamesAnchor.setVisible(false);
        anchorBook.setVisible(true);
    }

    @FXML
    void closeName(MouseEvent event) {
        anchorBook.setVisible(false);
    }

    @FXML
    void openManageBookView(MouseEvent event) {
        try{
            body.getChildren().clear();
            AnchorPane loard = FXMLLoader.load(getClass().getResource("/view/ManageBooksView.fxml"));
            body.getChildren().add(loard);
        }catch (IOException e1){
            System.out.println("IoExeption");
            e1.printStackTrace();
        }
    }
    //----




    //MEMBER----->
    @FXML
    void closeNameMem(MouseEvent event) {
        anchorMember.setVisible(false);
    }

    @FXML
    void showNameMem(MouseEvent event) {
        btnNamesAnchor.setVisible(false);
        anchorMember.setVisible(true);
    }

    @FXML
    void OpenMamageMemberView(MouseEvent event) {
        try{
            body.getChildren().clear();
            AnchorPane loard = FXMLLoader.load(getClass().getResource("/view/ManageMembersView.fxml"));
            body.getChildren().add(loard);
        }catch (IOException e1){
            System.out.println("IoExeption");
            e1.printStackTrace();
        }
    }



    //AUTHORS---->
    @FXML
    void showNameAuth(MouseEvent event) {
        btnNamesAnchor.setVisible(false);
        anchorAuthor.setVisible(true);
    }


    @FXML
    void closeNameAuth(MouseEvent event) {
        anchorAuthor.setVisible(false);
    }

    @FXML
    void OpenMamageAuthorView(MouseEvent event) {
        try{
            body.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ManageAuthorView.fxml"));
            body.getChildren().add(load);
        }catch (IOException e1){
            System.out.println("IOException \nUnable to load Manage Author View");
            e1.printStackTrace();
        }
    }





//    BOOK ISSUES---->
    @FXML
    void showNameSec(MouseEvent event) {
        btnNamesAnchor.setVisible(false);
        anchorBookIssue.setVisible(true);
    }

    @FXML
    void closeNameSec(MouseEvent event) {
        anchorBookIssue.setVisible(false);
    }

    @FXML
    void openBookIssues(MouseEvent event) {
        try{
            //OPEN THE ISSUE BOOKS PAGE
            body.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ManageBookIssueView.fxml"));
            body.getChildren().add(load);

        }catch (IOException e1){
            System.out.println("IOException");
            e1.printStackTrace();
        }
    }



    //SUPPLIERS---->
    @FXML
    void showNameSup(MouseEvent event) {
        btnNamesAnchor.setVisible(false);
        anchorSupplier.setVisible(true);
    }


    @FXML
    void closeNameSup(MouseEvent event) {
        anchorSupplier.setVisible(false);
    }

    @FXML
    void openSupplierManage(MouseEvent event) {
        try{
            body.getChildren().clear();
            AnchorPane load =FXMLLoader.load(getClass().getResource("/view/manageSuppliersView.fxml"));
            body.getChildren().add(load);
        }catch (IOException e1){
            System.out.println("IOException");
            e1.printStackTrace();
        }
    }




    //DAMAGE ---->
    @FXML
    void showNameDama(MouseEvent event) {
        btnNamesAnchor.setVisible(false);
        anchorBookReturn.setVisible(true);
    }


    @FXML
    void closeNameDama(MouseEvent event) {
        anchorBookReturn.setVisible(false);
    }

    @FXML
    void openBookReturning(MouseEvent event) {
        try{
            body.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ManageBookReturning.fxml"));
            body .getChildren().add(load);
        }catch (IOException e1){
            System.out.println("Unable to load Book Returning page");
            e1.printStackTrace();
        }
    }


    //EMPLOYEE--->
    @FXML
    void showNameEmp(MouseEvent event) {
        btnNamesAnchor.setVisible(false);
        anchorEmployee.setVisible(true);
    }

    @FXML
    void closeNameEmp(MouseEvent event) {
        anchorEmployee.setVisible(false);
    }

    @FXML
    void gotomanageEmployeePage(MouseEvent event) {
        if(userEmail.equals("admin@gmail.com")){
            try{
                body.getChildren().clear();
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ManageEmployees.fxml"));
                body.getChildren().add(load);
            }catch (IOException e1){
                System.out.println("IoException");
                e1.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Only admin can Manage the Employees!!").show();
        }
    }


    //LOG OUT
    @FXML
    void loardLoginPage(MouseEvent event) {
        logout();
    }

    public void logout(){
        try{
            dashboardBody.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            dashboardBody.getChildren().add(load);
            Stage stage = (Stage) dashboardBody.getScene().getWindow();
            stage.setTitle("Log in");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize auto-logout for this scene
        Platform.runLater(() -> {
            Scene scene = dashboardBody.getScene();
            if (scene != null) {
                initAutoLogout(scene);
            } else {
                System.err.println("Scene is null, auto-logout not initialized.");
            }
        });



        getUserDetailsAndSetGmail();
        openHomePage();
    }



    public void getUserDetailsAndSetGmail(){
        try{
            dto = userModel.getUserDetails(userEmail);
            userGmail.setText(dto.getEmail());

            if(userEmail.equals("admin@gmail.com")){
                //IS THE ADMIN LOGIN
                btnAddNewUser.setVisible(true);
            }else{
                //IS THE USER LOGIN
                btnAddNewUser.setVisible(false);
            }

        }catch (ClassNotFoundException e1){
            System.out.println("class not Found");
            e1.printStackTrace();
        } catch (SQLException e2){
            System.out.println("Sql queree error");
            e2.printStackTrace();
        } catch (Exception e3){
            e3.printStackTrace();
        }
    }
    private void openHomePage(){
        try{
            body.getChildren().clear();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/HomePage.fxml"));
            AnchorPane load = fxmlLoader.load();
//
//            HomePage homepageContro = fxmlLoader.getController();
//            homepageContro.setUserDetails(userEmail);
            body.getChildren().add(load);
        }catch(Exception e){
            System.out.println("Unable to loard DashBoard on Dashboard conto");
            e.printStackTrace();
        }
    }

    @FXML
    void goToHome(MouseEvent event) {
        try {
            body.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
            body.getChildren().add(load);
        }catch (IOException e1){
            System.out.printf("IOException");
            e1.printStackTrace();
        }
    }

    @FXML
    void openDashAnchor(MouseEvent event) {
        btnNamesAnchor.setVisible(false);
        anchorDash.setVisible(true);
    }

    @FXML
    void closeDashAnchor(MouseEvent event) {
        anchorDash.setVisible(false);
    }
}