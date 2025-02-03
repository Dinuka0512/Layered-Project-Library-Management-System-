package edu.ijse.gdse.libarymanagementsystem.bo.custom.impl;

import edu.ijse.gdse.libarymanagementsystem.bo.custom.MemberBO;
import edu.ijse.gdse.libarymanagementsystem.dao.DAOFactory;
import edu.ijse.gdse.libarymanagementsystem.dao.custom.MemberDAO;
import edu.ijse.gdse.libarymanagementsystem.dto.MemberDto;
import edu.ijse.gdse.libarymanagementsystem.dto.MemberPopularDto;
import edu.ijse.gdse.libarymanagementsystem.entity.Member;
import edu.ijse.gdse.libarymanagementsystem.entity.MemberPopular;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberBOimpl implements MemberBO {
    private MemberDAO memberDAO = (MemberDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBER);
    @Override
    public ArrayList<MemberPopularDto> getPopularMember() throws SQLException, ClassNotFoundException {
        ArrayList<MemberPopular> memberPopular = memberDAO.getPopularMember();
        ArrayList<MemberPopularDto> memberPopularDtos = new ArrayList<>();
        for(MemberPopular member: memberPopular){
            MemberPopularDto memberPopularDto = new MemberPopularDto(
                    member.getName(),
                    member.getTotalIssues()
            );

            memberPopularDtos.add(memberPopularDto);
        }

        return memberPopularDtos;
    }
    @Override
    public String genarateMemberId() throws SQLException, ClassNotFoundException {
        return memberDAO.generateNewId();
    }

    @Override
    public ArrayList<MemberDto> getAllDetails() throws SQLException, ClassNotFoundException {
        ArrayList<Member> membersLists = memberDAO.getAll();
        ArrayList<MemberDto> memberDtos = new ArrayList<>();
        for(Member member : membersLists){
            MemberDto memberDto = new MemberDto(
                    member.getMemberId(),
                    member.getName(),
                    member.getAddress(),
                    member.getEmail(),
                    member.getContact()
            );

            memberDtos.add(memberDto);
        }

        return memberDtos;
    }

    @Override
    public boolean saveMember(MemberDto dto) throws SQLException, ClassNotFoundException {
        return memberDAO.save(new Member(dto.getMemberId(),dto.getName(),dto.getAddress(),dto.getEmail(),dto.getContact()));
    }
}
