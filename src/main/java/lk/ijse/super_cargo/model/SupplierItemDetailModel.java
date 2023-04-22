package lk.ijse.super_cargo.model;

import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Order;
import lk.ijse.super_cargo.dto.SupplierOrderDetail;
import lk.ijse.super_cargo.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SupplierItemDetailModel {


    public static String getNextLoadId() throws SQLException {
        String sql="SELECT LoadId FROM supplieritemdetail ORDER BY LoadId DESC LIMIT 1";

        ResultSet resultSet= CrudUtil.execute(sql);
        if(resultSet.next()){

            return splitOrder(resultSet.getString(1));

        }
        return splitOrder(null );
    }

    private static String splitOrder(String currentId){
        if(currentId != null){

            String[]strings=currentId.split("LOAD-");
            int id=Integer.parseInt(strings[1]);
            ++id;

            String digit=String.format("%03d",id);
            return "LOAD-"+digit;

        }
        return "LOAD-"+"001";
    }

    public static boolean SupplierLoad(String supplierLoadId, String supplierId, String loadPayment, List<SupplierOrderDetail> supplierOrderList) throws SQLException {


        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = save(supplierLoadId,supplierId, LocalTime.now(), LocalDate.now(),loadPayment,supplierOrderList);
            if(isSaved) {
                System.out.println("isSaved");
                boolean isUpdated = ItemModel.updateItem(supplierOrderList);

                if(isUpdated) {
                    System.out.println("isUpdated");


                        con.commit();
                        return true;
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

    private static boolean save(String supplierLoadId, String supplierId, LocalTime now, LocalDate now1, String loadPayment, List<SupplierOrderDetail> supplierOrderList) throws SQLException {

        for(SupplierOrderDetail supplierOrderDetail :supplierOrderList){
            if(!Save(supplierLoadId,supplierId,loadPayment,now,now1,supplierOrderDetail)){
                return false;

            }
        }
        return true;

    }

    private static boolean Save(String supplierLoadId, String supplierId, String loadPayment, LocalTime now, LocalDate now1, SupplierOrderDetail supplierOrderDetail) throws SQLException {

        String sql="INSERT INTO supplieritemdetail(LoadId,itemId,supplierId,time,date,price,qnt)" +
                "VALUES(?,?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                supplierLoadId,
                supplierOrderDetail.getItemId(),
                supplierId,
                now,
                now1,
                loadPayment,
                supplierOrderDetail.getQnt()


        );
    }
    }



