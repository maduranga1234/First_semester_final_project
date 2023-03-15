package lk.ijse.super_cargo.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private ResourceBundle resources;


    @FXML
    private AnchorPane loginAncPane;
    @FXML
    private JFXButton btn1;

    @FXML
    private Label loginlbl;

    @FXML
    private ComboBox logincombo;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField userNameTxt;

    @FXML
    private TextField passwordTxt;



    @FXML
    void initialize() {

        assert loginAncPane != null : "fx:id=\"loginAncPane\" was not injected: check your FXML file 'loginform.fxml'.";
        logincombo.getItems().addAll("Admin", "Manager");
    }

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void LoginOnAction(ActionEvent event) throws IOException {




            Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/dashBord.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

}
    }



