package edu.ijse.gdse.libarymanagementsystem.controller;

import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.IssueTableDto;
import edu.ijse.gdse.libarymanagementsystem.dto.MemberDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.IssueTableTm;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.TempBookIssueTm;
import edu.ijse.gdse.libarymanagementsystem.model.*;
import edu.ijse.gdse.libarymanagementsystem.util.Gmail;
import javafx.application.Platform;
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

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class ManageBookIssueView implements Initializable {

    @FXML
    private AnchorPane body;

    @FXML
    private AnchorPane anchorBookIssue;

    @FXML
    private AnchorPane anchorSendingMail;

    @FXML
    private AnchorPane anchorIssueTable;

    @FXML
    private Button open;

    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane anchorBookReturning;

    @FXML
    private ComboBox<String> comboBookId;

    @FXML
    private ComboBox<String> comboMemberId;

    @FXML
    private TableColumn<TempBookIssueTm, String> columnBookId;

    @FXML
    private TableColumn<TempBookIssueTm, String> columnBookName;

    @FXML
    private TableColumn<TempBookIssueTm, String> columnIssueId;

    @FXML
    private TableColumn<TempBookIssueTm, Integer> columnQty;

    @FXML
    private TableColumn<TempBookIssueTm, Button> columnButton;

    @FXML
    private TableView<TempBookIssueTm> tableTempIssue;

    @FXML
    private Label lblBookIdAndName;

    @FXML
    private Label lblBookNameload;

    @FXML
    private Label lblBookQtyLoad;

    @FXML
    private Label lblBookqty;

    @FXML
    private Label lblIssueId;

    @FXML
    private Label lblMemName;

    @FXML
    private ProgressBar proBar;

    @FXML
    private Label lblSendingMessage;

    @FXML
    private Label lblMemberNameload;

    @FXML
    private Button btnBookIssuing;

    @FXML
    private Button btnBookReturning;

    private final MemberModel memberModel = new MemberModel();
    private final BookModel bookModel = new BookModel();
    private final IssueModel issueModel = new IssueModel();
    private final UserModel userModel = new UserModel();
    private final ManageBookIssueModel manageBookIssueModel = new ManageBookIssueModel();

    private BookDto bookDetail = null;
    private MemberDto memberDetails = null;
    private ArrayList<TempBookIssueTm> tempBookIssuesArrayList = new ArrayList<>();
    private final int bookQtyCanGet = 5;


    //ISSUE TABLE
    @FXML
    private TableColumn<IssueTableTm, String> columnIssueIssueId;

    @FXML
    private TableColumn<IssueTableTm, String> columnIssueMemEmail;

    @FXML
    private TableColumn<IssueTableTm, String> columnIssueMemId;

    @FXML
    private TableColumn<IssueTableTm, String> columnIssueDate;

    @FXML
    private TableColumn<IssueTableTm, String> columnIssueTime;

    @FXML
    private TableColumn<IssueTableTm, String> columnIssueUserId;

    @FXML
    private TableView<IssueTableTm> tableIssue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TEMP TABLE COLUMN INITIALIZING.....
        columnIssueId.setCellValueFactory(new PropertyValueFactory<>("issueId"));
        columnBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        columnBookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        columnButton.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));


        //ISSUE TABLE COLUMN INITIALIZING.....
        columnIssueIssueId.setCellValueFactory(new PropertyValueFactory<>("issueId"));
        columnIssueMemId.setCellValueFactory(new PropertyValueFactory<>("memId"));
        columnIssueMemEmail.setCellValueFactory(new PropertyValueFactory<>("memEmail"));
        columnIssueDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnIssueTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnIssueUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));

        pageReload();
    }

    private void pageReload(){
        loardMemberIds();
        ladBookIds();

        //LOAD NEXT IDS
        generateIssueId();

        //LOAD TABLE DATA
        loadIssueTable();

        lblSendingMessage.setVisible(false);
    }

    //LOAD ISSUE TABLE HERE
    private void loadIssueTable(){
        try{
            ArrayList<IssueTableDto> dtos = issueModel.getAllData();
            //CREATE THE TABLE MODEL TYPE ARRAY
            ArrayList<IssueTableTm> issueTableTms = new ArrayList<>();

            for(IssueTableDto dto: dtos){
                //GET EACH MEMBER ID
                String memberId = dto.getMemId();
                MemberDto newMemberDetails = memberModel.getMemberDetails(memberId);

                IssueTableTm issueTableTm = new IssueTableTm(
                        dto.getIssueId(),
                        dto.getMemId(),
                        newMemberDetails.getEmail(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getUserId()
                );

                issueTableTms.add(issueTableTm);
            }
            //ADDING VALUES TO OBSERVABLE ARRAY LIST
            ObservableList<IssueTableTm> observableList = FXCollections.observableArrayList();
            for(IssueTableTm dto: issueTableTms){
                IssueTableTm issueTableTm = new IssueTableTm(
                        dto.getIssueId(),
                        dto.getMemId(),
                        dto.getMemEmail(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getUserId()
                );

                observableList.add(issueTableTm);
            }

            tableIssue.setItems(observableList);

        }catch (ClassNotFoundException e1){
            System.out.println("ClassNotFoundException");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQLException");
            e2.printStackTrace();
        }
    }

    //GENERATE NEXT IDS
    private void generateIssueId(){
        try{
            String nextId = issueModel.getNextIssueId();
            lblIssueId.setText(nextId);
        }catch (SQLException e1){
            System.out.println("SQL EXCeption");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not Found Exception");
            e2.printStackTrace();
        }
    }

    // --------> LOAD COMBO BOX DATA
    private void ladBookIds(){
        //HERE LOAD THE BOOK IDS FOR COMBO BOX
        try{
            ArrayList<String> dto = bookModel.getAllBookIds();
            ObservableList<String> observableList= FXCollections.observableArrayList();
            for(String id : dto){
                observableList.add(id);
            }
            comboBookId.setItems(observableList);
        }catch (SQLException e1){
            System.out.println("SQL EXCeption");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not Found Exception");
            e2.printStackTrace();
        }
    }
    private void loardMemberIds(){
        //HERE LOAD THE MEMBER IDS FOR COMBO BOX
        try{
            ArrayList<String> dto = memberModel.getAllMemberIds();
            ObservableList<String> observableList= FXCollections.observableArrayList();
            for(String id : dto){
                observableList.add(id);
            }
            comboMemberId.setItems(observableList);
        }catch (SQLException e1){
            System.out.println("SQL EXCeption");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not Found Exception");
            e2.printStackTrace();
        }
    }


    //-------> LOAD THE COMBOBOX NAMES
    @FXML
    void comboBookNameLoad(ActionEvent event) {
        //HERE LOAD THE BOOK NAME TO LABEL
        try{
                String bookId = comboBookId.getValue();
                bookDetail = bookModel.getBookDetails(bookId);
            if(bookDetail != null){
                lblBookIdAndName.setText(bookId + " | " + bookDetail.getName());

                //HERE LOAD THE SAMPLE DATA
                lblBookNameload.setText(bookDetail.getName());
                lblBookQtyLoad.setText(Integer.toString(bookDetail.getQty()));
            }else{
                lblBookIdAndName.setText(" ");
                //HERE LOAD THE SAMPLE DATA
                lblBookNameload.setText(" ");
                lblBookQtyLoad.setText(" ");
            }
        }catch (SQLException e1){
            System.out.println("SQL EXCeption");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not Found Exception");
            e2.printStackTrace();
        }
    }

    @FXML
    void comboMemberNameLoad(ActionEvent event) {
        //HERE LOAD THE MEMBER NAME TO LABEL
        try{
            String memId = comboMemberId.getValue();
            memberDetails = memberModel.getMemberDetails(memId);
            if(memberDetails != null){
                lblMemName.setText(memId + " | " + memberDetails.getName());
                lblMemberNameload.setText("Mr/Miss. " + memberDetails.getName());
            }else{
                lblMemName.setText("");
                lblMemberNameload.setText("");

            }
        }catch (SQLException e1){
            System.out.println("SQL EXCeption");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("Class Not Found Exception");
            e2.printStackTrace();
        }
    }


    @FXML
    void goToHomePage(MouseEvent event) {
        //CAN GO TO HOME PAGE
        try{
            body.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
            body.getChildren().add(load);
        }catch (IOException e){
            System.out.println("IOException \nUnable to load home page");
            e.printStackTrace();
        }
    }

    @FXML
    void addNewBookToIssueTable(ActionEvent event) {
        if(comboBookId.getValue() != null){
            //HERE GET THE BOOK NAME

            for(TempBookIssueTm temp : tempBookIssuesArrayList){
                //HERE GOING TO CHECK IS THERE HAVE GET THE SAME BOOK?
                //IN THE ONE ISSUE ID CANNOT HAVE THE SAME TWO BOOKS

                if(temp.getBookId().equals(comboBookId.getValue())){
                    //IS THERE HAVE SAME NEED TO STOP THIS ISSUING
                    new Alert(Alert.AlertType.ERROR, "THERE ALL READY HAVE THIS BOOK!!").show();
                    return;
                }
            }

            if((tempBookIssuesArrayList.size() + 1) <= bookQtyCanGet){
                //CREATE THE BUTTON AND WE CAN ACCESS IT WITH btn REFERENCE
                Button btn = new Button("Remove");

                if(bookDetail != null){

                    if(bookDetail.getQty() == 0){
                        //OUT OF STOCK
                        new Alert(Alert.AlertType.ERROR,"SORRY!,\nThis "+ bookDetail.getName() + " book order is currently out of stock.").show();
                        return;
                    }

                    if(bookDetail.getQty() >= Integer.parseInt(lblBookqty.getText())){
                        /*
                        * IN THERE CHECK IS THE BOOK QTY IS
                        * GRATER THAN THE ISSUE QTY
                        * */

                        if(Integer.parseInt(lblBookqty.getText()) == 0){
                            //ORDER THE 0 BOOKS -----> SO WE DON'T NEED TO ADD IT ON CART
                            //BECAUSE THERE HAVE NOT ANY BOOK...
                            new Alert(Alert.AlertType.WARNING, "Please the quantity you have added 0 books...???").show();
                            return;
                        }

                        TempBookIssueTm tempIssue = new TempBookIssueTm(
                                lblIssueId.getText(),
                                comboBookId.getValue(),
                                bookDetail.getName(),
                                Integer.parseInt(lblBookqty.getText()),
                                btn
                        );

                        btn.setOnAction(actionEvent -> {
                            //HERE REMOVE THIS FORM ARRAY LIST
                            tempBookIssuesArrayList.remove(tempIssue);
                            tableTempIssue.refresh();
                            //HERE LOAD THE TABLE AFTER CHANGES
                            loardThetempIssueTable();
                        });

                        tempBookIssuesArrayList.add(tempIssue);
                        refresh();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Book Have Only " + bookDetail.getQty() + "! You can not get the " + lblBookqty.getText() + " Books..").show();
                    }

                }else{
                    new Alert(Alert.AlertType.WARNING,"The Cart Is Empty..").show();
                }

            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Sorry You have reached to the limit! \nThe Member Can Only get the " + bookQtyCanGet + " Books For at one Time...").show();
                return;
            }
        }else{
            //IS NULL
            new Alert(Alert.AlertType.WARNING,"Please select the book Id!!!").show();
        }
    }

    private void refresh(){
        loardThetempIssueTable();
        clearTextIntempArea();
    }

    private void loardThetempIssueTable(){
        ObservableList<TempBookIssueTm> observableList = FXCollections.observableArrayList();
        for(TempBookIssueTm temp : tempBookIssuesArrayList){
           TempBookIssueTm tempBookIssueTm = new TempBookIssueTm(
                   temp.getIssueId(),
                   temp.getBookId(),
                   temp.getName(),
                   temp.getQty(),
                   temp.getBtnRemove()
           );

           observableList.add(tempBookIssueTm);
        }

        tableTempIssue.setItems(observableList);
    }


    private void clearTextIntempArea(){
        comboBookId.setValue("");
        comboBookId.setPromptText("Select Book...");
    }

    @FXML
    void getTempTableValues(MouseEvent event) {
        TempBookIssueTm tempBookIssueTm = tableTempIssue.getSelectionModel().getSelectedItem();

        if(tempBookIssueTm != null){
            lblIssueId.setText(tempBookIssueTm.getIssueId());
            comboBookId.setValue(tempBookIssueTm.getBookId());
            lblBookqty.setText(Integer.toString(tempBookIssueTm.getQty()));
        }
    }

    @FXML
    void resetTable(ActionEvent event) {
        tempBookIssuesArrayList.clear();
        refresh();
    }

    @FXML
    void bookIssueNow(ActionEvent event) {
        //HERE ISSUE THE BOOK
        /*
         * TO CHECK THE ARRAY LIST IS ARRAY LIST IS EMPTY
         * WE USE THE .isEmpty() METHOD...
         * */
        lblSendingMessage.setVisible(true);

        if(!tempBookIssuesArrayList.isEmpty()){
            if(memberDetails != null){
                bookIssuingProcess();
            }else{
                new Alert(Alert.AlertType.WARNING,"Please Select the Member").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Please select the books first\nMUST ADD BOOK INTO THE CART").show();
        }
    }


    private void bookIssuingProcess(){
        String userId = userModel.getUserId(DashBoardContro.getUserEmail());
        //HERE GET THE DATE ....
        String date = String.valueOf(LocalDate.now());

        //HERE GET THE PRESENT TIME...
        //AND FORMAT IT TO MORE READABLE TYPE
        LocalTime time = LocalTime.now(); //----> EX - 10.14.20.9900975
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma"); // HERE FORMAT THAT
        String formattedTime = time.format(formatter).toLowerCase();



        //HERE CREATING THE ISSUE TABLE DTO
        IssueTableDto issueTableDto = new IssueTableDto(
                lblIssueId.getText(),
                memberDetails.getMemberId(),
                userId,
                date,
                formattedTime,
                false
        );

        try{
            boolean isIssueCompleted = manageBookIssueModel.issueNow(
                    issueTableDto,
                    tempBookIssuesArrayList
            );

            if(isIssueCompleted){
                String subject = "Confirmation of Book Issue: Gnanapradeepa Public Library";
                String body = "Dear " + memberDetails.getName() + ", " + "\n\nThis is to confirm that you have successfully issued a book from the" +
                        "Gnanapradeepa Public Library, Bandaragama.\n" + "Issue Date: " + date +" "+ formattedTime +  "\nDue Date: " + LocalDate.now().plusWeeks(2) +
                        "\n* Please ensure that the book is returned on due date (Two weeks) or before the due date to avoid any late fees. \n* Failure to return the book on time will result in fines as per the library’s policy." +
                        "\n\nThank you! \nWe hope you enjoy your reading." +
                        "\n\nSincerely,Gnanapradeepa Public Library \nBandaragama";
                pageReload();
                tempBookIssuesArrayList.clear();
                loardThetempIssueTable();
                Gmail.sendEmails("Dinuhi0512@gmail.com",memberDetails.getEmail(),subject,body);
                new Alert(Alert.AlertType.CONFIRMATION, "Book Issue Successfuly!").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Book Issuing problem\nSomething went wrong..!!").show();
            }

        }catch (ClassNotFoundException e1){
            System.out.println("Class Not found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }finally {
            // Hide the progress bar and message after the operation
            Platform.runLater(() -> {
                lblSendingMessage.setVisible(false);
            });
        }

    }


    @FXML
    void btnOpenBookIssueTable(ActionEvent event) {
        loadIssueTable(); //HERE CAN GET NEW ADDED VALUES
        anchorIssueTable.setVisible(true);
        btnBack.setVisible(true);
        open.setVisible(false);
    }


    @FXML
    void close(ActionEvent event) {
        anchorIssueTable.setVisible(false);
        btnBack.setVisible(false);
        open.setVisible(true);
    }


    @FXML
    void generateIssueReport(ActionEvent event) {
        try{
            //LOAD THE REPORT
            Connection con = DBConnection.getInstance().getConnection();
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Reports/AllBookIssues.jrxml"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,null,con
            );

            JasperViewer.viewReport(jasperPrint,false);
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
