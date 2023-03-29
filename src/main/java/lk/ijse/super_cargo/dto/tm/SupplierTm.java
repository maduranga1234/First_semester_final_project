package lk.ijse.super_cargo.dto.tm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplierTm {

    private String supplierId;
    private String supplierName;
    private String address;
    private  String contact;
    private String email;
}
