package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SupplierController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorpaneHome;

    @FXML
    private JFXTextField SupIdText;

    @FXML
    private JFXTextField SupNameText;

    @FXML
    private JFXTextField SupAddressText1;

    @FXML
    private JFXTextField SuoContactText2;

    @FXML
    private JFXTextField SupEmailText3;

    @FXML
    private JFXButton SupUpdateBtn;

    @FXML
    private JFXButton SupSaveBtn;

    @FXML
    private JFXButton SupDeleteBtn;

    @FXML
    void SupDeleteClick(ActionEvent event) {

    }

    @FXML
    void SupSaveClick(ActionEvent event) {

    }

    @FXML
    void SupUpdateClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert AnchorpaneHome != null : "fx:id=\"AnchorpaneHome\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupIdText != null : "fx:id=\"SupIdText\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupNameText != null : "fx:id=\"SupNameText\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupAddressText1 != null : "fx:id=\"SupAddressText1\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SuoContactText2 != null : "fx:id=\"SuoContactText2\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupEmailText3 != null : "fx:id=\"SupEmailText3\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupUpdateBtn != null : "fx:id=\"SupUpdateBtn\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupSaveBtn != null : "fx:id=\"SupSaveBtn\" was not injected: check your FXML file 'supplier.fxml'.";
        assert SupDeleteBtn != null : "fx:id=\"SupDeleteBtn\" was not injected: check your FXML file 'supplier.fxml'.";

    }
}
