package lk.ijse.super_cargo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Item {

    private String itemId;
    private String itemName;
    private double weight;
    private  double unitPrice;
    private String quality;
}
