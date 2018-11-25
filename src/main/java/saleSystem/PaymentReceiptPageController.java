package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentReceiptPageController implements Initializable {

    @FXML
    private ComboBox<?> tourIDComboBoxDR;

    @FXML
    private TableView<?> depositReceiptTable;

    @FXML
    private JFXButton createDepositReceiptBtn;

    @FXML
    private JFXButton confirmDepositReceiptStatusBtn;

    @FXML
    private Tab tourIDComboBoxAR;

    @FXML
    private ComboBox<?> tourIDChoiceAR;

    @FXML
    private TableView<?> arrearReceiptTable;

    @FXML
    private JFXButton createArrearsReceiptBtn;

    @FXML
    private JFXButton confirmArrearsReceiptStatusBtn;

    @FXML
    private JFXHamburger menu;

    @FXML
    private JFXDrawer drawerMenu;

    @FXML
    void handleConfirmArrearsReceiptStatusBtn(ActionEvent event) {

    }

    @FXML
    void handleConfirmDepositReceiptStatusBtn(ActionEvent event) {

    }

    @FXML
    void handleCreateArrearsReceiptBtn(ActionEvent event) {

    }

    @FXML
    void handleCreateDepositReceiptBtn(ActionEvent event) {

    }

    @FXML
    void handleTourIDChoiceAR(ActionEvent event) {

    }

    @FXML
    void handleTourIDChoiceDR(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
    }
}
