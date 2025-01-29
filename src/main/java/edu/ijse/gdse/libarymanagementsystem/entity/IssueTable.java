package edu.ijse.gdse.libarymanagementsystem.entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IssueTable {
    private String issueId;
    private String memId;
    private String UserId;
    private String date;
    private String time;
    private boolean isCompleted;
}
