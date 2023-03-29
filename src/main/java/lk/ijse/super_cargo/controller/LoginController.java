package lk.ijse.super_cargo.controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import lk.ijse.super_cargo.dto.User;
import lk.ijse.super_cargo.model.UserModel;
import lk.ijse.super_cargo.util.ButtonColourController;

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
    private TextField userNameTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Label SignUpBtn;

    @FXML
    private Hyperlink SingUpClick;

    @FXML
    private Hyperlink Goodleicon;

    @FXML
    private Hyperlink Facebookicon;

    @FXML
    private Hyperlink ForgotPasswordBtn;
    @FXML
    private JFXPasswordField PasswordText;




    @FXML
    void initialize() {

        assert loginAncPane != null : "fx:id=\"loginAncPane\" was not injected: check your FXML file 'loginform.fxml'.";
        logincombo.getItems().addAll("Owner", "Manager");
    }

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    User logingCont=new User();

    @FXML
    void comboboxLogingOnAction(ActionEvent event){
        logingCont.setJobTitel((String) logincombo.getValue());
    }

    public void LoginOnAction(ActionEvent event) throws IOException {

        ButtonColourController.btncolor(btn1,loginAnchorPane);

        logingCont.setUserName(userNameTxt.getText());
        logingCont.setPassword(PasswordText.getText());

        try {
            User user= UserModel.LogingAction(logingCont);
            String userName=user.getUserName();
            String password=user.getPassword();
            String jobTitel=user.getJobTitel();

            if(userName.equals(logingCont.getUserName()) && password.equals(logingCont.getPassword()) && jobTitel.equals(logingCont.getJobTitel()) && jobTitel.equals("Owner")){
                Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/dashBord.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            }
            else if(userName.equals(logingCont.getUserName()) && password.equals(logingCont.getPassword()) && jobTitel.equals(logingCont.getJobTitel()) && jobTitel.equals("Manager")){
                Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/managerHomePage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();


            }else{
                new Alert(Alert.AlertType.ERROR,"invalid login details").show();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }








    @FXML
    void FacebookIcon(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.facebook.com/profile.php?id=100067319348809"));

    }

    @FXML
    void GoogleIcon(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("http://www.easternallied.com/"));

    }



    @FXML
    void SignUpClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/signup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void ForgotPasswordClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/frogetPassWord.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();


    }
}



