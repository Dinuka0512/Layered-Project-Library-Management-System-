package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.MemberDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.MemberDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Member;
import edu.ijse.gdse.libarymanagementsystem.entity.MemberPopular;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAOimpl implements MemberDAO {

    //==========================
    @Override
    public ArrayList<String> getAllMemberIds() throws SQLException, ClassNotFoundException {
        String sql = "select member_Id from Member";
        ArrayList<String> dto = new ArrayList<>();
        ResultSet res = CrudUtil.execute(sql);
        while (res.next()){
            String id = res.getString("member_Id");
            dto.add(id);
        }

        return dto;
    }

    @Override
    public Member getMemberDetails(String memId) throws SQLException, ClassNotFoundException {
        String sql = "select * from Member where member_Id = ? ";
        ResultSet res = CrudUtil.execute(
                sql,
                memId
        );

        if(res.next()){
            Member member = new Member(
                    res.getString("Member_Id"),
                    res.getString("name"),
                    res.getString("adress"),
                    res.getString("email"),
                    res.getString("contact_No")
            );

            return member;
        }
        return null;
    }

    @Override
    public ArrayList<MemberPopular> getPopularMember() throws SQLException, ClassNotFoundException {
        String sql = "SELECT m.name, m.email, COUNT(i.Member_Id) AS Total_Issues FROM Issue i JOIN Book_Issue bi ON i.Issue_Id = bi.Issue_Id JOIN Member m ON i.Member_Id = m.Member_Id GROUP BY m.Member_Id, m.name, m.email ORDER BY Total_Issues DESC LIMIT 3";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<MemberPopular> popMembers = new ArrayList<>();
        while(res.next()){
            MemberPopular member = new MemberPopular(
                    res.getString("name"),
                    res.getInt("Total_Issues")
            );

            popMembers.add(member);
        }
        return popMembers;
    }

    @Override
    public boolean isTheEmailAllreadyHave(String email) throws SQLException, ClassNotFoundException {
        String sql = "select * from Member where email = ?";
        ResultSet res = CrudUtil.execute(
                sql,
                email
        );

        if(res.next()){
            return true;
        }
        return false;
    }

    @Override
    public boolean isThisEmail(String id, String email) throws SQLException, ClassNotFoundException {
        String sql = "select email from Member where Member_id = ?";
        ResultSet res = CrudUtil.execute(sql, id);
        if(res.next()){
            if(res.getString("email").equals(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        String sql = "select Member_Id from Member Order by Member_Id desc limit 1";
        ResultSet res = CrudUtil.execute(sql);
        if(res.next()){
            String lastId = res.getString("Member_Id"); //M001
            String subString = lastId.substring(1); //001
            int i = Integer.parseInt(subString); //1
            i = i + 1; // 2
            String newId = String.format("M%03d", i);
            return newId;
        }
        return "M001";
    }

    @Override
    public ArrayList<Member> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from Member";
        ResultSet res = CrudUtil.execute(sql);
        ArrayList<Member> dtos = new ArrayList<>();
        while(res.next()){
            Member member = new Member(
                    res.getString("Member_Id"),
                    res.getString("name"),
                    res.getString("adress"),
                    res.getString("email"),
                    res.getString("contact_No")
            );

            dtos.add(member);
        }

        return dtos;
    }

    @Override
    public boolean save(Member dto) throws SQLException, ClassNotFoundException {
        String sql = "insert into Member value (?,?,?,?,?)";
        boolean isSaved = CrudUtil.execute(
                sql,
                dto.getMemberId(),
                dto.getName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getContact()
        );

        return isSaved;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "delete from Member where Member_Id = ?";
        boolean res = CrudUtil.execute(sql, id);
        return res;
    }

    @Override
    public boolean update(Member dto) throws SQLException, ClassNotFoundException {
        String sql = "update Member set name = ?, adress = ?, email = ?, contact_No = ? where Member_Id = ?";
        boolean res = CrudUtil.execute(
                sql,
                dto.getName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getContact(),
                dto.getMemberId()
        );

        return res;
    }

    //==========================

    @Override
    public Member search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
