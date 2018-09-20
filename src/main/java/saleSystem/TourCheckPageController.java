package saleSystem;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TourCheckPageController {
    @FXML private JFXButton tourCheckToHomeBtn;

    @FXML
    void handleBackHomeFormTourCheckBtn(ActionEvent event) throws IOException {
        tourCheckToHomeBtn.getScene().getWindow().hide();
        Stage tourToHomeWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/homePage.fxml"));
        Scene scene = new Scene(root);
        tourToHomeWindow.setScene(scene);
        tourToHomeWindow.show();
        tourToHomeWindow.setResizable(false);

    }

}
