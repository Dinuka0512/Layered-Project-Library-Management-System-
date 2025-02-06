package edu.ijse.gdse.libarymanagementsystem.entity.tm;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BookReturningTmEnt {
    private String issueID;
    private String bookId;
    private String memID;
    private String memName;
    private String memEmail;
    private String bookName;
    private String issueDate;
    private String issueTime;
}
