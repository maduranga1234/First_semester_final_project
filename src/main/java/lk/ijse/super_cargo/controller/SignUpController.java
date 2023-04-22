package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lk.ijse.super_cargo.model.EmployeeModel;
import lk.ijse.super_cargo.model.UserModel;
import lk.ijse.super_cargo.util.AlertController;
import lk.ijse.super_cargo.util.ValidationController;

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
    private ComboBox EmployeeIdBox;


    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void SignUpClick(ActionEvent event) throws IOException {

        String password=Passwordtext.getText();
        String ComPassword=ComPasswordText.getText();
        String Job= (String) JobCombo.getValue();

        if(ValidationController.emailCheck(EmailTaxt.getText())) {
            if (ValidationController.customerNameValidate(userNameTxt.getText())) {
                if(!EmployeeIdBox.getSelectionModel().isEmpty()) {
                    if (!JobCombo.getSelectionModel().isEmpty()) {
                        if (ValidationController.Password(Passwordtext.getText())) {
                            if (ValidationController.Password(ComPasswordText.getText())) {

                                if (password.equals(ComPassword) && Job.equals("Owner")) {

                                    User user = new User();

                                    user.setEmail(EmailTaxt.getText());
                                    user.setUserName(userNameTxt.getText());
                                    user.setEmployeeId(String.valueOf(EmployeeIdBox.getValue()));
                                    user.setJobTitel((String) JobCombo.getValue());
                                    user.setPassword(Passwordtext.getText());

                                    try {
                                        boolean isSingUp = UserModel.SingUp(user);

                                        if (isSingUp) {
                                            new Alert(Alert.AlertType.CONFIRMATION, "saved").show();
                                            AlertController.animationMesseage("lk.ijse.super_cargo.image/tick.gif", "Sing Up ", "Sing Up Successful");


                                            Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/loging.fxml"));
                                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                            scene = new Scene(root);
                                            stage.setScene(scene);
                                            stage.centerOnScreen();
                                            stage.show();
                                        }


                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                        new Alert(Alert.AlertType.ERROR, "Error").show();
                                        AlertController.animationMesseage("lk.ijse.super_cargo.image/wrongicon.png", "Sing Up ", "Sing Up Unsuccessful");
                                    }


                                } else if (password.equals(ComPassword) && Job.equals("Manager")) {
                                    User user = new User();

                                    user.setEmail(EmailTaxt.getText());
                                    user.setUserName(userNameTxt.getText());
                                    user.setEmployeeId(String.valueOf(EmployeeIdBox.getValue()));
                                    user.setJobTitel((String) JobCombo.getValue());
                                    user.setPassword(Passwordtext.getText());

                                    try {
                                        boolean isSingUp = UserModel.SingUp(user);
                                        if (isSingUp) {
                                            new Alert(Alert.AlertType.CONFIRMATION, "saved").show();
                                            AlertController.animationMesseage("lk.ijse.super_cargo.image/tick.gif", "Sing Up ", "Sing Up Successful");
                                            Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/loging.fxml"));
                                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                            scene = new Scene(root);
                                            stage.setScene(scene);
                                            stage.centerOnScreen();
                                            stage.show();
                                        }

                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                        new Alert(Alert.AlertType.ERROR, "Error").show();
                                        AlertController.animationMesseage("lk.ijse.super_cargo.image/wrongicon.png", "Sing Up ", "Sing Up Unsuccessful");
                                    }
                                }
                            }else{
                                AlertController.errormessage("invalied Confirm Password");

                            }
                        }else{
                            AlertController.errormessage("invalied Password");

                        }
                    }else{
                        AlertController.errormessage("Please Select Job Titel");

                    }
                }else{
                    AlertController.errormessage("Please Select Employee Id");

                }

            }else {
                AlertController.errormessage("invalied User Name");

            }
        }
        else{
            AlertController.errormessage("invalied Email");
        }




    }

    @FXML
    void initialize() {
        LoadEmployeeId();
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
    private void LoadEmployeeId(){

        try{
            ObservableList<String> employeeIds= FXCollections.observableArrayList();
            List<String> ids = EmployeeModel.LoadEmployeeIds();

            for(String id:ids) {
                employeeIds.add(id);

            }
            EmployeeIdBox.setItems(employeeIds);

        }catch (SQLException throwables){
            AlertController.errormessage("Something went Wrong");
        }
    }

    public void BackClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/loging.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
