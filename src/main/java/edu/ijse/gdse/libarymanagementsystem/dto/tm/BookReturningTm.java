package edu.ijse.gdse.libarymanagementsystem.dto.tm;
import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BookReturningTm {
    private String issueID;
    private String bookId;
    private String memID;
    private String memName;
    private String memEmail;
    private String bookName;
    private String issueDate;
    private String issueTime;
}
