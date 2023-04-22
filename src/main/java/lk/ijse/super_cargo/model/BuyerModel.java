package lk.ijse.super_cargo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.super_cargo.dto.Buyer;
import lk.ijse.super_cargo.dto.Employee;
import lk.ijse.super_cargo.dto.tm.BuyerTm;
import lk.ijse.super_cargo.dto.tm.EmployeeTm;
import lk.ijse.super_cargo.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerModel {

    public static boolean Save(Buyer buyer) throws SQLException {

      String sql= "INSERT INTO buyer(buyerId,userName,buyerName,country,contactNumber,email)" +
              "VALUES (?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                buyer.getBuyerId(),
                buyer.getUserName(),
                buyer.getBuyerName(),
                buyer.getCountry(),
                buyer.getContactNumber(),
                buyer.getEmail()
        );

    }

    public static ObservableList<BuyerTm> getAll() throws SQLException {

        String sql = "SELECT * FROM buyer";

        ObservableList<BuyerTm> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()){

            obList.add(new BuyerTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));

        }
        return obList;
    }


    public static Buyer Search(String buyerId) throws SQLException {

        String sql="SELECT * FROM buyer WHERE buyerId=?";

        ResultSet resultSet=CrudUtil.execute(sql,buyerId);
        if(resultSet.next()){
            return (new Buyer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)


            ));

        }
        return null;
    }


    public static boolean update(Buyer buyer) throws SQLException {
        String sql="UPDATE buyer SET userName=?," +
                "buyerName=?,country=?,contactNumber=?,email=? WHERE buyerId=?";

        return CrudUtil.execute(

                sql,
                buyer.getUserName(),
                buyer.getBuyerName(),
                buyer.getCountry(),
                buyer.getContactNumber(),
                buyer.getEmail(),
                buyer.getBuyerId()
        );

    }


    public static boolean delete(String buyerId) throws SQLException {
        String sql="DELETE FROM buyer WHERE buyerId=?";

        return CrudUtil.execute(
                sql,
                buyerId
        );

    }

    public static List<String>LoadBuyerIds() throws SQLException {

        String sql="SELECT buyerId FROM buyer";
        List<String>allBuyerId=new ArrayList<>();

        ResultSet resultSet=CrudUtil.execute(sql);
        while(resultSet.next()){
            allBuyerId.add(resultSet.getString(1));

        }
        return allBuyerId;
    }

    public static Buyer searchByBuyerId(String id) throws SQLException {
        String sql="SELECT * FROM buyer WHERE buyerId=?";
        ResultSet resultSet=CrudUtil.execute(sql,id);

        if(resultSet.next()){
            return new Buyer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            );

        }

        return null;
    }

    public static int countId() throws SQLException {
        String sql="SELECT COUNT(buyerId) FROM buyer";
        ResultSet resultSet= CrudUtil.execute(sql);
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;

    }
}
