package lk.ijse.super_cargo.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Buyer {


    private String buyerId;
    private String userName;
    private String buyerName;
    private String country;
    private String contactNumber;
    private String email;
}
