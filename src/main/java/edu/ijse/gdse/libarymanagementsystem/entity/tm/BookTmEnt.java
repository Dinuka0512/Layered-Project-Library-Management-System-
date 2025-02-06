package edu.ijse.gdse.libarymanagementsystem.entity.tm;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookTmEnt {
    private String bookId;
    private String name;
    private int qty;
    private double price;
    private String categoryName;
    private String authorName;
}
