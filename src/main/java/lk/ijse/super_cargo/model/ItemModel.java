package lk.ijse.super_cargo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.ijse.super_cargo.dto.*;
import lk.ijse.super_cargo.dto.tm.ItemTm;
import lk.ijse.super_cargo.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {


    public static boolean Save(Item item) throws SQLException {
        String sql = "INSERT INTO item(itemId,itemName,weight,unitPrice,quality)" +
                "VALUES (?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                item.getItemId(),
                item.getItemName(),
                item.getWeight(),
                item.getUnitPrice(),
                item.getQuality()
        );
    }

    public static Item Search(String itemId) throws SQLException {
        String sql = "SELECT * FROM item WHERE itemId=?";

        ResultSet resultSet = CrudUtil.execute(sql, itemId);

        if (resultSet.next()) {
            return (new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));

        }
        return null;


    }


    public static boolean Update(Item item) throws SQLException {
        String sql = "UPDATE item SET itemName=?,weight=?,unitPrice=?,quality=?" +
                "WHERE itemId=?";

        return CrudUtil.execute(

                sql,
                item.getItemName(),
                item.getWeight(),
                item.getUnitPrice(),
                item.getQuality(),
                item.getItemId());

    }


    public static boolean delete(String itemId) throws SQLException {
        String sql = "DELETE FROM item WHERE itemId=?";

        return CrudUtil.execute(
                sql,
                itemId
        );

    }


    public static ObservableList<ItemTm> getAll() throws SQLException {

        String sql = "SELECT * FROM item";

        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {

            obList.add(new ItemTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));

        }
        return obList;
    }

    public static List<String> LoadItemCodes() throws SQLException {

        String sql = "SELECT itemId FROM item";
        List<String> allItemData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            allItemData.add(resultSet.getString(1));


        }
        return allItemData;
    }


    public static Item searchByItemCode(String id) throws SQLException {
        String sql = "SELECT * FROM item WHERE itemId=?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)

            );
        }
        return null;
    }

    public static boolean updateQty(List<Order> placeOrderList) throws SQLException {

        for (Order order : placeOrderList) {
            if (!updateQty(order)) {

                return false;
            }

        }
        return true;
    }

    private static boolean updateQty(Order order) throws SQLException {
        String sql = "UPDATE item SET weight=(weight-?)WHERE itemId=?";

        return CrudUtil.execute(
                sql,
                order.getQuantity(),
                order.getItemId()
        );
    }


    public static boolean updateItem(List<SupplierOrderDetail> supplierOrderList) throws SQLException {

        for (SupplierOrderDetail supplierOrderDetail : supplierOrderList) {
            if (!updateItem1(supplierOrderDetail)) {

                return false;
            }

        }
        return true;
    }

    private static boolean updateItem1(SupplierOrderDetail supplierOrderDetail) throws SQLException {
        String sql = "UPDATE item SET weight=(weight+?)WHERE itemId=?";

        return CrudUtil.execute(
                sql,
                supplierOrderDetail.getQnt(),
                supplierOrderDetail.getItemId()
        );

    }




    public static ObservableList<XYChart.Series<String, Double>> getDataToBarChart() throws SQLException {
        String sql="SELECT itemName,weight FROM item WHERE weight<=10 ";

        ObservableList<XYChart.Series<String, Double>> datalist =FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute(sql);

        // Creating a new series object
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        while(resultSet.next()){
            String itemName = resultSet.getString("itemName");
            Double weight = Double.valueOf(resultSet.getInt("weight"));
            series.getData().add(new XYChart.Data<>(itemName, weight));
        }

        datalist.add(series);
        return datalist;
}


}













