package lk.ijse.super_cargo.util;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ButtonColourController {

    public static void btncolor(JFXButton btn, AnchorPane anchorPane) {
        btn.setStyle("-fx-background-color: #08de08; -fx-effect:  dropshadow(three-pass-box, rgba(10,10,10,0.6), 10, 0, 0, 10); -fx-background-radius : 3");
        anchorPane.getChildren().addListener((ListChangeListener<Node>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Node node : change.getAddedSubList()) {
                        if (node instanceof AnchorPane) {
                            // Check if the new node is an AnchorPane
                            AnchorPane newAnchorPane = (AnchorPane) node;
                            if (newAnchorPane.getId().equals("AnchorpaneHome")) {
                                btn.setStyle("-fx-background-color: #ffffff; -fx-effect:  dropshadow(three-pass-box, rgba(10,10,10,0.6), 10, 0, 0, 10); -fx-background-radius : 12");
                            } else {
                                btn.setStyle("-fx-background-color: white;");
                            }
                        }
                    }
                }
            }
        });
    }
}
