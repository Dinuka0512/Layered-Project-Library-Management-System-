package edu.ijse.gdse.libarymanagementsystem.model;

import edu.ijse.gdse.libarymanagementsystem.dto.MemberPopularDto;
import edu.ijse.gdse.libarymanagementsystem.dto.MemberDto;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberModel {
    public boolean isTheEmailAllreadyHave(String email) throws SQLException, ClassNotFoundException{
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

    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException{
        String sql = "delete from Member where Member_Id = ?";
        boolean res = CrudUtil.execute(sql, id);
        return res;
    }

    public boolean updateMember(MemberDto dto) throws SQLException, ClassNotFoundException{
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

    public boolean isThisEmail(String id, String email) throws SQLException, ClassNotFoundException{
        String sql = "select email from Member where Member_id = ?";
        ResultSet res = CrudUtil.execute(sql, id);
        if(res.next()){
            if(res.getString("email").equals(email)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getAllMemberIds() throws SQLException, ClassNotFoundException{
        String sql = "select member_Id from Member";
        ArrayList<String> dto = new ArrayList<>();
        ResultSet res = CrudUtil.execute(sql);
        while (res.next()){
            String id = res.getString("member_Id");
            dto.add(id);
        }

        return dto;
    }

    public MemberDto getMemberDetails(String memId) throws SQLException, ClassNotFoundException{
        String sql = "select * from Member where member_Id = ? ";
        ResultSet res = CrudUtil.execute(
                sql,
                memId
        );

        if(res.next()){
            MemberDto memberDto = new MemberDto(
                    res.getString("Member_Id"),
                    res.getString("name"),
                    res.getString("adress"),
                    res.getString("email"),
                    res.getString("contact_No")
            );

            return memberDto;
        }
        return null;
    }

}
