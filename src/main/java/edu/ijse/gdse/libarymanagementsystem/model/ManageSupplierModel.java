package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.SupplierDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.TempBookIssueTm;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.TempBookTM;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ManageSupplierModel {
    public ArrayList<BookDto> getAllBooks(String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "select b.Book_Id , b.name, b.qty, b.price, b.bookshelf_Id from Supplier s Join Book_Supply bs on s.Supplier_Id = bs.Supplier_Id Join Book b on b.Book_Id = bs.Book_Id where s.Supplier_Id = ?";
        ResultSet res = CrudUtil.execute(sql,supplierId);
        ArrayList<BookDto> dtos = new ArrayList<>();
        while(res.next()){
            BookDto dto = new BookDto(
                    res.getString("Book_Id"),
                    res.getString("name"),
                    res.getInt("qty"),
                    res.getDouble("price"),
                    res.getString("bookshelf_Id")
            );

            dtos.add(dto);
        }

        return dtos;
    }

    public boolean save(SupplierDto dto, ArrayList<TempBookTM> arr) throws SQLException, ClassNotFoundException{
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


    public boolean updateSupplier(SupplierDto dto, ArrayList<TempBookTM> arr) throws SQLException , ClassNotFoundException{
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
