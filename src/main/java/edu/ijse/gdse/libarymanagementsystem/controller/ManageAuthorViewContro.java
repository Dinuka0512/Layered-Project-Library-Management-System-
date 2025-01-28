package edu.ijse.gdse.libarymanagementsystem.controller;

import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.AuthorDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.AuthorTm;
import edu.ijse.gdse.libarymanagementsystem.model.AuthorModel;
import edu.ijse.gdse.libarymanagementsystem.util.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.jar.JarException;

public class ManageAuthorViewContro implements Initializable {

    @FXML
    private AnchorPane body;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private Label lblAuthorId;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnGetAllAuthorDetailsRepo;

    @FXML
    private Button btnGetAuthorBookDetailRepo;

    @FXML
    private Button btnGetAuthorRepo;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;
    @FXML
    private TableColumn<AuthorTm, String> columnAuthorAddress;

    @FXML
    private TableColumn<AuthorTm, String> columnAuthorContact;

    @FXML
    private TableColumn<AuthorTm, String> columnAuthorEmail;

    @FXML
    private TableColumn<AuthorTm, String> columnAuthorId;

    @FXML
    private TableColumn<AuthorTm, String> columnAuthorName;

    @FXML
    private TableView<AuthorTm> tableAuthor;

    private final AuthorModel authorModel = new AuthorModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnAuthorId.setCellValueFactory(new PropertyValueFactory<>("auhorId"));
        columnAuthorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAuthorEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        columnAuthorAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        columnAuthorContact.setCellValueFactory(new PropertyValueFactory<>("contract"));

        pageReset();
    }

    private void pageReset(){
        loardTable();

        //LOAD AUTHOR ID HERE
        loardNextAuthorId();

        //HERE CLEAR ALL TEXT
        clearTexts();

        //HERE RESET THE ALL BUTTONS
        buttonReset();
    }

    private void buttonReset(){
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnReset.setDisable(true);

        btnGetAuthorRepo.setDisable(true);
        btnGetAuthorBookDetailRepo.setDisable(true);
    }

    private void loardTable(){
        try{
            ArrayList<AuthorDto> dto = authorModel.getAllAuthorDetails();
            ObservableList<AuthorTm> observableList = FXCollections.observableArrayList();

            /*INTO THE JAVAFX TABLE WE CAN ONLY ADD THE
             * OBSERVABLE ARRAY LISTS ... SO NOW WE NEED TO CONVERT THIS TABLE MODEL
             * TO THE OBSERVABLE ARRAYLIST
             * AuthorDto ----(convert)---> AuthorTm ------(Need to add)-----> Observable ArrayList
             * */

            for(AuthorDto authorDto : dto){
                AuthorTm authorTm = new AuthorTm(
                        authorDto.getAuhorId(),
                        authorDto.getName(),
                        authorDto.getEmail(),
                        authorDto.getAddress(),
                        authorDto.getContract()
                );

                observableList.add(authorTm);
            }

            tableAuthor.setItems(observableList);

        }catch (ClassNotFoundException e1){
            System.out.println("Class not found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
    }


    private void loardNextAuthorId(){
        try{
            //HERE WE GET THE NEXT AUTHOR ID AND SET IT TO THE LABEL AUTHOR ID
            String authorId = authorModel.genarateAuthorId();
            lblAuthorId.setText(authorId);

        }catch (ClassNotFoundException e1){
            System.out.println("Class Not found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
    }


    @FXML
    void saveAuthor(ActionEvent event) {
        //HERE SAVE THE AUTHOR
        if(isEnterTheCorrectDetails() && isvalidEmail()){
            AuthorDto dto = new AuthorDto(
                    lblAuthorId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    txtContact.getText()
            );

            try{
                boolean isSaved = authorModel.saveNewAuthor(dto);
                if(isSaved){
                    pageReset();
                    clearTexts();
                    new Alert(Alert.AlertType.CONFIRMATION,"Saved Succsessfully!!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Save failed, Something Went Wrong").show();
                }
            }catch (SQLException e1){
                System.out.println("SQL Exception");
                e1.printStackTrace();
            }catch (ClassNotFoundException e2){
                System.out.println("Class Not Found Exception");
                e2.printStackTrace();
            }
        }
    }

    private boolean isEnterTheCorrectDetails(){
        if(Validation.isValidName(txtName.getText()) && !txtName.getText().equals("")){
            if(Validation.isValidEmail(txtEmail.getText()) && !txtEmail.getText().equals("")){
                //IN THERE WE CHECK THE IS EMAIL IS OK OR NOT
                //IS EMAIL IS OK WE NEED TO CHECK IS THE EMAIL
                // IS ALREADY HAVE OR NOT?....
                if(Validation.isValidName(txtAddress.getText()) && !txtAddress.getText().equals("")){
                    if(Validation.isValidMobileNumber(txtContact.getText()) && !txtContact.getText().equals("")){
                        return true;
                    }else{
                        new Alert(Alert.AlertType.WARNING,"PLEASE ENTER THE VALID CONTCT NUMBER").show();
                        return false;
                    }
                }else {
                    new Alert(Alert.AlertType.WARNING, "PLEASE CHECK THE ADDRESS \n* The address cannot be null").show();
                    return false;
                }
            }else{
                new Alert(Alert.AlertType.WARNING, "PLEASE ENTER THE VALID EMAIL").show();
                return false;
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "PLEASE ENTER THE VALID NAME \n* The name cannot have the numbers\n* The names cannot be null!!").show();
            return false;
        }
    }

    private boolean isvalidEmail(){
        if(authorModel.isEmailUnique(txtEmail.getText(),lblAuthorId.getText())){
            return true;
        }else{
            new Alert(Alert.AlertType.ERROR,"THIS EMAIL IS ALREADY HAVE \nSomething went wrong...").show();
            return false;
        }
    }

    private boolean isvalidEmailForUpdate(){
        if(authorModel.isEmailUnique(txtEmail.getText(), lblAuthorId.getText())){
            //OWN EMAIL---> false
            return true;
        }else{
            new Alert(Alert.AlertType.ERROR,"THIS EMAIL IS ALREADY HAVE \nSomething went wrong...").show();
            return false;
        }
    }

    private void clearTexts(){
        txtName.setText("");
        txtName.setPromptText("name");

        txtContact.setText("");
        txtContact.setPromptText("contact");

        txtAddress.setText("");
        txtAddress.setPromptText("address");

        txtEmail.setText("");
        txtEmail.setPromptText("email");
    }



    @FXML
    void getTableData(MouseEvent event) {
        //HERE GET THE TABLE DATA
        AuthorTm dto = tableAuthor.getSelectionModel().getSelectedItem();

        if(dto != null){
            lblAuthorId.setText(dto.getAuhorId());
            txtName.setText(dto.getName());
            txtEmail.setText(dto.getEmail());
            txtAddress.setText(dto.getAddress());
            txtContact.setText(dto.getContract());

            buttonFormat();
        }
    }

    private void buttonFormat(){
        btnSave.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        btnReset.setDisable(false);

        btnGetAuthorRepo.setDisable(false);
        btnGetAuthorBookDetailRepo.setDisable(false);
    }

    @FXML
    void resetButtonOnAction(ActionEvent event) {
        //HERE RESET BUTTON ACTION
        pageReset();
    }

    @FXML
    void UpdaeButtonOnAction(ActionEvent event) {
        //HERE UPDATE BUTTON ON ACTION
        try{
            if(isEnterTheCorrectDetails() && isvalidEmailForUpdate()){
                AuthorDto dto = new AuthorDto(
                        lblAuthorId.getText(),
                        txtName.getText(),
                        txtEmail.getText(),
                        txtAddress.getText(),
                        txtContact.getText()
                );

                boolean isUpdate = authorModel.updateAuthor(dto);
                if(isUpdate){
                    pageReset();
                    new Alert(Alert.AlertType.CONFIRMATION,"Author Updated!!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Update Failed,\nSomething Went Wrong..!!!").show();
                }
            }
        }catch (ClassNotFoundException e1){
            System.out.println("Class Not found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
    }

    @FXML
    void deleteButtonAction(ActionEvent event) {
        try{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Do you want to delete, Are you sure?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isDelete = authorModel.deleteAuthor(lblAuthorId.getText());
                if(isDelete){
                    new Alert(Alert.AlertType.CONFIRMATION, "Delete Author").show();
                    pageReset();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Delete Failed\n Something Went wrong !!").show();
                }
            }

        }catch (SQLException e1){
            System.out.println("SQLException");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not Found Exception");
            e2.printStackTrace();
        }
    }

    @FXML
    void goToHomePage(MouseEvent event) {
        try{
            body.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
            body.getChildren().add(load);
        }catch (IOException e1){
            System.out.println("IOException\nUnable to load the Home page");
            e1.printStackTrace();
        }
    }

    @FXML
    void getAll(MouseEvent event) {
        //HERE GENERATE THE ALL MEMBER DETAILS REPORT
        try{
            //HERE GET CONNECTION
            Connection con = DBConnection.getInstance().getConnection();

            //LOAD THE JASPER REPORT
            /*
            * AT IN THERE THE JASPER REPORT COMPILER
            * ----> CONVERT THE JRXML FILES TO JASPER REPORTS
            *
            *    JASPER COMPILER IS ==
            *    JRXML ------(CONVERT TO)-----> JASPER REPORTS
            * */
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Reports/AllAuthorDetailsReport.jrxml"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,null,con
            );

            JasperViewer.viewReport(jasperPrint);
        }catch (SQLException e1){
            System.out.println("SQL Exception");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not Found Exception ");
            e2.printStackTrace();
        }catch (JRException e3){
            new Alert(Alert.AlertType.ERROR,"Failed to load report").show();
            System.out.println("JRException");
            e3.printStackTrace();
        }
    }
}
