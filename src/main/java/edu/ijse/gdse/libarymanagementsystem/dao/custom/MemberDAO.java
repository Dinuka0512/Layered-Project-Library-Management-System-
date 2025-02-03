package edu.ijse.gdse.libarymanagementsystem.dao.custom;

import edu.ijse.gdse.libarymanagementsystem.dao.CrudDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.MemberDto;
import edu.ijse.gdse.libarymanagementsystem.dto.MemberPopularDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Member;
import edu.ijse.gdse.libarymanagementsystem.entity.MemberPopular;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberDAO extends CrudDAO<Member> {
    ArrayList<MemberPopular> getPopularMember() throws SQLException, ClassNotFoundException;
    boolean isTheEmailAllreadyHave(String email) throws SQLException, ClassNotFoundException;
    boolean isThisEmail(String id, String email) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllMemberIds() throws SQLException, ClassNotFoundException;
    Member getMemberDetails(String memId) throws SQLException, ClassNotFoundException;
}
