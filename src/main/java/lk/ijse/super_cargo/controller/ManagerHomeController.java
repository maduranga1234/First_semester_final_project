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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.super_cargo.util.ButtonColourController;

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
    private JFXButton MatirialBtn;


    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;

    @FXML
    private Hyperlink LogOutLink;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void LogOutClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/loging.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }


    @FXML
    void HomeManagerBtnClick(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/managerHomePage.fxml"));
        AnchorpaneHome.getChildren().clear();
        ManagerHomeAnchor.getChildren().add(load);
        ButtonColourController.btncolor(HomeManagerBtn,AnchorpaneHome);


    }

    @FXML
    void OrderManagerBtnClick(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/orders.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(OrdersManagerBtn,AnchorpaneHome);

    }

    @FXML
    void PaymentManagerBtnClick(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/payment.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(PaymentManagerBtn,AnchorpaneHome);

    }

    @FXML
    void StockManagerBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/buyer.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(StockManagerBtn,AnchorpaneHome);

    }

    @FXML
    void exportManagerBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/item.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(exportBtnManager,AnchorpaneHome);

    }

    public void MatirialBtnClick(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/matirial.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(MatirialBtn,AnchorpaneHome);


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
