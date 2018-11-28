package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentReceiptPageController implements Initializable {

    @FXML
    private ComboBox<?> tourIDChoiceDR;

    @FXML
    private TableView<?> depositReceiptTable;

    @FXML
    private JFXButton createDepositReceiptBtn;

    @FXML
    private JFXButton confirmDepositReceiptStatusBtn;

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
        Alert alertCreateArrearsReceipt = new Alert(Alert.AlertType.CONFIRMATION);
        alertCreateArrearsReceipt.setTitle("Confirmation Dialog");
        alertCreateArrearsReceipt.setHeaderText(null);
        alertCreateArrearsReceipt.setContentText("Do you want to create receipt?");
        Optional<ButtonType> createArrearsReceiptAction = alertCreateArrearsReceipt.showAndWait();

        if (createArrearsReceiptAction.get() == ButtonType.OK){
            Alert alertShowCreateArrearsReceipt = new Alert(Alert.AlertType.INFORMATION);
            alertShowCreateArrearsReceipt.setTitle("Information Dialog");
            alertShowCreateArrearsReceipt.setHeaderText(null);
            alertShowCreateArrearsReceipt.setContentText("Creating receipt is successfully!");
            Optional<ButtonType> showCreateArrearsReceiptAction = alertShowCreateArrearsReceipt.showAndWait();
            if (showCreateArrearsReceiptAction.get() == ButtonType.OK){
                // update database and table code
            }

        }
        if (createArrearsReceiptAction.get() == ButtonType.CANCEL){

        }

    }

    @FXML
    void handleCreateDepositReceiptBtn(ActionEvent event) {
        Alert alertCreateDepositReceipt = new Alert(Alert.AlertType.CONFIRMATION);
        alertCreateDepositReceipt.setTitle("Confirmation Dialog");
        alertCreateDepositReceipt.setHeaderText(null);
        alertCreateDepositReceipt.setContentText("Do you want to create deposit receipt?");
        Optional<ButtonType> createDepositReceiptAction = alertCreateDepositReceipt.showAndWait();
        if (createDepositReceiptAction.get() == ButtonType.OK){
            Alert alertShowCreateDepositReceipt = new Alert(Alert.AlertType.INFORMATION);
            alertShowCreateDepositReceipt.setTitle("Information Dialog");
            alertShowCreateDepositReceipt.setHeaderText(null);
            alertShowCreateDepositReceipt.setContentText("Creating receipt is successfully!");
            Optional<ButtonType> showCreateDepositReceiptAction = alertShowCreateDepositReceipt.showAndWait();
            if (showCreateDepositReceiptAction.get() == ButtonType.OK){
                // update database and table code
            }


        }
        if (createDepositReceiptAction.get() == ButtonType.CANCEL){

        }

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
