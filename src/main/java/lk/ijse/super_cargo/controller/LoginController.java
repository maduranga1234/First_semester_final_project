package lk.ijse.super_cargo.controller;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lk.ijse.super_cargo.dto.User;
import lk.ijse.super_cargo.model.UserModel;
import lk.ijse.super_cargo.util.AlertController;
import lk.ijse.super_cargo.util.ButtonColourController;
import lk.ijse.super_cargo.util.Navigation;

public class LoginController {

    @FXML
    public ResourceBundle resources;


    @FXML
    public AnchorPane loginAncPane;
    @FXML
    private JFXButton btn1;



    @FXML
    private ComboBox logincombo;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private TextField userNameTxt;

    @FXML
    public Hyperlink SingUpClick;

    @FXML
    public Hyperlink Goodleicon;

    @FXML
    public Hyperlink Facebookicon;

    @FXML
    public Hyperlink ForgotPasswordBtn;
    @FXML
    private JFXPasswordField PasswordText;

    @FXML
    private Group visibleGroup;

    @FXML
    private TextField visibleText;

    @FXML
    private Group passwordGroup;



    @FXML
    void initialize() {

        assert loginAncPane != null : "fx:id=\"loginAncPane\" was not injected: check your FXML file 'loginform.fxml'.";
        logincombo.getItems().addAll("Owner", "Manager");
    }

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    User user =new User();

    @FXML
    void comboboxLogingOnAction(ActionEvent event){
        user.setJobTitel((String) logincombo.getValue());
    }

    public void LoginOnAction(ActionEvent event) throws IOException {

        ButtonColourController.btncolor(btn1,loginAnchorPane);

        user.setUserName(userNameTxt.getText());
        user.setPassword(PasswordText.getText());
        user.setJobTitel(String.valueOf(logincombo.getValue()));
        try {

            if(logincombo.getSelectionModel().getSelectedIndex() == -1 || userNameTxt.getText().isEmpty() || PasswordText.getText().isEmpty()){
                new Alert(Alert.AlertType.ERROR,"invalid Owner login details").show();


            }
            else{
                if (logincombo.getValue().equals("Owner")){
                    if (UserModel.LogingAction(user)){
                        AlertController.animationMesseage("lk.ijse.super_cargo.image/tick.gif", "Loging ","Loging Successful");
                        Navigation.swishNavigation(event,"dashBord.fxml");
//                   Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/dashBord.fxml"));
//                   stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                   scene = new Scene(root);
//                   stage.setScene(scene);
//                   stage.centerOnScreen();
//                   stage.show();
                    }
                }
                if (logincombo.getValue().equals("Manager")){
                    if (UserModel.LogingAction(user)){
                        AlertController.animationMesseage("lk.ijse.super_cargo.image/tick.gif", "Loging ","Loging Successful");
                        Navigation.swishNavigation(event,"managerHomePage.fxml");
                    }
//
//               Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/managerHomePage.fxml"));
//                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.centerOnScreen();
//                stage.show();
                }

                if (!UserModel.LogingAction(user)){
                    new Alert(Alert.AlertType.ERROR,"invalid Owner login details").show();


                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


//        try {
//            User user= UserModel.LogingAction(logingCont);
//            String userName=user.getUserName();
//            String password=user.getPassword();
//            String jobTitel=user.getJobTitel();
//
//            if(userName.equals(logingCont.getUserName()) && password.equals(logingCont.getPassword()) && jobTitel.equals(logingCont.getJobTitel()) && jobTitel.equals("Owner")){
//                AlertController.animationMesseage("lk.ijse.super_cargo.image/tick.gif", "Loging ","Loging Successful");
//                Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/dashBord.fxml"));
//                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.centerOnScreen();
//                stage.show();
//            }
//            else if(userName.equals(logingCont.getUserName()) && password.equals(logingCont.getPassword()) && jobTitel.equals(logingCont.getJobTitel()) && jobTitel.equals("Manager")){
//                AlertController.animationMesseage("lk.ijse.super_cargo.image/tick.gif", "Loging ","Loging Successful");
//                Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/managerHomePage.fxml"));
//                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.centerOnScreen();
//                stage.show();
//
//
//            }else{
//                new Alert(Alert.AlertType.ERROR,"invalid login details").show();
//                PasswordText.setStyle("-fx-background-color:#ff3131");
//                userNameTxt.setStyle("-fx-background-color:#ff2d2d");
//                logincombo.setStyle("-fx-background-color:#ff2c2c");
//                passwordTxt.setStyle("-fx-background-radius:16");
//                AlertController.animationMesseage("lk.ijse.super_cargo.image/wrongicon.png", "Loging ","Loging Unsuccessful");
//            }
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }


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

    public void visableMouse(MouseEvent mouseEvent) {
        visibleGroup.setVisible(false);
        passwordGroup.setVisible(true);


    }

    public void disableMouse(MouseEvent mouseEvent) {

        visibleGroup.setVisible(true);
        visibleText.setText(PasswordText.getText());
        passwordGroup.setVisible(false);


    }
}



