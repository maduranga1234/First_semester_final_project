package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;

import com.jfoenix.controls.JFXPasswordField;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.super_cargo.dto.User;
import lk.ijse.super_cargo.model.UserModel;
import lk.ijse.super_cargo.util.AlertController;
import lk.ijse.super_cargo.util.EmailController;
import lk.ijse.super_cargo.util.ValidationController;

import javax.mail.MessagingException;

public class ForgetPasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EmailSentText;

    @FXML
    private JFXButton EmailSent;

    @FXML
    private TextField OtpCodeText;

    @FXML
    private JFXButton OtpCodeBtn;

    @FXML
    private JFXButton NewPassWordBtn;

    @FXML
    private AnchorPane BigAnchorpane;

    @FXML
    private AnchorPane SmallAnchorpane;

    @FXML
    private Label TimerLabel;

    @FXML
    private JFXButton ResendOtpBtn;

    @FXML
    private TextField userNameText;
    @FXML
    private Group Group1;
    @FXML
    private Group Group2;

    @FXML
    private Group Group3;


    @FXML
    private JFXPasswordField FirstPasswordText;

    @FXML
    private JFXPasswordField ComPasswordText;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Timeline timeline;
    private SimpleIntegerProperty timeSeconds=new SimpleIntegerProperty();
    private final int START_TIME=30;

    Random rand = new Random();

    public void timermethod(Label label){
        ResendOtpBtn.setVisible(false);
        timeSeconds.set(START_TIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START_TIME+1),
                new KeyValue(timeSeconds, 0)));
        timeline.setOnFinished(event -> {
            TimerLabel.setVisible(false);
            ResendOtpBtn.setVisible(true);
        });
        timeline.playFromStart();
        label.textProperty().bind(timeSeconds.asString());
}




String email;
    int randomnum;
    @FXML
    void EmailSentClick(ActionEvent event) throws MessagingException, SQLException {


        if(ValidationController.emailCheck(EmailSentText.getText())) {
            if (ValidationController.customerNameValidate(userNameText.getText())) {

               try {
                   String userName = userNameText.getText();
                   User user = UserModel.SearchById(userName);
                   System.out.println(user.getEmail());
                   email = user.getEmail();
               }catch (Exception e){
                   AlertController.errormessage("User Name not found");

               }

                if (EmailSentText.getText().equals(email)) {

                    Group1.setVisible(false);
                    Group2.setVisible(true);

                    timermethod(TimerLabel);

                    randomnum = rand.nextInt(9000);
                    randomnum += 1000;

                    try {
                        EmailController.sendEmail(email, "Test Email", randomnum + "");
                        System.out.println("Email sent successfully.");
                        AlertController.animationMesseage("lk.ijse.super_cargo.image/tick.gif", "OTP Sent ", "OTP Code Sent Successful");

                    } catch (Exception e) {

                        AlertController.errormessage("No Connection");
                    }
                }else{
                    AlertController.errormessage("This email is have not Data base");
                }
            }else{
                AlertController.errormessage("invalied Username");
            }
        } else {
            AlertController.errormessage("invalied Email");
        }
    }

    String otp;
    @FXML
    void OtpCodeBtnClick(ActionEvent event) throws IOException {

        if (!OtpCodeText.getText().isEmpty()) {
            if(ValidationController.salary(OtpCodeText.getText())) {
                otp = OtpCodeText.getText();


                if (otp.equals(String.valueOf(randomnum))) {

                    Group2.setVisible(false);
                    Group3.setVisible(true);

                }else{
                    AlertController.errormessage("Invalied OTP Code");
                }
            }else{
                AlertController.errormessage("Invalied OTP Code");
            }
        }else{
            AlertController.errormessage("Enter your Otp Code");
        }
    }

    @FXML
    void OkBtnClick3(ActionEvent event) throws SQLException, IOException {


        if (ValidationController.Password(FirstPasswordText.getText())) {
            if (ValidationController.Password(ComPasswordText.getText())) {

                User user = new User();

                if (FirstPasswordText.getText().equals(ComPasswordText.getText())) {

                    user.setUserName(userNameText.getText());
                    user.setPassword(ComPasswordText.getText());

                    try {
                        boolean isUpdate = UserModel.updatePassword(user);
                        if (isUpdate) {
                            //================
                            AlertController.confirmmessage("Successfull");
                            AlertController.animationMesseage("lk.ijse.super_cargo.image/tick.gif", "Password Change ", "Password Change Successful");

                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        AlertController.errormessage("Un Successfull");
                        AlertController.animationMesseage("lk.ijse.super_cargo.image/wrongicon.png", "Password Change", "Password Change Unsuccessful");

                    }
                    Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/loging.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                } else {
                    AlertController.errormessage("Password not save");
                    AlertController.animationMesseage("lk.ijse.super_cargo.image/wrongicon.png", "Password Change", "Password Change Unsuccessful");
                }
            }else{
                AlertController.errormessage("Invalied Confirm Password");
            }

        }else{
            AlertController.errormessage("Invalied Password");
        }
    }


    @FXML
    void initialize() {
        assert EmailSentText != null : "fx:id=\"EmailSentText\" was not injected: check your FXML file 'frogetPassWord.fxml'.";
        assert EmailSent != null : "fx:id=\"EmailSent\" was not injected: check your FXML file 'frogetPassWord.fxml'.";
        assert OtpCodeText != null : "fx:id=\"OtpCodeText\" was not injected: check your FXML file 'frogetPassWord.fxml'.";
        assert OtpCodeBtn != null : "fx:id=\"OtpCodeBtn\" was not injected: check your FXML file 'frogetPassWord.fxml'.";
        assert NewPassWordBtn != null : "fx:id=\"NewPassWordBtn\" was not injected: check your FXML file 'frogetPassWord.fxml'.";

    }



    public void ResendOtpClick(ActionEvent event) {

        timermethod(TimerLabel);
        TimerLabel.setVisible(true);
        randomnum = rand.nextInt(9000);
        randomnum += 1000;
        try {
            EmailController.sendEmail(email, "Test Email", randomnum + "");
            AlertController.animationMesseage("lk.ijse.super_cargo.image/tick.gif", "OTP Sent ","OTP Code Sent Successful");

        } catch (MessagingException e) {
            e.printStackTrace();
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
