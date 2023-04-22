package lk.ijse.super_cargo.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class BuyerTm {


    private String buyerId;
    private String userName;
    private String buyerName;
    private String country;
    private String contactNumber;
    private String email;
}
