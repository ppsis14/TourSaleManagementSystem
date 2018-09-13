package saleSystem;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML private JFXButton reserveBtn;
    @FXML private JFXButton memberBtn;
    @FXML private JFXButton tourCheckBtn;
    @FXML private JFXButton invoiceBtn;
    @FXML private JFXButton receiptBtn;
    @FXML private JFXButton logoutBtn;

    @FXML
    void handleInvoiceBtn(ActionEvent event) {

    }

    @FXML
    void handleLogoutBtn(ActionEvent event) throws IOException {
        logoutBtn.getScene().getWindow().hide();
        Stage loginHomeWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/loginPage.fxml"));
        Scene scene = new Scene(root);
        loginHomeWindow.setScene(scene);
        loginHomeWindow.show();
        loginHomeWindow.setResizable(false);

    }

    @FXML
    void handleMemberBtn(ActionEvent event) {

    }

    @FXML
    void handleReceiptBtn(ActionEvent event) {

    }

    @FXML
    void handleReserveBtn(ActionEvent event) throws IOException {
        reserveBtn.getScene().getWindow().hide();
        Stage reservationWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/reservationPage.fxml"));
        Scene scene = new Scene(root);
        reservationWindow.setScene(scene);
        reservationWindow.show();
        reservationWindow.setResizable(false);

    }

    @FXML
    void handleTourCheckBtn(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // set user login by call from database
        //showUserLogined.setText("Thikamporn Simud");
        //homePane.toFront();
        //reservePane.setVisible(false);

    }

}
