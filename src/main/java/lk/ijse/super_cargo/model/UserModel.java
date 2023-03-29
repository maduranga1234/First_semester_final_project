package lk.ijse.super_cargo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Employee;
import lk.ijse.super_cargo.dto.User;

import java.util.Properties;

public class UserModel {
    private static final String URL = "jdbc:mysql://localhost:3306/super_cargo";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public static boolean SingUp(User user) throws SQLException {
        String sql = "INSERT INTO user(userName,employeeId,password,email,jobTitel)" +
                "VALUES (?,?,?,?,?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1,user.getUserName());
            pstm.setString(2,user.getEmployeeId());
            pstm.setString(3,user.getPassword());
            pstm.setString(4,user.getEmail());
            pstm.setString(5,user.getJobTitel());

            int affectedRows = pstm.executeUpdate();
            return affectedRows > 0;
        }

    }

    public static boolean updatePassword(User user) throws SQLException {

        String sql="UPDATE user SET password=? WHERE userName=?";

        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,user.getPassword());
        pstm.setString(2,user.getUserName());

        return pstm.executeUpdate() > 0;
    }




    public static User LogingAction(User logingCont) throws SQLException {
        User logingCheck=new User();

        String sql="SELECT userName,password,jobTitel FROM user WHERE userName=?";

        ResultSet resultSet;
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, logingCont.getUserName());

            resultSet = pstm.executeQuery();
            if(resultSet.next()){
                logingCheck.setUserName(resultSet.getString(1));
                logingCheck.setPassword(resultSet.getString(2));
                logingCheck.setJobTitel(resultSet.getString(3));
                System.out.println(logingCheck.getUserName());
                System.out.println(logingCheck.getPassword());
                System.out.println(logingCheck.getJobTitel());
            }
            return logingCheck;
        }

    }

    public static User SearchById(String userName) throws SQLException {

        String sql="SELECT * FROM user WHERE userName=?";
        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet resultSet=pstm.executeQuery();

        if(resultSet.next()){

            return  new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

        }
        return null;

    }
}
