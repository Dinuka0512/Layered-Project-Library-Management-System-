package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.SupplierDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.SupplierDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;
import edu.ijse.gdse.libarymanagementsystem.entity.Supplier;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOimpl implements SupplierDAO {
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "delete from supplier where Supplier_Id = ?";
        boolean isDeleted = CrudUtil.execute(sql,id);
        return isDeleted;
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from supplier where email = ?";
        String email = id;
        ResultSet res = CrudUtil.execute(sql, email);
        if(res.next()){
            //EMAIL IS ALL READY HAVE
            return false;
        }else{
            //EMAIL IS UNIQUE
            return true;
        }
    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String sql = "select Supplier_Id from supplier order by Supplier_Id desc limit 1";
        ResultSet res = CrudUtil.execute(sql);
        if(res.next()){
            String lastId = res.getString("Supplier_Id"); //Sup001
            String subString = lastId.substring(3); //001
            int i = Integer.parseInt(subString);
            i = i + 1;
            String newId = String.format("Sup%03d",i);
            return newId;
        }
        return "Sup001";
    }

    @Override
    public boolean isEmailUniqueToUpdate(String supplierId, String email) throws SQLException, ClassNotFoundException {
        String sql = "select * from supplier where email = ? And Supplier_Id != ?";
        ResultSet res = CrudUtil.execute(
                sql,
                email,
                supplierId
        );
        return (res.next())? false : true;
    }

    @Override
    public ArrayList<Book> getAllBooks(String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "select b.Book_Id , b.name, b.qty, b.price, b.bookshelf_Id from Supplier s Join Book_Supply bs on s.Supplier_Id = bs.Supplier_Id Join Book b on b.Book_Id = bs.Book_Id where s.Supplier_Id = ?";
        ResultSet res = CrudUtil.execute(sql,supplierId);
        ArrayList<Book> dtos = new ArrayList<>();
        while(res.next()){
            Book dto = new Book(
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

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from supplier";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<Supplier> dtos = new ArrayList<>();
        while(res.next()) {
            Supplier dto = new Supplier(
                    res.getString("Supplier_Id"),
                    res.getString("name"),
                    res.getString("contact"),
                    res.getString("address"),
                    res.getString("Email")
            );

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
