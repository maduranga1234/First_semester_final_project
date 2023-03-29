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

public class DashBordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private Label JobTitelLabel;

    @FXML
    private AnchorPane HomeAnchorpane;

    @FXML
    private JFXButton HomeBtn;

    @FXML
    private JFXButton stockBtn;

    @FXML
    private JFXButton exportBtn;

    @FXML
    private JFXButton OrdersBtn;

    @FXML
    private JFXButton SupplierBtn;

    @FXML
    private JFXButton PaymentBtn;

    @FXML
    private JFXButton EmployeeBtn;

    @FXML
    private JFXButton ReportBtn;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;

    @FXML
    private Hyperlink LogOutLink;

    @FXML
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
    void HomeBtnClick(ActionEvent event) throws IOException {

            Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/dashBord.fxml"));
            AnchorpaneHome.getChildren().clear();
            HomeAnchorpane.getChildren().add(load);
            ButtonColourController.btncolor(HomeBtn,HomeAnchorpane);

    }

    @FXML
    void stockBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/buyer.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(stockBtn,AnchorpaneHome);

    }

    @FXML
    void exportBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/item.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(exportBtn,AnchorpaneHome);
    }

    @FXML
    void OrdersBtnClick(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/orders.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(OrdersBtn,AnchorpaneHome);

    }

    @FXML
    void SupplierBtnClick(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/supplier.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(SupplierBtn,AnchorpaneHome);

    }

    @FXML
    void PaymentBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/payment.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(PaymentBtn,AnchorpaneHome);
    }

    @FXML
    void EmployeeBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/employee.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(EmployeeBtn,AnchorpaneHome);
    }
    public void ReportBtnClick(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/report.fxml"));
        AnchorpaneHome.getChildren().clear();
        AnchorpaneHome.getChildren().add(load);
        ButtonColourController.btncolor(ReportBtn,AnchorpaneHome);
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

//  public void SetJobName() {
//
//      LoginController loginController = new LoginController();
//
//  }
//

    @FXML
    void initialize() {

       Timenow();
        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'dashBord.fxml'.";
        assert stockBtn != null : "fx:id=\"stockBtn\" was not injected: check your FXML file 'dashBord.fxml'.";



    }


}
