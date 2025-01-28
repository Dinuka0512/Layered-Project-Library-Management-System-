package edu.ijse.gdse.libarymanagementsystem.controller;

import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.SupplierDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.SupplierTm;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.TempBookTM;
import edu.ijse.gdse.libarymanagementsystem.model.BookModel;
import edu.ijse.gdse.libarymanagementsystem.model.ManageSupplierModel;
import edu.ijse.gdse.libarymanagementsystem.model.SupplierModel;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageSuppliersView implements Initializable {


    @FXML
    private AnchorPane body;

    @FXML
    private ComboBox<String> comboBookId;

    @FXML
    private TableColumn<SupplierTm, String> columnAddress;

    @FXML
    private TableColumn<SupplierTm, String> columnContact;

    @FXML
    private TableColumn<SupplierTm, String> columnEmail;

    @FXML
    private TableColumn<SupplierTm, String> columnName;

    @FXML
    private TableColumn<SupplierTm, String> columnSupplierId;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblSupplierId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnReset;

    @FXML
    private TableColumn<TempBookTM, Button> columnTempAction;

    @FXML
    private TableColumn<TempBookTM, String> columnTempBookId;

    @FXML
    private TableColumn<TempBookTM, String> columnTempBookShelf;

    @FXML
    private TableColumn<TempBookTM, String> columnTempName;

    @FXML
    private TableColumn<TempBookTM, Double> columnTempPrice;

    @FXML
    private TableColumn<TempBookTM, Integer> columnTempQty;

    @FXML
    private TableView<TempBookTM> tempBookTable;

    @FXML
    private TableView<SupplierTm> tableSupplier;
    private ArrayList<TempBookTM> tempBookTMSArrayList = new ArrayList<>();
    private final SupplierModel supplierModel = new SupplierModel();
    private final BookModel bookModel = new BookModel();
    private final ManageSupplierModel manageSupplierModel = new ManageSupplierModel();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //HERE INITIALIZE THE TABLE COLUMNS
        columnSupplierId.setCellValueFactory(new PropertyValueFactory<>("suppierId"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        //TEMP BOOK TABLE INITIALIZE
        columnTempBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        columnTempName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTempQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        columnTempPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnTempBookShelf.setCellValueFactory(new PropertyValueFactory<>("bookShelfId"));
        columnTempAction.setCellValueFactory(new PropertyValueFactory<>("remove"));

        pageLoad();
    }

    private void pageLoad(){
        //LOAD THE TABLE
        loadTable();

        //LOAD THE COMBO BOX VALUES
        loadComboBoxValues();

        //GENARATE THE NEXT SUPPLIER IDS
        loardNextSupplierIds();
    }

    private void loadTable(){
        //HERE LOAD THE TABLE
        try{
            ArrayList<SupplierDto> dtos = supplierModel.getAllSuppliers();
            ObservableList<SupplierTm> supplierTms = FXCollections.observableArrayList();
            for (SupplierDto dto : dtos){
                SupplierTm supplierDto = new SupplierTm(
                        dto.getSupplierId(),
                        dto.getName(),
                        dto.getContact(),
                        dto.getAddress(),
                        dto.getEmail()
                );

                supplierTms.add(supplierDto);
            }
            //SET THE TABLE ITEMS
            tableSupplier.setItems(supplierTms);

        }catch (SQLException e1){
            System.out.println("SQLException");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("ClassNotFoundException");
            e2.printStackTrace();
        }
    }

    private void loadComboBoxValues(){
        try{
            ArrayList<String> bookIds = bookModel.getAllBookIds();
                if(bookIds != null){
                ObservableList<String> observableList = FXCollections.observableArrayList();
                observableList.addAll(bookIds);
                comboBookId.setItems(observableList);
                }

        }catch (SQLException e1){
            System.out.println("SQLException");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("ClassNotFoundException");
            e2.printStackTrace();
        }
    }

    @FXML
    void onClickCombo(ActionEvent event) {
        try {
            if (comboBookId.getValue() != null) {
                BookDto bookDetails = bookModel.getBookDetails(comboBookId.getValue());
                if(bookDetails != null){
                    lblBookName.setText(comboBookId.getValue() + " | " + bookDetails.getName());
                }else{
                    lblBookName.setText("BOOK NOT FOUND!");
                }
            }
        }catch (SQLException e1){
            System.out.println("SQLException");
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            System.out.println("ClassNotFoundException");
            e2.printStackTrace();
        }
    }

    private void loardNextSupplierIds(){
        //HERE LOAD THE NEXT SUPPLIER IDS
        try{
            String newId = supplierModel.loardNextSupplierId();
            lblSupplierId.setText(newId);
        }catch (ClassNotFoundException e1){
            System.out.println("Class Not Found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
    }


    private void pageRefresh(){
        //CLEAR THE ALL TEXT
        loardNextSupplierIds();

        txtName.setText("");
        txtName.setPromptText("name");

        txtEmail.setText("");
        txtEmail.setPromptText("email");

        txtContact.setText("");
        txtContact.setPromptText("contact");

        txtAddress.setText("");
        txtAddress.setPromptText("address");

        comboBookId.setValue("");
    }

    @FXML
    void resetthePage(ActionEvent event) {
        resetPage();
    }

    private void resetPage(){
        loadTable();
        pageRefresh();
        pageReforMat();
        clearTempTable();
    }

    private void clearTempTable(){
        tempBookTMSArrayList.clear();
        tempBookTable.setItems(null);
    }

    @FXML
    void getDataOnClick(MouseEvent event) {
        SupplierTm dto = tableSupplier.getSelectionModel().getSelectedItem();
        if(dto != null){
            lblSupplierId.setText(dto.getSuppierId());
            txtName.setText(dto.getName());
            txtAddress.setText(dto.getAddress());
            txtContact.setText(dto.getContact());
            txtEmail.setText(dto.getEmail());
            getSupplierBooks();
            changePageFormat();
        }
    }


    private void getSupplierBooks(){
        //WHEN ClICK THE SUPPLIER TABLE COLUMN THERE LORD THE SUPPLIER SUPPLYING BOOKS ON TO THE TEMP TABLE
        try{
            ArrayList<BookDto> dtos = manageSupplierModel.getAllBooks(lblSupplierId.getText());
            ObservableList<TempBookTM> observableList = FXCollections.observableArrayList();
            for(BookDto dto : dtos){
                Button btn = new Button("Remove");
                TempBookTM tempBookTM = new TempBookTM(
                        dto.getBookId(),
                        dto.getName(),
                        dto.getQty(),
                        dto.getPrice(),
                        dto.getBookShelfId(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    //HERE REMOVE THIS FORM ARRAY LIST
                    tempBookTMSArrayList.remove(tempBookTM);
                    tempBookTable.refresh();
                    //HERE LOAD THE TABLE AFTER CHANGES
                    loadTempTable();
                });

                tempBookTMSArrayList.add(tempBookTM);
                observableList.add(tempBookTM);
            }
            tempBookTable.setItems(observableList);

        }catch (ClassNotFoundException e1){
            System.out.println("Class Not Found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
    }

    private void loadTempTable(){
        ObservableList<TempBookTM> observableList = FXCollections.observableArrayList();
        for(TempBookTM tempBookTM : tempBookTMSArrayList){
            observableList.add(tempBookTM);
        }
        tempBookTable.setItems(observableList);
    }

    private void changePageFormat(){
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        btnReset.setDisable(false);
        btnSave.setDisable(true);
    }

    private void pageReforMat(){
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnReset.setDisable(true);
        btnSave.setDisable(false);
    }
    @FXML
    void gotoManageBook(ActionEvent event) {
        try{
            body.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ManageBookIssueView.fxml"));
            body.getChildren().add(load);
        }catch (IOException e1){
            System.out.println("IOException");
            e1.printStackTrace();
        }
    }

    @FXML
    void gotoHomepage(MouseEvent event) {
        try{
            body.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
            body.getChildren().add(load);
        }catch (IOException e1){
            System.out.println("IOException");
            e1.printStackTrace();
        }
    }

    @FXML
    void addBookToTempTable(ActionEvent event) {
        try {
            if (comboBookId.getValue() != null) {
                BookDto bookDetails = bookModel.getBookDetails(comboBookId.getValue());
                Button btn = new Button("Remove");
                TempBookTM bookTM = new TempBookTM(
                        bookDetails.getBookId(),
                        bookDetails.getName(),
                        bookDetails.getQty(),
                        bookDetails.getPrice(),
                        bookDetails.getBookShelfId(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    //HERE REMOVE THIS FORM ARRAY LIST
                    tempBookTMSArrayList.remove(bookTM);
                    tempBookTable.refresh();
                    //HERE LOAD THE TABLE AFTER CHANGES
                    loadTempTable();
                });

                //HERE CHECK IS ALL READY HAVE?
                for(TempBookTM temp :tempBookTMSArrayList){
                    if(temp.getBookId().equals(bookTM.getBookId())){
                        new Alert(Alert.AlertType.WARNING, "This Book is AllReady Have...!").show();
                        return;
                    }
                }

                tempBookTMSArrayList.add(bookTM);

                ObservableList<TempBookTM> observableList = FXCollections.observableArrayList();
                for(TempBookTM temp : tempBookTMSArrayList){
                    observableList.add(temp);
                }

                tempBookTable.setItems(observableList);
            }
        }catch (ClassNotFoundException e1){
            System.out.println("Class Not Found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
    }

    @FXML
    void saveSupplier(ActionEvent event) {
        if(isReadyToSaveSupplier(false)){
            if(tempBookTMSArrayList.isEmpty()){
                new Alert(Alert.AlertType.WARNING, "Please Add the Supplier Books...\nBefore save the supplier!").show();
            }else{
                save();
            }
        }
    }

    @FXML
    void UpdateSupplier(ActionEvent event) {
        if(isReadyToSaveSupplier(true)){
            if(tempBookTMSArrayList.isEmpty()){
                new Alert(Alert.AlertType.WARNING, "Supplier Cannot have Without Supply Books").show();
            }else{
                update();
            }
        }
    }

    @FXML
    void deleteSupplier(ActionEvent event) {
        try{
            //BOOK DELETE HERE
            Alert alert = new Alert(Alert.AlertType.WARNING, "Do you want to delete, Are you sure?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isDeleted = supplierModel.deleteSupplier(lblSupplierId.getText());
                if(isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION,"Deleted!..").show();
                    pageRefresh();
                    loadTable();
                    pageLoad();
                    clearTempTable();
                }else{
                    new Alert(Alert.AlertType.CONFIRMATION,"Supplier Deleting Problem").show();
                }
            }

        }catch (ClassNotFoundException e1){
            System.out.println("Class Not Found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
    }


    private boolean isReadyToSaveSupplier(boolean isUpdate){
        if(Validation.isValidName(txtName.getText())){
            //CHECK SUPPLIER NAME
            if(Validation.isValidName(txtAddress.getText())){
                //CHECK SUPPLIER CONTACT
                if(Validation.isValidEmail(txtEmail.getText())){
                    //CHECK SUPPLIER ADDRESS
                    if(isUpdate){
                        //------->>>>> UPDATING HERE
                        if(isEmailUniqueOrSame()){
                            //HERE CHECK THE EMAIL IS IT UNIQUE TO UPDATE
                        }else{
                            new Alert(Alert.AlertType.ERROR,"This Email is All ready Have...").show();
                            return false;
                        }
                    }else{
                        //------->>>>> SAVING HERE
                        if(isEmailIsUnique()){
                            //HERE CHECK THE EMAIL IS IT UNIQUE
                        }else{
                            new Alert(Alert.AlertType.ERROR,"This Email is All ready Have...").show();
                            return false;
                        }
                    }

                    if(Validation.isValidMobileNumber(txtContact.getText())){
                        //CHECK SUPPLIER EMAIL
                        //ALL ARE OK
                        return true;
                    }else{
                        new Alert(Alert.AlertType.WARNING, "Please Enter Valid Contact").show();
                        return false;
                    }

                }else{
                    new Alert(Alert.AlertType.WARNING,"Please Enter the Valid Email \nEmail canot be null").show();
                    return false;
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "Please check the City name, \nAddress Canot be Null").show();
                return false;
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Please Enter Valid Name \nNames Only can have letters \nEx - (A-z").show();
            return false;
        }
    }

    private boolean isEmailIsUnique(){
        try{
            boolean isEmailUnique = supplierModel.isEmailUnique(txtEmail.getText());
            if(isEmailUnique){
                //true - Unique
                return true;
            }
            //false - AllReady Have
            return false;
        }catch (ClassNotFoundException e1){
            System.out.println("Class Not found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("Sql Exception");
            e2.printStackTrace();
        }
        return false;
    }


    private boolean isEmailUniqueOrSame(){
        boolean isEmailReadyToUpdate = supplierModel.isEmailUniqueToUpdate(lblSupplierId.getText(), txtEmail.getText());
        if(isEmailReadyToUpdate){
            return true;
        }else{
            return false;
        }
    }

    private void save() {

        try{
            SupplierDto dto = new SupplierDto(
                    lblSupplierId.getText(),
                    txtName.getText(),
                    txtContact.getText(),
                    txtAddress.getText(),
                    txtEmail.getText()
            );

            boolean isSaved = manageSupplierModel.save(dto,tempBookTMSArrayList);
            if(isSaved){
                resetPage();
                loadTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Saved").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Supplier Saving failed \nSomething Went wrong...").show();
            }

        }catch (ClassNotFoundException e1){
            System.out.println("Class Not Found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
    }

    private void update(){
        try{
            SupplierDto dto = new SupplierDto(
                    lblSupplierId.getText(),
                    txtName.getText(),
                    txtContact.getText(),
                    txtAddress.getText(),
                    txtEmail.getText()
            );

            boolean isUpdate = manageSupplierModel.updateSupplier(dto, tempBookTMSArrayList);
            if(isUpdate){
                pageLoad();
                clearTempTable();
                resetPage();
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated Successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Supplier Updating Problem").show();
            }
        }catch (ClassNotFoundException e1){
            System.out.println("Class Not Found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
    }
}
