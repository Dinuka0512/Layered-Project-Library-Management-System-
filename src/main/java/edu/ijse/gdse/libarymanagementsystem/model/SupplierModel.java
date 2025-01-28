package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.SupplierDto;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public ArrayList<String> getAllSupplierIds() throws ClassNotFoundException, SQLException {
        String sql = "select Supplier_Id from Supplier";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<String> dto = new ArrayList<>();
        while(res.next()){
            String id = res.getString("Supplier_Id");
            dto.add(id);
        }

        return dto;
    }

    public String loardNextSupplierId() throws SQLException, ClassNotFoundException{
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

    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException{
        String sql = "insert into supplier values (?,?,?,?,?)";
        boolean isSaved = CrudUtil.execute(
                sql,
                dto.getSupplierId(),
                dto.getName(),
                dto.getContact(),
                dto.getAddress(),
                dto.getEmail()
        );

        return isSaved;
    }

    public boolean isEmailUnique(String email) throws SQLException, ClassNotFoundException{
        String sql = "select * from supplier where email = ?";
        ResultSet res = CrudUtil.execute(sql, email);
        if(res.next()){
            //EMAIL IS ALL READY HAVE
            return false;
        }else{
            //EMAIL IS UNIQUE
            return true;
        }
    }

    public String getSupplierName(String id) throws ClassNotFoundException, SQLException{
        String sql = "select name from supplier where Supplier_Id = ?";
        ResultSet res = CrudUtil.execute(sql, id);
        if(res.next()){
            return res.getString("name");
        }
        return null;
    }

    public ArrayList<SupplierDto> getAllSuppliers() throws ClassNotFoundException, SQLException{
        String sql = "select * from supplier";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<SupplierDto> dtos = new ArrayList<>();
        while(res.next()){
            SupplierDto dto = new SupplierDto(
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

    public boolean deleteSupplier(String supId) throws SQLException, ClassNotFoundException{
        String sql = "delete from supplier where Supplier_Id = ?";
        boolean isDeleted = CrudUtil.execute(sql,supId);
        return isDeleted;
    }

    public boolean isEmailUniqueToUpdate(String supplierId, String email) {
        try{
            String sql = "select * from supplier where email = ? And Supplier_Id != ?";
            ResultSet res = CrudUtil.execute(
                    sql,
                    email,
                    supplierId
            );

            if(res.next()){
                return false;
            }else{
                return true;
            }

        }catch (ClassNotFoundException e1){
            System.out.println("Class Not Found Exception");
            e1.printStackTrace();
        }catch (SQLException e2) {
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
        return false;
    }


}
