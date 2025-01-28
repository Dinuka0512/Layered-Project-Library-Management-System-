package edu.ijse.gdse.libarymanagementsystem.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class BookDto {
    private String bookId;
    private String name;
    private int qty;
    private double price;
    private String bookShelfId;
}
