package edu.ijse.gdse.libarymanagementsystem.dto;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookSuplyDto {
    private String book_Id;
    private String supplier_Id;
    private int qty;
}
