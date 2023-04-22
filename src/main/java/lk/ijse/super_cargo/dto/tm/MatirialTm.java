package lk.ijse.super_cargo.dto.tm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class MatirialTm {

    private String matirialId;
    private String supplierId;
    private String matirialName;
    private double weight;
    private double Price;
    private String quality;
}
