package edu.ijse.gdse.libarymanagementsystem.entity;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AuthorDto {
    private String auhorId;
    private String name;
    private String Email;
    private String Address;
    private String contract;
}
