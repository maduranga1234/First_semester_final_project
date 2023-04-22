package lk.ijse.super_cargo.dto.tm;

import javafx.scene.control.Button;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderTm {

    private String ItemCode;
    private String ItemName;
    private String UnitPrice;
    private double Quantity;
    private double QuntityUnitPrice;
    private Button action;
}
