package tourSaleManagementController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tourSaleManagementSystemUtil.DisplayGUIUtil;

public class ToolBarController {
    @FXML private JFXButton homeWindow;
    @FXML private JFXButton reserveWindow;
    @FXML private JFXButton memberWindow;
    @FXML private JFXButton tourCheckWindow;
    @FXML private JFXButton invoiceWindow;
    @FXML private JFXButton receiptWindow;
    @FXML private JFXButton logoutWindow;

    @FXML
    private void loadHomeWindow(ActionEvent event) {
        homeWindow.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/homePage.fxml"), "Home");
    }

    @FXML
    private void loadInvoiceWindow(ActionEvent event) {
        invoiceWindow.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/invoicePage.fxml"), "Invoice");
    }

    @FXML
    private void loadLoginWindow(ActionEvent event) {
        logoutWindow.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/loginPage.fxml"), "Login");
    }
    @FXML
    private void loadMemberWindow(ActionEvent event){
        memberWindow.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/customerManagePage.fxml"), "Customer Management");
    }

    @FXML
    private void loadReceiptWindow(ActionEvent event) {
        receiptWindow.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/paymentReceiptPage.fxml"), "Payment Receipt");
    }
    @FXML
    private void loadReserveWindow(ActionEvent event) {
        reserveWindow.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/reservationPage.fxml"), "Reservation");
    }

    @FXML
    private void loadTourCheckWindow(ActionEvent event) {
        tourCheckWindow.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/tourCheckPage.fxml"), "Tour Checking");
    }
}