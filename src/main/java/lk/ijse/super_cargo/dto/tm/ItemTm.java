package lk.ijse.super_cargo.dto.tm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ItemTm {

    private String itemId;
    private String itemName;
    private double weight;
    private double unitPrice;
    private String quality;
}
