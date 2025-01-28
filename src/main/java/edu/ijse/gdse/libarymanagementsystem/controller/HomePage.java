package edu.ijse.gdse.libarymanagementsystem.controller;

import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.Barchart;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.BookIdAndQty;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.Linechart;
import edu.ijse.gdse.libarymanagementsystem.dto.BookSupplyNameAndQtyDto;
import edu.ijse.gdse.libarymanagementsystem.dto.Member;
import edu.ijse.gdse.libarymanagementsystem.dto.UserDto;
import edu.ijse.gdse.libarymanagementsystem.model.*;
import edu.ijse.gdse.libarymanagementsystem.util.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomePage implements Initializable {


    @FXML
    private StackedBarChart<String, Number> barchartMember;

    private UserModel userModel = new UserModel();

    @FXML
    private TextField txtNewUserName;

    @FXML
    private AnchorPane workSpace;

    @FXML
    private TextField txtPasswordConfirm;

    @FXML
    private Label lblpopMemEmail1;

    @FXML
    private Label lblpopMemEmail2;

    @FXML
    private Label lblpopMemEmail3;

    @FXML
    private Label lblpopMemName1;

    @FXML
    private Label lblpopMemName2;

    @FXML
    private Label lblpopMemName3;


    @FXML
    private Button btnSave;

    @FXML
    private Label lblPrivacy;

    @FXML
    private Label lblWellcome;

    @FXML
    private Label currentUName;

    @FXML
    private TextField txtCurruntPassword;

    @FXML
    private AnchorPane anchorPrivacy;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private Button btnExit;

    private UserDto userDetails;

    @Setter
    private AnchorPane dashboardBody;

    @FXML
    private Label lblBook1;

    @FXML
    private Label lblBook2;

    @FXML
    private Label lblBook3;

    @FXML
    private Label lblBook4;

    @FXML
    private Label lblbookqty1;

    @FXML
    private Label lblbookqty2;

    @FXML
    private Label lblbookqty3;

    @FXML
    private Label lblbookqty4;

    @FXML
    private Label lblTodayBi;

    @FXML
    private Label lblTodaybR;

    @FXML
    private Label lblCashOnHandTd;
    private String userEmail;

    @FXML
    private Label lblSupplierName1;

    @FXML
    private Label lblSupplierName2;

    @FXML
    private Label lblSupplierName3;

    @FXML
    private Label lblSupplierSupbQty1;

    @FXML
    private Label lblSupplierSupbQty2;

    @FXML
    private Label lblSupplierSupbQty3;

    private static String userName;
    private final BookIssueModel bookIssueModel = new BookIssueModel();
    private final BookModel bookModel = new BookModel();
    private final MemberModel memberModel = new MemberModel();
    private final IssueModel issueModel = new IssueModel();
    private final ReturnBookModel returnBookModel = new ReturnBookModel();
    private final BookSupplyModel bookSupplyModel = new BookSupplyModel();

    private String date = String.valueOf(LocalDate.now());

    @FXML
    private LineChart<String, Number> lineBarChart;

    @FXML
    private LineChart<String, Number> barchartPayments;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDetails = DashBoardContro.getDto();

        //SET THE DATA
        userName = userDetails.getName();
        userEmail = userDetails.getEmail();

        lblWellcome.setText("Wellcome " + userName + ",");
        currentUName.setText(userName);

        setPopulerBooks();
        setPopularMembers();
        setPopularSupplier();

        setUpBarChart();
        todayBookIssues();
        todayBookReturns();
        setCashOnHand();
        loadPaymentBarchart();
    }

    private void setPopularSupplier(){
        ArrayList<BookSupplyNameAndQtyDto> dtos = bookSupplyModel.getSupplierNameAndAllBookSuppliedQty();
        if(dtos != null){
            int i = 1;
            for(BookSupplyNameAndQtyDto dto : dtos){
                if(i == 1){
                    lblSupplierName1.setText(dto.getSupplierName());
                    lblSupplierSupbQty1.setText(Integer.toString(dto.getQty()));
                }else if(i == 2){
                    lblSupplierName2.setText(dto.getSupplierName());
                    lblSupplierSupbQty2.setText(Integer.toString(dto.getQty()));
                } else if (i ==3) {
                    lblSupplierName3.setText(dto.getSupplierName());
                    lblSupplierSupbQty3.setText(Integer.toString(dto.getQty()));
                }
                i++;
            }
        }
    }

    private void loadPaymentBarchart(){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Return Amounts");

        ArrayList<Barchart> data = returnBookModel.getBarchartValues();

        for (Barchart item : data) {
            series.getData().add(new XYChart.Data<>(item.getDate(), item.getAmount()));
        }

        barchartPayments.getData().add(series);
    }

    private void setCashOnHand(){
        int cashonHandToday = returnBookModel.getCashonHandToday(date);
        lblCashOnHandTd.setText("Rs " + cashonHandToday + "/=");
    }

    private void todayBookIssues(){
        int bookIssueCount = issueModel.getTodaYIssueBookCounts(date);
        String bi = String.format("%02d", bookIssueCount);
        lblTodayBi.setText(bi);
    }

    private void todayBookReturns(){
        int bookReturnCount = returnBookModel.getTodayBookReturns(date);
        String br = String.format("%02d", bookReturnCount);
        lblTodaybR.setText(br);
    }

    private void setUpBarChart() {
        // Create data series
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Books Issued");

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Books Returned");

        ArrayList<Linechart> lineChartsIssue = issueModel.getDataToAddLineChart();
        ArrayList<Linechart> lineChartsReturn = returnBookModel.getDataToAddLineChart();

        //ISSUES
        for (Linechart linechart : lineChartsIssue) {
            series1.getData().add(new XYChart.Data<>(linechart.getDate(), linechart.getCount()));
        }

        //RETURNS
        for (Linechart linechart : lineChartsReturn) {
            series2.getData().add(new XYChart.Data<>(linechart.getDate(), linechart.getCount()));
        }

        // Add data series to the StackedBarChart
        lineBarChart.getData().addAll(series1, series2);
    }

    //EXIT BUTTON
    @FXML
    void closePrivacyWindow(ActionEvent event) {
        anchorPrivacy.setVisible(false);
    }

    @FXML
    void openPrivacyWindow(MouseEvent event) {
        // IS ADMIN IS LOGED -> setVisible(true) methord is not working adn give allert...
        if(DashBoardContro.isAdminLogin()){
            new Alert(Alert.AlertType.CONFIRMATION,"SORRY!\nAdmin user name or password cannot be change!...").show();
        }else{
            anchorPrivacy.setVisible(true);
        }
    }


    //SAVE BUTTON
    @FXML
    void saveNewDetails(ActionEvent event) throws SQLException, ClassNotFoundException{

//        boolean isValidName = txtNewUserName.getText().matches("^[A-Za-z ]+$");
        boolean isValidName = Validation.isValidName(txtNewUserName.getText());
        if(isValidName && !txtNewUserName.getText().equals(null)){
            //CHECK USERNAME IS VALID

            if(txtCurruntPassword.getText().equals(userDetails.getPassword())){
                //CHECK IS OLD PASSWORD IS SAME

//                boolean isValidPassword = txtNewPassword.getText().matches("^[a-zA-Z0-9]{4,30}$");
                boolean isValidPassword = Validation.isValidPassword(txtNewPassword.getText());
                if(isValidPassword && !txtNewPassword.getText().equals(null)){
                    //CHECK IS PASSWORD IS VALID

                    if(txtNewPassword.getText().equals(txtPasswordConfirm.getText())){
                        //CHECK IS THE BOTH ARE SAME OR NOT
                        //HERE NOW ALL ARE OK----------------
                        updateUser();
                    }else{
                        new Alert(Alert.AlertType.CONFIRMATION,"New Password and Current Password must be same!!..").show();
                    }
                }else{
                    new Alert(Alert.AlertType.CONFIRMATION,"PLEASE ENTER VALID PASSWORD \nPassword cannot be Null\n* You can add only letters & numbers(A-Z , a-z, 0-9) \n* Password must be 4 characters \n* password not shuld longerThan 30 characters...").show();
                }
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Wrong Password, \nYou need to enter current password..").show();
            }
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"CHECK YOUR NEW USER NAME \n* User name canot be null \n* User name can have only charecters (A-Z , a-z)").show();
        }
    }


    private void updateUser(){
        try{
            boolean isUpdated = userModel.updateUser(txtNewUserName.getText(),txtNewPassword.getText(),userDetails.getEmail());
            if(isUpdated){
                //saved
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully updated!").show();
                clearText();
                //when successfully updated we need to auto maticaly go to login page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
                Stage window = (Stage) txtCurruntPassword.getScene().getWindow();
                window.setScene(new Scene(loader.load()));

            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Failed").show();
            }

        }catch (ClassNotFoundException e1){
            System.out.println("Class Not found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("Sql Exception");
            e2.printStackTrace();
        }catch (IOException e3){
            System.out.println("IOExeption");
            e3.printStackTrace();
        }
    }
    //end hear-

    private void clearText(){
        txtCurruntPassword.setText("");
        txtNewPassword.setText("");
        txtPasswordConfirm.setText("");
        txtNewUserName.setText("");
    }



    @FXML
    void addNewUser(ActionEvent event) {
        try{
            Parent fxmlLorder = FXMLLoader.load(getClass().getResource("/view/CreateAccount.fxml"));
            Scene scene = new Scene(fxmlLorder);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Create New Account");
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();

        }catch (IOException e){
            System.out.println("faild to loard Create Account");
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

    private void setPopulerBooks(){
        //HERE SET THE POPULAR BOOKS
        try{
            int lblId = 1;
            ArrayList<BookIdAndQty> bookIds = bookIssueModel.getPopularBooks();
            for(BookIdAndQty arr : bookIds){
                BookDto dto = bookModel.getBookDetails(arr.getBook_Id());

                if (lblId == 1) {
                    lblBook1.setText(dto.getName());
                    lblbookqty1.setText(Integer.toString(arr.getQty()));
                } else if (lblId == 2) {
                    lblBook2.setText(dto.getName());
                    lblbookqty2.setText(Integer.toString(arr.getQty()));
                } else if (lblId == 3) {
                    lblBook3.setText(dto.getName());
                    lblbookqty3.setText(Integer.toString(arr.getQty()));
                } else if (lblId == 4) {
                    lblBook4.setText(dto.getName());
                    lblbookqty4.setText(Integer.toString(arr.getQty()));
                }
                lblId++;
            }
        }catch (ClassNotFoundException e1){
            System.out.println("class not Found");
            e1.printStackTrace();
        } catch (SQLException e2){
            System.out.println("Sql queree error");
            e2.printStackTrace();
        }
    }

    private void setPopularMembers(){
        try{
            int i = 1;
            ArrayList<Member> popMembers = memberModel.getPopularMember();
            for(Member member : popMembers){
                if(i==1){
                    lblpopMemName1.setText(member.getName());
                    lblpopMemEmail1.setText(Integer.toString(member.getTotalIssues()));

                } else if (i == 2) {
                    lblpopMemName2.setText(member.getName());
                    lblpopMemEmail2.setText(Integer.toString(member.getTotalIssues()));
                } else if (i == 3) {
                    lblpopMemName3.setText(member.getName());
                    lblpopMemEmail3.setText(Integer.toString(member.getTotalIssues()));
                }
                i++;
            }
        }catch (ClassNotFoundException e1){
            System.out.println("class not Found");
            e1.printStackTrace();
        } catch (SQLException e2){
            System.out.println("Sql queree error");
            e2.printStackTrace();
        }
    }
}
