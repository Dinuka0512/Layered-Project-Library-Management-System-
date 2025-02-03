package edu.ijse.gdse.libarymanagementsystem.entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BookIdAndQty {
    private String book_Id;
    private int qty;
}
