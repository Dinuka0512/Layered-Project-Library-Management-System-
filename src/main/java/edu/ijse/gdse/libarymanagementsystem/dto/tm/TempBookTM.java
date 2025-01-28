package edu.ijse.gdse.libarymanagementsystem.dto.tm;
import javafx.scene.control.Button;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TempBookTM {
    private String bookId;
    private String name;
    private int qty;
    private double price;
    private String bookShelfId;
    private Button remove;
}
