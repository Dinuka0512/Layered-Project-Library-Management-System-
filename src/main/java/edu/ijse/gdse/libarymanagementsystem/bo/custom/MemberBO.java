package edu.ijse.gdse.libarymanagementsystem.bo.custom;

import edu.ijse.gdse.libarymanagementsystem.bo.SuperBO;
import edu.ijse.gdse.libarymanagementsystem.dto.MemberDto;
import edu.ijse.gdse.libarymanagementsystem.dto.MemberPopularDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberBO extends SuperBO {
    ArrayList<MemberPopularDto> getPopularMember() throws SQLException, ClassNotFoundException;
    String genarateMemberId() throws SQLException, ClassNotFoundException;
    ArrayList<MemberDto> getAllDetails() throws SQLException, ClassNotFoundException;
    boolean saveMember(MemberDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteMember(String id) throws SQLException, ClassNotFoundException;
    boolean updateMember(MemberDto dto) throws SQLException, ClassNotFoundException;
    boolean isTheEmailAllreadyHave(String email) throws SQLException, ClassNotFoundException;
    boolean isThisEmail(String id, String email) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllMemberIds() throws SQLException, ClassNotFoundException;
    MemberDto getMemberDetails(String memId) throws SQLException, ClassNotFoundException;
}
