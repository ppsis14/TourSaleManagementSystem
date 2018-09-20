package saleSystem;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InvoicePageController {
    @FXML
    private JFXButton invoiceHomeBtn;

    @FXML
    void handleBackHomeFromInvoiceBtn(ActionEvent event) throws IOException {
        invoiceHomeBtn.getScene().getWindow().hide();
        Stage invoiceToHomeWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/homePage.fxml"));
        Scene scene = new Scene(root);
        invoiceToHomeWindow.setScene(scene);
        invoiceToHomeWindow.show();
        invoiceToHomeWindow.setResizable(false);
    }


}
