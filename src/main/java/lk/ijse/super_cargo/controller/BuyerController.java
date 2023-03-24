package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class BuyerController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private JFXTextField BuyerIdText;

    @FXML
    private JFXTextField BuyerNameText;

    @FXML
    private JFXTextField CountryText;

    @FXML
    private JFXTextField EmailText;

    @FXML
    private JFXTextField NumberText;

    @FXML
    private JFXButton BuyerUpdateBtn;

    @FXML
    private JFXButton BuyerSaveBtn;

    @FXML
    private JFXButton BuyerDeleteBtn;

    @FXML
    void DeleteClick(ActionEvent event) {

    }

    @FXML
    void SaveClick(ActionEvent event) {

    }

    @FXML
    void UpdateClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'buyer.fxml'.";
        assert BuyerIdText != null : "fx:id=\"BuyerIdText\" was not injected: check your FXML file 'buyer.fxml'.";
        assert BuyerNameText != null : "fx:id=\"BuyerNameText\" was not injected: check your FXML file 'buyer.fxml'.";
        assert CountryText != null : "fx:id=\"CountryText\" was not injected: check your FXML file 'buyer.fxml'.";
        assert EmailText != null : "fx:id=\"EmailText\" was not injected: check your FXML file 'buyer.fxml'.";
        assert NumberText != null : "fx:id=\"NumberText\" was not injected: check your FXML file 'buyer.fxml'.";
        assert BuyerUpdateBtn != null : "fx:id=\"BuyerUpdateBtn\" was not injected: check your FXML file 'buyer.fxml'.";
        assert BuyerSaveBtn != null : "fx:id=\"BuyerSaveBtn\" was not injected: check your FXML file 'buyer.fxml'.";
        assert BuyerDeleteBtn != null : "fx:id=\"BuyerDeleteBtn\" was not injected: check your FXML file 'buyer.fxml'.";

    }
}
