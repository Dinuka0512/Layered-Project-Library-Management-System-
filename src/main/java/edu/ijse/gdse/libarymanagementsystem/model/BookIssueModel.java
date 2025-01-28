package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.dto.BookDto;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.BookIdAndQty;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookIssueModel {
    public ArrayList<BookIdAndQty> getPopularBooks() throws SQLException, ClassNotFoundException {
        String sql = "select Book_Id,sum(qty) as qty from Book_Issue Group by Book_Id limit 4";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<BookIdAndQty> dtos = new ArrayList<>();
        while(res.next()){
            BookIdAndQty dto = new BookIdAndQty(
                    res.getString("Book_Id"),
                    res.getInt("qty")
            );

            dtos.add(dto);
        }
        return dtos;
    }
}
