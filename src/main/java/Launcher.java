import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        URL resource = Launcher.class.getResource("/lk.ijse.super_cargo.view/dashBord.fxml");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));

        stage.centerOnScreen();
        stage.show();
    }
}