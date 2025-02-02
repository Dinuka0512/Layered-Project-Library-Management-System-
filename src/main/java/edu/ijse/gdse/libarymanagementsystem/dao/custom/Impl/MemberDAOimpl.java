package edu.ijse.gdse.libarymanagementsystem.dao.custom.Impl;

import edu.ijse.gdse.libarymanagementsystem.dao.custom.MemberDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.MemberPopularDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Member;
import edu.ijse.gdse.libarymanagementsystem.entity.MemberPopular;
import edu.ijse.gdse.libarymanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAOimpl implements MemberDAO {
    //==========================
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
    //==========================
    @Override
    public ArrayList<Member> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Member dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Member dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Member search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
