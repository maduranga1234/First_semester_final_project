package lk.ijse.super_cargo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Employee;
import lk.ijse.super_cargo.dto.tm.EmployeeTm;
import lk.ijse.super_cargo.util.CrudUtil;

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

//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, employee.getEmpId());
//            pstm.setString(2, employee.getEmpName());
//            pstm.setString(3, employee.getEmpNic());
//            pstm.setString(4, employee.getEmpDob());
//            pstm.setString(5, employee.getEmpAddress());
//            pstm.setString(6, employee.getEmpContact());
//            pstm.setString(7, employee.getEmpJob());
//
//            int affectedRows = pstm.executeUpdate();
//            return affectedRows > 0;
//        }

        return CrudUtil.execute(
                sql,
                employee.getEmpId(),
                employee.getEmpName(),
                employee.getEmpNic(),
                employee.getEmpDob(),
                employee.getEmpAddress(),
                employee.getEmpContact(),
                employee.getEmpJob()
        );
    }

    public static ObservableList<EmployeeTm> getAll() throws SQLException {

        String sql = "SELECT * FROM Employee";

      ObservableList<EmployeeTm>obList=FXCollections.observableArrayList();

      ResultSet resultSet = CrudUtil.execute(sql);

      while (resultSet.next()){

          obList.add(new EmployeeTm(
                  resultSet.getString(1),
                  resultSet.getString(2),
                  resultSet.getString(3),
                  resultSet.getString(4),
                  resultSet.getString(5),
                  resultSet.getString(6),
                  resultSet.getString(7)
          ));

      }
      return obList;

    }

    public static Employee Search(String empId) throws SQLException {

         String sql="SELECT * FROM employee WHERE employeeId=?";

      ResultSet resultSet=CrudUtil.execute(sql,empId);
      if(resultSet.next()){
          return (new Employee(
                  resultSet.getString(1),
                  resultSet.getString(2),
                  resultSet.getString(3),
                  resultSet.getString(4),
                  resultSet.getString(5),
                  resultSet.getString(6),
                  resultSet.getString(7)

          ));

      }
        return null;
    }


    public static boolean update(Employee employee) throws SQLException {
        String sql="UPDATE employee SET employeeName=?," +
                "nic=?,dateOfBirth=?,address=?,contactNumber=?,jobTitel=? WHERE employeeId=?";

       return CrudUtil.execute(
               sql,

               employee.getEmpName(),
               employee.getEmpNic(),
               employee.getEmpDob(),
               employee.getEmpAddress(),
               employee.getEmpContact(),
               employee.getEmpJob(),
               employee.getEmpId()
       );

    }

    public static boolean delete(String empId) throws SQLException {
        String sql="DELETE FROM employee WHERE employeeId=?";

      return CrudUtil.execute(
              sql,
              empId
      );

    }

    public static List<String> LoadEmployeeIds() throws SQLException {

        String sql = "SELECT employeeId FROM employee";
        List<String> allItemData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            allItemData.add(resultSet.getString(1));


        }
        return allItemData;
    }
}