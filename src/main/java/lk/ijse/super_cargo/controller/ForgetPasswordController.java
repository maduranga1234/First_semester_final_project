package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private Group Group1;
    @FXML
    private Group Group2;


    Random rand=new Random();
    int randomnum;
    String email;

    @FXML
    void EmailSentClick(ActionEvent event) throws MessagingException {

        Group1.setVisible(false);
        Group2.setVisible(true);

        randomnum=rand.nextInt(9000);
        randomnum += 1000;

        email=EmailSentText.getText();

        try {
            EmailController.sendEmail(email, "Test Email", randomnum+"");
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();


}
    }

    String otp;

    @FXML
    void OtpCodeBtnClick(ActionEvent event) throws IOException {
     otp= OtpCodeText.getText();


       if(otp.equals(String.valueOf(randomnum))){

         Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/changePassWord.fxml"));
            SmallAnchorpane.getChildren().clear();
            SmallAnchorpane.getChildren().add(load);

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
}
