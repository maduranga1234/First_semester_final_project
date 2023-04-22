package lk.ijse.super_cargo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.super_cargo.dto.Item;
import lk.ijse.super_cargo.dto.Salary;
import lk.ijse.super_cargo.dto.tm.ItemTm;
import lk.ijse.super_cargo.dto.tm.SalaryTm;
import lk.ijse.super_cargo.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {


    public static boolean Save(Salary salary) throws SQLException {
        String sql="INSERT INTO salary(salaryId,employeeId,ot,amount,paymentMethord)" +
                "VALUES (?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                salary.getSalaryId(),
                salary.getEmployeeId(),
                salary.getOt(),
                salary.getAmount(),
                salary.getPaymentMethord()
        );
    }

    public static Salary Search(String salaryId) throws SQLException {
        String sql = "SELECT * FROM salary WHERE salaryId=?";

        ResultSet resultSet=CrudUtil.execute(sql,salaryId);

        if(resultSet.next()){
            return (new Salary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));

        }
        return null;


    }

    public static boolean Update(Salary salary) throws SQLException {
        String sql="UPDATE salary SET employeeId=?,ot=?,amount=?,paymentMethord=?" +
                "WHERE salaryId=?";

        return CrudUtil.execute(

                sql,
                salary.getEmployeeId(),
                salary.getOt(),
                salary.getAmount(),
                salary.getPaymentMethord(),
                salary.getSalaryId());

    }

    public static boolean delete(String salaryId) throws SQLException {
        String sql="DELETE FROM salary WHERE salaryId=?";

        return CrudUtil.execute(
                sql,
                salaryId
        );

    }


    public static ObservableList<SalaryTm> getAll() throws SQLException {

        String sql = "SELECT * FROM salary";

        ObservableList <SalaryTm> obList= FXCollections.observableArrayList();

        ResultSet resultSet=CrudUtil.execute(sql);
        while (resultSet.next()){

            obList.add(new SalaryTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));

        }
        return obList;
    }




}
