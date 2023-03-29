package lk.ijse.super_cargo.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Matirial {


    private String matirialId;
    private String matirialName;
    private double weight;
    private  double price;
    private String quality;
}
