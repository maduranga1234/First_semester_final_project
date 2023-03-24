package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ManagerHomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private AnchorPane ManagerHomeAnchor;

    @FXML
    private JFXButton exportBtnManager;

    @FXML
    private JFXButton OrdersManagerBtn;

    @FXML
    private JFXButton PaymentManagerBtn;

    @FXML
    private JFXButton StockManagerBtn;

    @FXML
    private JFXButton HomeManagerBtn;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;


    @FXML
    void HomeManagerBtnClick(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/managerHomePage.fxml"));
        AnchorpaneHome.getChildren().clear();
        ManagerHomeAnchor.getChildren().add(load);


    }

    @FXML
    void OrderManagerBtnClick(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/orders.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);

    }

    @FXML
    void PaymentManagerBtnClick(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/payment.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);

    }

    @FXML
    void StockManagerBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/buyer.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);

    }

    @FXML
    void exportManagerBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/export.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);

    }
    private void Timenow(){
        Thread thread =new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM,  dd, yyyy");
            while (true){
                try{
                    Thread.sleep(1000);

                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                String timenow1 = sdf1.format(new Date());

                Platform.runLater(() ->{
                    lblTime.setText(timenow);
                    lblDate.setText(timenow1);
                });
            }
        });
        thread.start();
    }


    @FXML
    void initialize() {
        assert exportBtnManager != null : "fx:id=\"exportBtnManager\" was not injected: check your FXML file 'managerHomePage.fxml'.";
        assert OrdersManagerBtn != null : "fx:id=\"OrdersManagerBtn\" was not injected: check your FXML file 'managerHomePage.fxml'.";
        assert PaymentManagerBtn != null : "fx:id=\"PaymentManagerBtn\" was not injected: check your FXML file 'managerHomePage.fxml'.";
        assert StockManagerBtn != null : "fx:id=\"StockManagerBtn\" was not injected: check your FXML file 'managerHomePage.fxml'.";
        Timenow();
    }
}
