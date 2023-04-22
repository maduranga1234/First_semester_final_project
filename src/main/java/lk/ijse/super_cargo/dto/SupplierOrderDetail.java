package lk.ijse.super_cargo.dto;

import lombok.*;

import java.security.PrivateKey;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class SupplierOrderDetail {


    private String itemId;
    private double qnt;
}
