package edu.ijse.gdse.libarymanagementsystem.entity;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BookSupplyNameAndQtyDto {
    private String SupplierName;
    private int qty;
}
