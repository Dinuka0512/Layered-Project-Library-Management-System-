package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.BookSupplyDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.BookSupplyNameAndQtyDto;
import edu.ijse.gdse.libarymanagementsystem.entity.BookSuply;
import edu.ijse.gdse.libarymanagementsystem.entity.BookSupplyNameAndQty;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookSupplyDAOimpl implements BookSupplyDAO {
    //==============================


    @Override
    public ArrayList<BookSupplyNameAndQty> getSupplierNameAndAllBookSuppliedQty() throws SQLException, ClassNotFoundException {
        String sql = "select s.name, Sum(bs.qty) as qty from Supplier s join book_Supply bs on s.Supplier_Id = bs.Supplier_Id Group by bs.Supplier_Id";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<BookSupplyNameAndQty> dtos = new ArrayList<>();
        while(res.next()){
            BookSupplyNameAndQty dto = new BookSupplyNameAndQty(
                    res.getString("name"),
                    res.getInt("qty")
            );

            dtos.add(dto);
        }
        return dtos;
    }

    //==============================

    @Override
    public ArrayList<BookSuply> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(BookSuply dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(BookSuply dto) throws SQLException, ClassNotFoundException {
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
    public BookSuply search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
