package lk.ijse.super_cargo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Salary {

    private String salaryId;
    private String employeeId;
    private double ot;
    private  double amount;
    private String paymentMethord;
}
