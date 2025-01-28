package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.controller.DashBoardContro;
import edu.ijse.gdse.libarymanagementsystem.db.DBConnection;
import edu.ijse.gdse.libarymanagementsystem.dto.IssueTableDto;
import edu.ijse.gdse.libarymanagementsystem.dto.tm.TempBookIssueTm;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageBookIssueModel {
    public boolean issueNow(IssueTableDto dto, ArrayList<TempBookIssueTm> tempDetails) throws SQLException, ClassNotFoundException{
        Connection con = DBConnection.getInstance().getConnection();
        try{
            con.setAutoCommit(false);
            //HERE TRY TO INSERT VALUES INTO ISSUE TABLE
            String addValuesIssue = "insert into Issue values(?,?,?,?,?,?)";
            boolean issueValuesSaved = CrudUtil.execute(
                    addValuesIssue,
                    dto.getIssueId(),
                    dto.getMemId(),
                    dto.getUserId(),
                    dto.getDate(),
                    dto.getTime(),
                    dto.isCompleted()
            );

            if(issueValuesSaved){
                //ISSUE TABLE SAVED!!!
                //HERE SAVE THE BOOK ISSUE--->
                boolean isSaved = true;

                String addValuesToBookIssue = "insert into Book_Issue values(?,?,?)";
                for(TempBookIssueTm tempDto : tempDetails){
                    TempBookIssueTm temp = new TempBookIssueTm(
                            tempDto.getIssueId(),
                            tempDto.getBookId(),
                            tempDto.getName(),
                            tempDto.getQty(),
                            tempDto.getBtnRemove()
                    );

                    boolean isBookIssueSaved = CrudUtil.execute(
                            addValuesToBookIssue,
                            temp.getBookId(),
                            temp.getIssueId(),
                            temp.getQty()
                    );

                    if(!isBookIssueSaved){
                        isSaved = false;
                    }
                }

                if(isSaved){
                    //IS SAVED THE BOOK ISSUES
                    //HERE GOING TO UPDATE THE BOOK TABLE QTY ----->

                    boolean isUpdated = true;
                    String updateBookQty = "update book set qty = qty - ? where Book_Id = ?";

                    for(TempBookIssueTm tempDto : tempDetails){
                        TempBookIssueTm temp = new TempBookIssueTm(
                                tempDto.getIssueId(),
                                tempDto.getBookId(),
                                tempDto.getName(),
                                tempDto.getQty(),
                                tempDto.getBtnRemove()
                        );

                        boolean isBookReduseQty = CrudUtil.execute(
                                updateBookQty,
                                temp.getQty(),
                                temp.getBookId()
                        );

                        if(!isBookReduseQty){
                            isSaved = false;
                        }
                    }

                    if(isUpdated){
                        //THE BOOK QTY IS UPDATED
                        //ALL ARE OK NOW
                        con.commit();
                        return true;
                    }else {
                        //BOOK UPDATING PROBLEM
                        con.rollback();
                        return false;
                    }

                }else{
                    //BOOK ISSUE TABLE PROBLEM
                    con.rollback();
                    return false;
                }

            }else{
                //ISSUE TABLE IS NOT SAVED
                con.rollback();
                return false;
            }

        }catch (Exception e1){
            con.rollback();
            System.out.println("EXCEPTION..Something Went wrong...!");
            e1.printStackTrace();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }
}
