package lk.ijse.super_cargo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.super_cargo.db.DBConnection;
import lk.ijse.super_cargo.dto.Item;
import lk.ijse.super_cargo.dto.Matirial;
import lk.ijse.super_cargo.dto.tm.ItemTm;
import lk.ijse.super_cargo.dto.tm.MatirialTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatirialModel {




    public static boolean Save(Matirial matirial) throws SQLException {
        String sql="INSERT INTO matirial(matirialId,matirialName,weight,Price,quality)" +
                "VALUES (?,?,?,?,?)";

        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,matirial.getMatirialId());
        pstm.setString(2,matirial.getMatirialName());
        pstm.setDouble(3,matirial.getWeight());
        pstm.setDouble(4,matirial.getPrice());
        pstm.setString(5,matirial.getQuality());


        int affecteRows=pstm.executeUpdate();
        return affecteRows > 0;

    }

    public static boolean Update(Matirial matirial) throws SQLException {
        String sql="UPDATE matirial SET matirialName=?,weight=?,Price=?,quality=?" +
                "WHERE matirialId=?";

        int affecteRows;

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, matirial.getMatirialName());
            pstm.setDouble(2,matirial.getWeight());
            pstm.setDouble(3,matirial.getPrice());
            pstm.setString(4,matirial.getQuality());
            pstm.setString(5,matirial.getMatirialId());


            affecteRows=pstm.executeUpdate();

            return  affecteRows > 0;
        }
    }



    public static boolean delete(String matirialId) throws SQLException {
        String sql="DELETE FROM matirial WHERE matirialId=?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, matirialId);

            int affectRows=pstm.executeUpdate();
            return affectRows > 0;
        }

    }


    public static Matirial Search(String matirialId) throws SQLException {
        String sql = "SELECT * FROM matirial WHERE matirialId=?";

        ResultSet resultSet;

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,matirialId);

            resultSet = pstm.executeQuery();


            if (resultSet.next()) {
                return new Matirial(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                );

            }

        }return null;
    }






    public static ObservableList<MatirialTm> getAll() throws SQLException {

        String sql = "SELECT * FROM matirial";


        ResultSet resultSet;
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            resultSet=pstm.executeQuery();


            ObservableList<MatirialTm>matirialData= FXCollections.observableArrayList();

            while (resultSet.next()) {

                matirialData.add(new MatirialTm(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                ));

            } return matirialData;

        }
    }


}
