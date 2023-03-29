package lk.ijse.super_cargo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Employee;
import lk.ijse.super_cargo.dto.tm.EmployeeTm;

import java.util.Properties;

public class EmployeeModel {
    private static final String URL = "jdbc:mysql://localhost:3306/super_cargo";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }


    public static boolean Save(Employee employee) throws SQLException {

        String sql = "INSERT INTO employee(employeeId,employeeName,nic,dateOfBirth,address,contactNumber,jobTitel)" +
                "VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, employee.getEmpId());
            pstm.setString(2, employee.getEmpName());
            pstm.setString(3, employee.getEmpNic());
            pstm.setString(4, employee.getEmpDob());
            pstm.setString(5, employee.getEmpAddress());
            pstm.setString(6, employee.getEmpContact());
            pstm.setString(7, employee.getEmpJob());

            int affectedRows = pstm.executeUpdate();
            return affectedRows > 0;
        }

    }

    public static ObservableList<EmployeeTm> getAll() throws SQLException {

        String sql = "SELECT * FROM Employee";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = pstm.executeQuery();

           ObservableList<EmployeeTm> employeeData= FXCollections.observableArrayList();

            while (resultSet.next()) {

                employeeData.add(new EmployeeTm(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)

                ));

            }return employeeData;
        }

    }

    public static Employee Search(String empId) throws SQLException {

         String sql="SELECT * FROM employee WHERE employeeId=?";

        ResultSet resultSet;
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, empId);

            resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                return new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)

                );
            }

        }
        return null;

    }


    public static boolean update(Employee employee) throws SQLException {
        String sql="UPDATE employee SET employeeName=?," +
                "nic=?,dateOfBirth=?,address=?,contactNumber=?,jobTitel=? WHERE employeeId=?";

        int affecteRows;
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, employee.getEmpName());
            pstm.setString(2, employee.getEmpNic());
            pstm.setString(3, employee.getEmpDob());
            pstm.setString(4, employee.getEmpAddress());
            pstm.setString(5, employee.getEmpContact());
            pstm.setString(6, employee.getEmpJob());
            pstm.setString(7, employee.getEmpId());

            affecteRows = pstm.executeUpdate();


            return affecteRows > 0;
        }

    }

    public static boolean delete(String empId) throws SQLException {
        String sql="DELETE FROM employee WHERE employeeId=?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, empId);

            int affectRows=pstm.executeUpdate();
            return affectRows > 0;
        }

    }


}