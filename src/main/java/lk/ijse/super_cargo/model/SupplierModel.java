package lk.ijse.super_cargo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Employee;
import lk.ijse.super_cargo.dto.Item;
import lk.ijse.super_cargo.dto.Supplier;
import lk.ijse.super_cargo.dto.tm.ItemTm;
import lk.ijse.super_cargo.dto.tm.SupplierTm;
import lk.ijse.super_cargo.util.CrudUtil;

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

        return CrudUtil.execute(
                sql,
               supplier.getSupplierId(),
                supplier.getSupplierName(),
                supplier.getAddress(),
                supplier.getContact(),
                supplier.getEmail()
        );
    }

    public static Supplier Search(String supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplierId=?";

        ResultSet resultSet=CrudUtil.execute(sql,supplierId);

        if(resultSet.next()){
            return (new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));

        }
        return null;
    }

    public static ObservableList<SupplierTm> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";


        ObservableList <SupplierTm> obList=FXCollections.observableArrayList();

        ResultSet resultSet=CrudUtil.execute(sql);
        while (resultSet.next()){

            obList.add(new SupplierTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));

        }
        return obList;

    }

    public static boolean Update(Supplier supplier) throws SQLException {
        String sql="UPDATE supplier SET supplierName=?,address=?,contactNumber=?,email=?" +
                "WHERE supplierId=?";

        return CrudUtil.execute(

                sql,

               supplier.getSupplierName(),
               supplier.getAddress(),
               supplier.getContact(),
               supplier.getEmail(),
        supplier.getSupplierId());
    }

    public static boolean delete(String supplierId) throws SQLException {
        String sql="DELETE FROM supplier WHERE supplierId=?";

        return CrudUtil.execute(
                sql,
                supplierId
        );

    }

    public static List<String> LoadSupplierIds() throws SQLException {
        String sql="SELECT supplierId FROM supplier";
        List<String>allSupplierId=new ArrayList<>();

        ResultSet resultSet=CrudUtil.execute(sql);
        while(resultSet.next()){
            allSupplierId.add(resultSet.getString(1));

        }
        return allSupplierId;
    }

    public static Supplier searchBySupplierId(String id) throws SQLException {
        String sql="SELECT * FROM supplier WHERE supplierId=?";
        ResultSet resultSet=CrudUtil.execute(sql,id);

        if(resultSet.next()){
            return new Supplier(
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


