package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.super_cargo.dto.User;
import lk.ijse.super_cargo.model.UserModel;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private TextField userNameTxt;

    @FXML
    private JFXButton btn2;

    @FXML
    private JFXPasswordField Passwordtext;

    @FXML
    private JFXPasswordField ComPasswordText;

    @FXML
    private TextField EmailTaxt;

    @FXML
    private TextField JobText;

    @FXML
    private TextField IdText;

    @FXML
    private ComboBox JobCombo;


    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void SignUpClick(ActionEvent event) throws IOException {

        String password=Passwordtext.getText();
        String ComPassword=ComPasswordText.getText();
        String Job= (String) JobCombo.getValue();

      if(password.equals(ComPassword) && Job.equals("Owner")) {

            User user = new User();

            user.setEmail(EmailTaxt.getText());
            user.setUserName(userNameTxt.getText());
            user.setEmployeeId(IdText.getText());
            user.setJobTitel((String) JobCombo.getValue());
            user.setPassword(Passwordtext.getText());
            user.setComformPassword(ComPasswordText.getText());

            try {
                boolean isSingUp = UserModel.SingUp(user);
                if (isSingUp) {
                    new Alert(Alert.AlertType.CONFIRMATION, "saved").show();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }



          Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/dashBord.fxml"));
          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.centerOnScreen();
          stage.show();



        }


      else if(password.equals(ComPassword) && Job.equals("Manager")){
          User user = new User();

          user.setEmail(EmailTaxt.getText());
          user.setUserName(userNameTxt.getText());
          user.setEmployeeId(IdText.getText());
          user.setJobTitel((String) JobCombo.getValue());
          user.setPassword(Passwordtext.getText());
          user.setComformPassword(ComPasswordText.getText());

          try {
              boolean isSingUp = UserModel.SingUp(user);
              if (isSingUp) {
                  new Alert(Alert.AlertType.CONFIRMATION, "saved").show();
              }

          } catch (SQLException throwables) {
              throwables.printStackTrace();
              new Alert(Alert.AlertType.ERROR, "Error").show();
          }



          Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/managerHomePage.fxml"));
          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.centerOnScreen();
          stage.show();

      }
    }

    @FXML
    void initialize() {
        assert loginAnchorPane != null : "fx:id=\"loginAnchorPane\" was not injected: check your FXML file 'signup.fxml'.";
        assert userNameTxt != null : "fx:id=\"userNameTxt\" was not injected: check your FXML file 'signup.fxml'.";
        assert btn2 != null : "fx:id=\"btn2\" was not injected: check your FXML file 'signup.fxml'.";
        assert Passwordtext != null : "fx:id=\"Passwordtext\" was not injected: check your FXML file 'signup.fxml'.";
        assert ComPasswordText != null : "fx:id=\"ComPasswordText\" was not injected: check your FXML file 'signup.fxml'.";
        assert EmailTaxt != null : "fx:id=\"EmailTaxt\" was not injected: check your FXML file 'signup.fxml'.";
        assert JobText != null : "fx:id=\"JobText\" was not injected: check your FXML file 'signup.fxml'.";
        assert IdText != null : "fx:id=\"IdText\" was not injected: check your FXML file 'signup.fxml'.";
        JobCombo.getItems().addAll("Owner","Manager");
    }
}
