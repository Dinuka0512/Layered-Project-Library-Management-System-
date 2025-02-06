package edu.ijse.gdse.libarymanagementsystem.entity.tm;
import javafx.scene.control.Button;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TempBookTmEnt {
    private String bookId;
    private String name;
    private int qty;
    private double price;
    private String bookShelfId;
    private Button remove;
}
