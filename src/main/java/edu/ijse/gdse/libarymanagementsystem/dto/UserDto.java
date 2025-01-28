package edu.ijse.gdse.libarymanagementsystem.dto;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private String userId;
    private String name;
    private String address;
    private String password;
    private String email;
}
