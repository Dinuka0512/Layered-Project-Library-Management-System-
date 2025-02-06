package edu.ijse.gdse.libarymanagementsystem.entity.tm;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IssueTableTmEnt {
    private String issueId;
    private String memId;
    private String memEmail;
    private String date;
    private String time;
    private String userId;
}
