package edu.ijse.gdse.libarymanagementsystem.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IssueTableDto {
    private String issueId;
    private String memId;
    private String UserId;
    private String date;
    private String time;
    private boolean isCompleted;
}
