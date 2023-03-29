package lk.ijse.super_cargo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Item;
import lk.ijse.super_cargo.dto.Supplier;
import lk.ijse.super_cargo.dto.tm.ItemTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {



    public static boolean Save(Item item) throws SQLException {
        String sql="INSERT INTO item(itemId,itemName,weight,unitPrice,quality)" +
                "VALUES (?,?,?,?,?)";

        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,item.getItemId());
        pstm.setString(2,item.getItemName());
        pstm.setDouble(3,item.getWeight());
        pstm.setDouble(4,item.getUnitPrice());
        pstm.setString(5,item.getQuality());

        int affecteRows=pstm.executeUpdate();
        return affecteRows > 0;

    }

    public static Item Search(String itemId) throws SQLException {
        String sql = "SELECT * FROM item WHERE itemId=?";

        ResultSet resultSet;

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, itemId);

            resultSet = pstm.executeQuery();


            if (resultSet.next()) {
                return new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                );

            }

        }return null;
    }


    public static boolean Update(Item item) throws SQLException {
        String sql="UPDATE item SET itemName=?,weight=?,unitPrice=?,quality=?" +
                "WHERE itemId=?";

        int affecteRows;

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, item.getItemName());
            pstm.setDouble(2, item.getWeight());
            pstm.setDouble(3, item.getUnitPrice());
            pstm.setString(4,  item.getQuality());
            pstm.setString(5, item.getItemId());

            affecteRows=pstm.executeUpdate();

            return  affecteRows > 0;
        }
    }


    public static boolean delete(String itemId) throws SQLException {
        String sql="DELETE FROM item WHERE itemId=?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, itemId);

            int affectRows=pstm.executeUpdate();
            return affectRows > 0;
        }

    }


    public static ObservableList<ItemTm> getAll() throws SQLException {

        String sql = "SELECT * FROM item";


        ResultSet resultSet;
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            resultSet=pstm.executeQuery();


            ObservableList<ItemTm>itemData= FXCollections.observableArrayList();

            while (resultSet.next()) {

                     itemData.add(new ItemTm(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)

                ));

            } return itemData;

        }
    }
}













