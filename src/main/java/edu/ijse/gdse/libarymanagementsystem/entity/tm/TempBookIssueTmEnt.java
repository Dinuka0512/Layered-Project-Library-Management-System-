package edu.ijse.gdse.libarymanagementsystem.entity.tm;

import javafx.scene.control.Button;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TempBookIssueTmEnt {
    private String issueId;
    private String bookId;
    private String name;
    private int qty;
    private Button btnRemove;
}
