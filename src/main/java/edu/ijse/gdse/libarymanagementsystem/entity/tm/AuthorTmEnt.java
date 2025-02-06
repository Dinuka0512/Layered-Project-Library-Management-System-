package edu.ijse.gdse.libarymanagementsystem.entity.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AuthorTmEnt {
    private String auhorId;
    private String name;
    private String Email;
    private String Address;
    private String contract;
}
