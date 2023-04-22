package lk.ijse.super_cargo.dto.tm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SalaryTm {

    private String salaryId;
    private String employeeId;
    private double ot;
    private  double amount;
    private String paymentMethord;
}
