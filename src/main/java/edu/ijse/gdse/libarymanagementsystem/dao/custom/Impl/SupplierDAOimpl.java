package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.SupplierDAO;
import edu.ijse.gdse.libarymanagementsystem.entity.Book;
import edu.ijse.gdse.libarymanagementsystem.entity.Supplier;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOimpl implements SupplierDAO {
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
        return null;
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
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
