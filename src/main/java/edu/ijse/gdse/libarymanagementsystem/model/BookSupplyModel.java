package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.dto.BookSuplyDto;
import edu.ijse.gdse.libarymanagementsystem.dto.BookSupplyNameAndQtyDto;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookSupplyModel {
    public BookSuplyDto getAll(String bookId) throws ClassNotFoundException, SQLException {
        String sql = "select * from book_Supply where Book_Id = ?";
        ResultSet res = CrudUtil.execute(
                sql,
                bookId
        );

        if(res.next()){
            BookSuplyDto dto = new BookSuplyDto(
                    res.getString("Book_Id"),
                    res.getString("Supplier_Id"),
                    res.getInt("qty")
            );

            return dto;
        }
        return null;
    }

    public ArrayList<BookSupplyNameAndQtyDto> getSupplierNameAndAllBookSuppliedQty(){
        try{
            String sql = "select s.name, Sum(bs.qty) as qty from Supplier s join book_Supply bs on s.Supplier_Id = bs.Supplier_Id Group by bs.Supplier_Id";
            ResultSet res = CrudUtil.execute(sql);
            ArrayList<BookSupplyNameAndQtyDto> dtos = new ArrayList<>();
            while(res.next()){
                BookSupplyNameAndQtyDto dto = new BookSupplyNameAndQtyDto(
                        res.getString("name"),
                        res.getInt("qty")
                );

                dtos.add(dto);
            }
            return dtos;
        }catch (ClassNotFoundException e1){
            System.out.println("Class Not found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("Sql Exception");
            e2.printStackTrace();
        }

        return null;
    }
}
