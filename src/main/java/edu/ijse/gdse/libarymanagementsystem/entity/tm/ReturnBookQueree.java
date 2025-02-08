package edu.ijse.gdse.libarymanagementsystem.entity.tm;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReturnBookQueree {
    private String id;
    private String bookId;
    private String name;
    private int qty;
    private double price;
    private String bookShelfId;
    private String IssueId;
    private double fee;
    private String formattedTime;
    private String date;
}
