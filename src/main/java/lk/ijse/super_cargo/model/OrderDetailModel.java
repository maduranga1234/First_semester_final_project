package lk.ijse.super_cargo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import lk.ijse.super_cargo.dto.Order;
import lk.ijse.super_cargo.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailModel {

    public static boolean Save(String orderid, List<Order> placeOrderList) throws SQLException {

        for(Order order :placeOrderList){
            if(!Save(orderid,order)){
                return false;

            }
        }
        return true;
    }

    public static boolean Save(String orderId,Order order) throws SQLException {
        String sql="INSERT INTO ordersdetail(orderId,itemId,weight)" +
                "VALUES(?,?,?)";

        return CrudUtil.execute(
                sql,
                orderId,
                order.getItemId(),
                order.getQuantity()

        );
    }

    public static ObservableList<PieChart.Data> getDataToPieChart() throws SQLException {
        String sql="SELECT item.itemName,COUNT(ordersdetail.itemId) "+
                "FROM ordersdetail "+
                "INNER JOIN item "+
                "ON item.itemId = ordersdetail.itemId " +
                "INNER JOIN orders " +
                "ON ordersdetail.orderId=orders.orderId " +
                "WHERE MONTH(orders.orderDate) = MONTH(CURRENT_DATE()) " +
                "GROUP BY item.itemName " +
                "LIMIT 5 ";
        ObservableList<PieChart.Data> datalist = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute(sql);

        while(resultSet.next()){
            datalist.add(
                    new PieChart.Data(
                            resultSet.getString(1),
                            resultSet.getInt(2)
                    )
            );
        }
        return datalist;
    }
}
