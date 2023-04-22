package lk.ijse.super_cargo.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.super_cargo.model.BuyerModel;
import lk.ijse.super_cargo.model.ItemModel;
import lk.ijse.super_cargo.model.OrderDetailModel;
import lk.ijse.super_cargo.model.OrderModel;

public class OwnerHomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<String,Double> barChart;

    @FXML
    private Label CustomerLbl;

    @FXML
    private Label OrdersLbl;

    @FXML
    private Label IncomeLbl;


    public void setDataToPieChart() throws SQLException {
        ObservableList<PieChart.Data> pieChartData = OrderDetailModel.getDataToPieChart();

        pieChart.setData(pieChartData);
    }

    public void setDataToBarChart() throws SQLException {
        ObservableList<XYChart.Series<String, Double>> barChartData = ItemModel.getDataToBarChart();

        barChart.setData(barChartData);
}

    void countcustomer(){
        try {
            int count= BuyerModel.countId();
            CustomerLbl.setText(String.valueOf("0"+count));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void countTodayIncome(){
        try {
            int count= OrderModel.countIncome();
            IncomeLbl.setText(String.valueOf(count));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
}
}

    void countOrders(){
        try {
            int count= OrderModel.countId();
            OrdersLbl.setText(String.valueOf(0+count));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @FXML
    void initialize() throws SQLException {

        setDataToBarChart();
        setDataToPieChart();
        countcustomer();
        countOrders();
        countTodayIncome();
        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'ownerHome.fxml'.";
        assert pieChart != null : "fx:id=\"pieChart\" was not injected: check your FXML file 'ownerHome.fxml'.";
        assert barChart != null : "fx:id=\"BarChart\" was not injected: check your FXML file 'ownerHome.fxml'.";

    }
}
