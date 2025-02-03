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
}
