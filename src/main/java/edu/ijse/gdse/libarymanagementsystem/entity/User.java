package edu.ijse.gdse.libarymanagementsystem.entity;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String userId;
    private String name;
    private String address;
    private String password;
    private String email;
    private String BranchId;
}
