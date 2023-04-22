package lk.ijse.super_cargo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Item;
import lk.ijse.super_cargo.dto.Matirial;
import lk.ijse.super_cargo.dto.Supplier;
import lk.ijse.super_cargo.dto.tm.ItemTm;
import lk.ijse.super_cargo.dto.tm.MatirialTm;
import lk.ijse.super_cargo.dto.tm.SupplierTm;
import lk.ijse.super_cargo.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatirialModel {




    public static boolean Save(Matirial matirial) throws SQLException {
        String sql="INSERT INTO matirial(matirialId,supplierId,matirialName,weight,Price,quality)" +
                "VALUES (?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                matirial.getMatirialId(),
                matirial.getSupplierId(),
                matirial.getMatirialName(),
                matirial.getWeight(),
                matirial.getPrice(),
                matirial.getQuality()
        );

    }

    public static boolean Update(Matirial matirial) throws SQLException {
        String sql="UPDATE matirial SET supplierId=?,matirialName=?,weight=?,Price=?,quality=?" +
                "WHERE matirialId=?";

        return CrudUtil.execute(

                sql,


                matirial.getSupplierId(),
                matirial.getMatirialName(),
                matirial.getWeight(),
                matirial.getPrice(),
                matirial.getQuality(),
                matirial.getMatirialId());
    }



    public static boolean delete(String matirialId) throws SQLException {
        String sql="DELETE FROM matirial WHERE matirialId=?";

        return CrudUtil.execute(
                sql,
                matirialId
        );

    }


    public static Matirial Search(String matirialId) throws SQLException {
        String sql = "SELECT * FROM matirial WHERE matirialId=?";

        ResultSet resultSet=CrudUtil.execute(sql,matirialId);

        if(resultSet.next()){
            return (new Matirial(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)

            ));

        }
        return null;
    }

    public static ObservableList<MatirialTm> getAll() throws SQLException {

        String sql = "SELECT * FROM matirial";

        ObservableList <MatirialTm> obList=FXCollections.observableArrayList();

        ResultSet resultSet=CrudUtil.execute(sql);
        while (resultSet.next()){

            obList.add(new MatirialTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)
            ));
        }
        return obList;
    }
}
