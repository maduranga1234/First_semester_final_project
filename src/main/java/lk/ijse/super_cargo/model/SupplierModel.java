package lk.ijse.super_cargo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Employee;
import lk.ijse.super_cargo.dto.Supplier;
import lk.ijse.super_cargo.dto.tm.SupplierTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SupplierModel {
    private static final String URL = "jdbc:mysql://localhost:3306/super_cargo";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }



    public static boolean Save(Supplier supplier) throws SQLException {

        String sql="INSERT INTO supplier(supplierId,supplierName,address,contactNumber,email)" +
                "VALUES (?,?,?,?,?)";

        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);


        pstm.setString(1,supplier.getSupplierId());
        pstm.setString(2,supplier.getSupplierName());
        pstm.setString(3,supplier.getAddress());
        pstm.setString(4,supplier.getContact());
        pstm.setString(5,supplier.getEmail());

        int affecteRows=pstm.executeUpdate();
        return affecteRows > 0;
    }

    public static Supplier Search(String supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplierId=?";

        ResultSet resultSet;

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, supplierId);

            resultSet = pstm.executeQuery();


            if (resultSet.next()) {
                return new Supplier(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );

            }

        }return null;
    }

    public static ObservableList<SupplierTm> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";


        ResultSet resultSet;
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            resultSet = pstm.executeQuery();


            ObservableList<SupplierTm>supplierData= FXCollections.observableArrayList();

            while (resultSet.next()) {

                supplierData.add(new SupplierTm(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)

                ));

            } return supplierData;

        }
    }

    public static boolean Update(Supplier supplier) throws SQLException {
        String sql="UPDATE supplier SET supplierName=?,address=?,contactNumber=?,email=?" +
                "WHERE supplierId=?";

        int affecteRows;

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, supplier.getSupplierName());
            pstm.setString(2, supplier.getAddress());
            pstm.setString(3, supplier.getContact());
            pstm.setString(4, supplier.getEmail());
            pstm.setString(5, supplier.getSupplierId());

            affecteRows=pstm.executeUpdate();

            return  affecteRows > 0;
        }
    }

    public static boolean delete(String supplierId) throws SQLException {
        String sql="DELETE FROM supplier WHERE supplierId=?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, supplierId);

            int affectRows=pstm.executeUpdate();
            return affectRows > 0;
        }

    }

}


