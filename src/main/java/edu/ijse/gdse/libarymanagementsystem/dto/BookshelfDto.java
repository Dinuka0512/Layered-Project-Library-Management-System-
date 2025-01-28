package edu.ijse.gdse.libarymanagementsystem.dto;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookshelfDto {
    private String bookshelfId;
    private String location;
    private String sectionId;
}
