package edu.ijse.gdse.libarymanagementsystem.entity;
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
