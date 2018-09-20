package saleSystem;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentReceiptPageController {
    @FXML JFXButton receiptToHomeBtn;
    @FXML
    void handleBackHomeFromReceiptBtn() throws IOException {
        receiptToHomeBtn.getScene().getWindow().hide();
        Stage receiptToHomeWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/homePage.fxml"));
        Scene scene = new Scene(root);
        receiptToHomeWindow.setScene(scene);
        receiptToHomeWindow.show();
        receiptToHomeWindow.setResizable(false);
    }
}
