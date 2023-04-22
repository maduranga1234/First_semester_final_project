package lk.ijse.super_cargo.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplierDetailTm {
    private String itemcode;
    private String itemname;
    private String qty;
    private double payment;
    private Button removebtn;
}
