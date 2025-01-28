package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.Barchart;
import edu.ijse.gdse.libarymanagementsystem.dto.ShortCuts.Linechart;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnBookModel {
    public String getBookRetunId() {
        try{
            String sql = "select Return_Id from return_Book Order by Return_Id desc limit 1";
            ResultSet res = CrudUtil.execute(sql);
            if(res.next()){
                String lastId = res.getString("Return_Id"); // BR001
                String subString = lastId.substring(2); // 001
                int i = Integer.parseInt(subString); // 1
                i = i + 1;
                String newId = String.format("BR%03d", i);
                return newId;
            }
            return "BR001";
        }catch (ClassNotFoundException e1){
            System.out.println("Class not found");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("SQL Exception");
            e2.printStackTrace();
        }
        return "BR001";
    }

    public ArrayList<Linechart> getDataToAddLineChart(){
        try{
            String sql = "select Date, count(*) as count  from Return_Book Group by Date";
            ResultSet res = CrudUtil.execute(sql);

            ArrayList<Linechart> linecharts = new ArrayList<>();
            while(res.next()){
                Linechart linechart = new Linechart(
                        res.getString("Date"),
                        res.getInt("count")
                );

                linecharts.add(linechart);
            }

            return linecharts;

        } catch (ClassNotFoundException e1){
            System.out.println("Class Not found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("Sql Exception");
            e2.printStackTrace();
        }
        return  null;
    }

    public int getTodayBookReturns(String date){
        try{
            String sql = "select count(Return_Id) as count from Return_Book where Date = ?";
            ResultSet res = CrudUtil.execute(sql,date);
            if(res.next()){
                return res.getInt("count");
            }
        }catch (ClassNotFoundException e1){
            System.out.println("Class Not found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("Sql Exception");
            e2.printStackTrace();
        }

        return 0;
    }

    public int getCashonHandToday(String date){
        try{
            String sql = "select Sum(Amount) as amount from Return_Book where Date = ?";
            ResultSet res = CrudUtil.execute(sql, date);
            if(res.next()){
                return res.getInt("amount");
            }
        }catch (ClassNotFoundException e1){
            System.out.println("Class Not found Exception");
            e1.printStackTrace();
        }catch (SQLException e2){
            System.out.println("Sql Exception");
            e2.printStackTrace();
        }

        return 0;
    }

    public ArrayList<Barchart> getBarchartValues(){
        try{
            String sql = "select Date, sum(Amount) as amount from Return_Book Group by Date";
            ResultSet res = CrudUtil.execute(sql);
            ArrayList<Barchart> dtos = new ArrayList<>();
            if(res.next()){
                while(res.next()){
                    Barchart dto = new Barchart(
                            res.getString("Date"),
                            res.getDouble("amount")
                    );

                    dtos.add(dto);
                }
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
