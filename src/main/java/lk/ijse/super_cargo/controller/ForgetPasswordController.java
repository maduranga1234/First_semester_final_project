package lk.ijse.super_cargo.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.super_cargo.dto.User;
import lk.ijse.super_cargo.model.UserModel;
import lk.ijse.super_cargo.util.AlertController;
import lk.ijse.super_cargo.util.EmailController;

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






    Random rand=new Random();
    int randomnum;


    @FXML
    void EmailSentClick(ActionEvent event) throws MessagingException, SQLException {

        String userName = userNameText.getText();
        User user = UserModel.SearchById(userName);
        System.out.println(user.getEmail());
        String email = user.getEmail();

        if (EmailSentText.getText().equals(email)) {
            Group1.setVisible(false);
            Group2.setVisible(true);

        randomnum=rand.nextInt(9000);
        randomnum += 1000;

        try {
            EmailController.sendEmail(email, "Test Email", randomnum+"");
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
}
        }
    }

    String otp;
    @FXML
    void OtpCodeBtnClick(ActionEvent event) throws IOException {
     otp= OtpCodeText.getText();


       if(otp.equals(String.valueOf(randomnum))){

        Group2.setVisible(false);
        Group3.setVisible(true);

    }
    }

    @FXML
    void OkBtnClick3(ActionEvent event) throws SQLException, IOException {
        User user=new User();

        if(FirstPasswordText.getText().equals(ComPasswordText.getText())) {

            user.setUserName(userNameText.getText());
            user.setPassword(ComPasswordText.getText());

            try {
                boolean isUpdate = UserModel.updatePassword(user);
                if (isUpdate) {
                    //================
                    AlertController.confirmmessage("Successfull");
                }
            }catch (SQLException throwables){
                throwables.printStackTrace();
                AlertController.errormessage("Un Successfull");

            }
            Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.super_cargo.view/loging.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
        else {
            AlertController.errormessage("Password not save");
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
