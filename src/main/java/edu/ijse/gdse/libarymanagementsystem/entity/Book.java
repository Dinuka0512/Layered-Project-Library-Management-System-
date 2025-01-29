package edu.ijse.gdse.libarymanagementsystem.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Book {
    private String bookId;
    private String name;
    private int qty;
    private double price;
    private String bookShelfId;
}
