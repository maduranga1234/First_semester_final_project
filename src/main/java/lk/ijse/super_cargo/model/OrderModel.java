package lk.ijse.super_cargo.model;

import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Order;
import lk.ijse.super_cargo.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderModel {

    public static String getNextOrderId() throws SQLException {
        String sql="SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";

        ResultSet resultSet= CrudUtil.execute(sql);
         if(resultSet.next()){

             return splitOrder(resultSet.getString(1));

         }
         return splitOrder(null );
    }

    private static String splitOrder(String currentId){
        if(currentId != null){

            String[]strings=currentId.split("ORD-");
            int id=Integer.parseInt(strings[1]);
            ++id;

            String digit=String.format("%03d",id);
            return "ORD-"+digit;

        }
        return "ORD-"+"001";
    }

    private static boolean save(String orderid, String buyerid, LocalTime time, LocalDate date, String ordpay) throws SQLException {
        String sql = "INSERT INTO orders(orderId,buyerId,time,orderDate,payment) VALUES(?,?,?,?,?)";

        return  CrudUtil.execute(
                sql,
                orderid,
                buyerid,
                time,
                date,
                ordpay
        );
    }



    public static boolean placeOrder(String orderid, String buyerid, String ordpay, List<Order> placeOrderList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = save(orderid,buyerid, LocalTime.now(), LocalDate.now(),ordpay);
            if(isSaved) {
                System.out.println("isSaved");
                boolean isUpdated = ItemModel.updateQty(placeOrderList);

                if(isUpdated) {
                    System.out.println("isUpdated");
                    boolean isOrdered = OrderDetailModel.Save(orderid,placeOrderList);
                    if(isOrdered) {
                        System.out.println("isOrdered");

                            con.commit();
                            return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    public static int countIncome() throws SQLException {
        String sql="SELECT SUM(payment) FROM Orders WHERE orderDate=DATE(NOW())";
        ResultSet resultSet=CrudUtil.execute(sql);
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;
}

    public static int countId() throws SQLException {
        String sql="SELECT COUNT(orderId) FROM orders";
        ResultSet resultSet= CrudUtil.execute(sql);
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;

    }
}
