package edu.ijse.gdse.libarymanagementsystem.dto;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDto {
    private String memberId;
    private String name;
    private String address;
    private String email;
    private String contact;
}
