package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class InvoicePageController {

    @FXML
    private ComboBox<String> tourIDChoiceDI;

    @FXML
    private TableView<?> depositInvoiceTable;

    @FXML
    private JFXButton createDepositInvoiceBtn;

    @FXML
    private JFXButton confirmDepositInvoiceStatusBtn;

    @FXML
    private ComboBox<String> tourIDChoiceAI;

    @FXML
    private TableView<?> arrearInvoiceTable;

    @FXML
    private JFXButton createArrearsInvoiceBtn;

    @FXML
    private JFXButton confirmArrearsInvoiceStatusBtn;

    @FXML
    private JFXHamburger menu;

    @FXML
    private JFXDrawer drawerMenu;

    @FXML
    void handleConfirmArrearsInvoiceStatusBtn(ActionEvent event) {

    }

    @FXML
    void handleConfirmDepositInvoiceStatusBtn(ActionEvent event) {

    }

    @FXML
    void handleCreateArrearsInvoiceBtn(ActionEvent event) {

    }

    @FXML
    void handleCreateDepositInvoiceBtn(ActionEvent event) {

    }

    @FXML
    void handleTourIDChoiceDI(ActionEvent event) {

    }

    @FXML
    void handleTourIDComboBox(ActionEvent event) {

    }

}
