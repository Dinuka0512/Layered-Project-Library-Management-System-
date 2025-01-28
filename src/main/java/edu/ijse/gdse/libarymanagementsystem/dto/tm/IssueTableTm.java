package edu.ijse.gdse.libarymanagementsystem.dto.tm;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IssueTableTm {
    private String issueId;
    private String memId;
    private String memEmail;
    private String date;
    private String time;
    private String userId;
}
