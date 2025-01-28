package edu.ijse.gdse.libarymanagementsystem.controller;

import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.MemberDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.MemberTm;
import edu.ijse.gdse.libarymanagementsystem.model.MemberModel;
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
import java.time.LocalDate;
import java.util.*;

public class ManageMemberViewContro implements Initializable {

    //GETTING REPORTS
    @FXML
    private Button btnGetMemActivityReport;
    @FXML
    private Button btnGetMemDetails;
    @FXML
    private Button btnGetAllReports;



    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRestart;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblMemId;

    @FXML
    private TextField txtMemAddress;

    @FXML
    private TextField txtMemContact;

    @FXML
    private TextField txtMemEmail;

    @FXML
    private TextField txtMemName;

    @FXML
    private TableColumn<MemberTm, String> memAddress;

    @FXML
    private TableColumn<MemberTm, String> memContact;

    @FXML
    private TableColumn<MemberTm, String> memEmail;

    @FXML
    private TableColumn<MemberTm, String> memId;

    @FXML
    private TableColumn<MemberTm, String> memName;

    @FXML
    private TableView<MemberTm> memberTable;

    @FXML
    private AnchorPane memberBody;

    private final MemberModel memberModel = new MemberModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //SET VALUES FOR THE LOAD TABLES
        memId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        memName.setCellValueFactory(new PropertyValueFactory<>("name"));
        memAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        memContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        memEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        reloadPage();
    }


    private void reloadPage(){
        genarateMemberID();
        loadTable();
        clearAllText();

        btnSave.setDisable(false);
        btnRestart.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnGetMemActivityReport.setDisable(true);
        btnGetMemDetails.setDisable(true);
    }


    private void loadTable(){
        try{
            ArrayList<MemberDto> memberDtos = memberModel.getAllDetails();
            ObservableList<MemberTm> memberTMS = FXCollections.observableArrayList();

            for(MemberDto dto : memberDtos){
                MemberTm member = new MemberTm(
                        dto.getMemberId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getEmail(),
                        dto.getContact()
                );

                memberTMS.add(member);
            }

            memberTable.setItems(memberTMS);

        }catch (SQLException e1){
            System.out.println("Sql Exception");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not found Exception");
            e2.printStackTrace();
        }
    }

    private void genarateMemberID(){
        try{
            String newId = memberModel.genarateMemberId();
            lblMemId.setText(newId);
        }catch (SQLException e1){
            System.out.println("SQLException");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("ClassNotFoundException");
            e2.printStackTrace();
        }
    }

    @FXML
    void save(ActionEvent event) {
        if(isReadyToSave()){
            memberSave();
        }
    }

    private boolean isReadyToSave(){
        try{
            if(Validation.isValidName(txtMemName.getText()) && !txtMemName.getText().equals(null)){
                //CHECK NAME
                if(Validation.isValidName(txtMemAddress.getText()) && !txtMemAddress.getText().equals(null)){
                    //CHECK ADDRESS
                    if(Validation.isValidEmail(txtMemEmail.getText()) && !txtMemEmail.getText().equals(null)){
                        //CHECK EMAIL
                        if(memberModel.isTheEmailAllreadyHave(txtMemEmail.getText())){
                            new Alert(Alert.AlertType.ERROR,"This Email is Allready Have").show();
                            return false;
                        }else {
                            if(Validation.isValidMobileNumber(txtMemContact.getText()) && !txtMemContact.getText().equals(null)){
                                //CHECK CONTACT NUMBER
                                //ALL ARE OK HEAR
                                return true;
                            }else{
                                new Alert(Alert.AlertType.WARNING,"PLEASE ENTER VALID CONTACT NUMBER").show();
                                return false;
                            }
                        }
                    }else{
                        new Alert(Alert.AlertType.WARNING,"PLEASE ENTER VALID EMAIL ADDRESS").show();
                        return false;
                    }
                }else{
                    new Alert(Alert.AlertType.WARNING,"CHECK ADDRESS \n* The city name only can have letters\n* Address can't be null").show();
                    return false;
                }
            }else{
                new Alert(Alert.AlertType.WARNING,"CHECK NAME\n* The name only can have letters\n* Name can't be null").show();
                return false;
            }
        }catch (SQLException e1){
            System.out.println("SQLException");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("ClassNotFoundException");
            e2.printStackTrace();
        }
        return false;
    }

    private void memberSave(){
        MemberDto memberDto = new MemberDto(
            lblMemId.getText(),
            txtMemName.getText(),
            txtMemAddress.getText(),
            txtMemEmail.getText(),
            txtMemContact.getText()
        );

        try{
            boolean isSaved = memberModel.saveMember(memberDto);
            if(isSaved){
                reloadPage();
                new Alert(Alert.AlertType.CONFIRMATION,"Member Saved SuccessFully..!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Member Saving failed!").show();
            }
        }catch (SQLException e1){
            System.out.println("SQL EXCEPTION");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class not found EXCEPTION");
            e2.printStackTrace();
        }
    }


    private void clearAllText(){
        txtMemName.setText("");
        txtMemAddress.setText("");
        txtMemContact.setText("");
        txtMemEmail.setText("");

        txtMemName.setPromptText("name");
        txtMemEmail.setPromptText("email");
        txtMemContact.setPromptText("contact");
        txtMemAddress.setPromptText("address");
    }


    @FXML
    void goToHomePage(MouseEvent event) {
        //GO Back To Home
        try{
            memberBody.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
            memberBody.getChildren().add(load);
        }catch (IOException e1){
            System.out.println("IoException \nUnable to loard Home page");
            e1.printStackTrace();
        }
    }


    @FXML
    void onClick(MouseEvent event) {
        //WHEN CLICK THE (GET TABLE VALUES)
        MemberTm memberTm = memberTable.getSelectionModel().getSelectedItem();
        if(memberTm != null){
            lblMemId.setText(memberTm.getMemberId());
            txtMemName.setText(memberTm.getName());
            txtMemEmail.setText(memberTm.getEmail());
            txtMemContact.setText(memberTm.getContact());
            txtMemAddress.setText(memberTm.getAddress());

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            btnRestart.setDisable(false);
            btnSave.setDisable(true);
            btnGetMemDetails.setDisable(false);
            btnGetMemActivityReport.setDisable(false);
        }
    }


    @FXML
    void reset(ActionEvent event) {
        reloadPage();
    }


    @FXML
    void updateMem(ActionEvent event) {
        try{
            if(isReadyToUpdate()){
                MemberDto dto = new MemberDto(
                        lblMemId.getText(),
                        txtMemName.getText(),
                        txtMemAddress.getText(),
                        txtMemEmail.getText(),
                        txtMemContact.getText()
                );

                boolean res = memberModel.updateMember(dto);

                if(res){
                    //IS UPDATE DONE
                    new Alert(Alert.AlertType.CONFIRMATION, "Update save Successfully...!").show();
                    reloadPage();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Update Faild... \nSomthing Went wrong..!").show();
                }
            }
        }catch (SQLException e1){
            System.out.println("SQL Exception");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not Found Exception");
            e2.printStackTrace();
        }
    }


    private boolean isReadyToUpdate(){
        try{
            if(Validation.isValidName(txtMemName.getText()) && !txtMemName.getText().equals(null)){
                //CHECK NAME
                if(Validation.isValidName(txtMemAddress.getText()) && !txtMemAddress.getText().equals(null)){
                    //CHECK ADDRESS
                    if(Validation.isValidEmail(txtMemEmail.getText()) && !txtMemEmail.getText().equals(null)){
                        //CHECK EMAIL
                        if(memberModel.isTheEmailAllreadyHave(txtMemEmail.getText()) && !memberModel.isThisEmail(lblMemId.getText(), txtMemEmail.getText())){
                            new Alert(Alert.AlertType.ERROR,"This Email is Allready Have").show();
                            return false;
                        }else {
                            if(Validation.isValidMobileNumber(txtMemContact.getText()) && !txtMemContact.getText().equals(null)){
                                //CHECK CONTACT NUMBER
                                //ALL ARE OK HEAR
                                return true;
                            }else{
                                new Alert(Alert.AlertType.WARNING,"PLEASE ENTER VALID CONTACT NUMBER").show();
                                return false;
                            }
                        }
                    }else{
                        new Alert(Alert.AlertType.WARNING,"PLEASE ENTER VALID EMAIL ADDRESS").show();
                        return false;
                    }
                }else{
                    new Alert(Alert.AlertType.WARNING,"CHECK ADDRESS \n* The city name only can have letters\n* Address can't be null").show();
                    return false;
                }
            }else{
                new Alert(Alert.AlertType.WARNING,"CHECK NAME\n* The name only can have letters\n* Name can't be null").show();
                return false;
            }
        }catch (SQLException e1){
            System.out.println("SQL Exception");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not Found Exception");
            e2.printStackTrace();
        }
        return false;
    }


    @FXML
    void deleteMem(ActionEvent event) {
        try{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Do you want to delete, Are you sure?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

                boolean isDeleted = memberModel.deleteMember(lblMemId.getText());
                if (isDeleted) {
                    reloadPage();
                    new Alert(Alert.AlertType.CONFIRMATION, "Member deleted...!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete member...!\nSomthing went wrong").show();
                }
            }
        }catch (SQLException e1){
            System.out.println("SQL Exception");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class not found Exception");
            e2.printStackTrace();
        }
    }




    //REPORTS GENERATING
    @FXML
    void getMemActivityReport(ActionEvent event) {
        //HERE WE CAN GET THE MEMBER BARROW BOOKS AND RETURNS
    }

    @FXML
    void getAllReports(ActionEvent event) {
        //HERE WE CAN GET ALL MEMBERS DETAILS
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/AllMembersDetailsReport.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

//            Map<String, Object> paramerers = new HashMap<>();
//            paramerers.put("P_Date", LocalDate.now());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
            System.out.println("JRException");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Exception");
            e.printStackTrace();
        }catch (ClassNotFoundException e1){
            System.out.println("Class not foound Exception");
            e1.printStackTrace();
        }
    }

    @FXML
    void getMemDetails(ActionEvent event) {
        //HERE WE CAN GET MEMBER WHAT WE HAVE SELECTED PERSON DETAILS TO REPORT
    }
}
