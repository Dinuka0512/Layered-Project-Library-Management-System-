package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.SupplierBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.SupplierDAO;
import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.SupplierDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.TempBookTM;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;
import edu.ijse.gdse.libarymanagementsystem.entity.Supplier;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOimpl implements SupplierBO {
    private SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);

    @Override
    public boolean isEmailUniqueToUpdate(String supplierId, String email) throws SQLException, ClassNotFoundException {
        return supplierDAO.isEmailUniqueToUpdate(supplierId,email);
    }

    @Override
    public boolean deleteSupplier(String supId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supId);
    }
    @Override
    public ArrayList<SupplierDto> getAllSuppliers() throws ClassNotFoundException, SQLException {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDto> dtos = new ArrayList<>();
        for(Supplier supplier : suppliers){
            SupplierDto dto = new SupplierDto(
                    supplier.getSupplierId(),
                    supplier.getName(),
                    supplier.getContact(),
                    supplier.getAddress(),
                    supplier.getEmail()
            );

            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public boolean isEmailUnique(String email) throws SQLException, ClassNotFoundException {
        return supplierDAO.exist(email);
    }

    @Override
    public String loardNextSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewId();
    }

    @Override
    public ArrayList<BookDto> getAllBooks(String supplierId) throws SQLException, ClassNotFoundException {
        ArrayList<Book> books = supplierDAO.getAllBooks(supplierId);
        ArrayList<BookDto> dtos = new ArrayList<>();
        for(Book book : books){
            BookDto bookDto = new BookDto(
                    book.getBookId(),
                    book.getName(),
                    book.getQty(),
                    book.getPrice(),
                    book.getBookShelfId()
            );

            dtos.add(bookDto);
        }

        return dtos;
    }
    @Override
    public boolean save(SupplierDto dto, ArrayList<TempBookTM> arr) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            //HERE TRY TO SAVE SPPLIER...
            String saveSupplierSql = "insert into supplier values (?,?,?,?,?)";
            boolean isSavedTheSupplier = CrudUtil.execute(
                    saveSupplierSql,
                    dto.getSupplierId(),
                    dto.getName(),
                    dto.getContact(),
                    dto.getAddress(),
                    dto.getEmail()
            );


            if(isSavedTheSupplier){
                //HERE TRY TO SAVE BOOK SUPPLY TABLE
                String saveBookSupplySql = "insert into book_Supply values (?,?,?)";
                boolean isSavedCorrectly = true;

                for(TempBookTM temp : arr){
                    boolean isSavedBookSupply = CrudUtil.execute(
                            saveBookSupplySql,
                            temp.getBookId(),
                            dto.getSupplierId(),
                            temp.getQty()
                    );

                    if(!isSavedBookSupply){
                        isSavedCorrectly = false;
                    }
                }

                if(isSavedCorrectly){
                    con.commit();
                    return true;
                }else{
                    //BOOK SUPPLY SAVING ERROR
                    con.rollback();
                    return false;
                }

            }else{
                //SUPPLIER SAVING ERROR
                con.rollback();
                return false;
            }

        }catch (Exception e1){
            System.out.println("EXCEPTION");
            e1.printStackTrace();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }
    @Override
    public boolean updateSupplier(SupplierDto dto, ArrayList<TempBookTM> arr) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        try{
            con.setAutoCommit(false);

            //HERE UPDATE THE SUPPLIER
            String sql = "update supplier set name = ?, contact = ?, address = ?, email = ? where supplier_Id = ?";
            boolean isUpdate = CrudUtil.execute(
                    sql,
                    dto.getName(),
                    dto.getContact(),
                    dto.getAddress(),
                    dto.getEmail(),
                    dto.getSupplierId()
            );

            if(isUpdate){
                //HERE UPDATE THE SUPPLIER BOOKS
                //FRIST DELETE ALL ....
                String sqlDeleteBooks = "delete from book_Supply where Supplier_Id = ?";
                boolean isDeleted = CrudUtil.execute(
                        sqlDeleteBooks,
                        dto.getSupplierId()
                );

                if(isDeleted){
                    //NOW ADDING THE BOOKS WHAT WE HAVE UPDATED..
                    //HERE TRY TO SAVE BOOK SUPPLY TABLE
                    String saveBookSupplySql = "insert into book_Supply values (?,?,?)";
                    boolean isSavedCorrectly = true;

                    for(TempBookTM temp : arr){
                        boolean isSavedBookSupply = CrudUtil.execute(
                                saveBookSupplySql,
                                temp.getBookId(),
                                dto.getSupplierId(),
                                temp.getQty()
                        );

                        if(!isSavedBookSupply){
                            isSavedCorrectly = false;
                        }
                    }

                    if(isSavedCorrectly){
                        con.commit();
                        return true;
                    }else{
                        //BOOK SUPPLY SAVING ERROR
                        con.rollback();
                        return false;
                    }
                }else{
                    //DELETING BOOK SUPPLY ERROR
                    con.rollback();
                    return false;
                }
            }else{
                //SUPPLIER UPDATING ERROR
                con.rollback();
                return false;
            }

        }catch (Exception e1){
            System.out.println("EXCEPTION UPDATING");
            e1.printStackTrace();
        }finally {
            con.setAutoCommit(true);
        }
        return false;
    }

}
