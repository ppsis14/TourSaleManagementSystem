package saleSystem;

import Table.Customer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class InvoicePageController implements Initializable {

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
    private TableView<?> arrearsInvoiceTable;

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
        Alert alertCreateArrearsInvoice = new Alert(Alert.AlertType.CONFIRMATION);
        alertCreateArrearsInvoice.setTitle("Confirmation Dialog");
        alertCreateArrearsInvoice.setHeaderText(null);
        alertCreateArrearsInvoice.setContentText("Do you want to create invoice?");
        Optional<ButtonType> createArrearsInvoiceAction = alertCreateArrearsInvoice.showAndWait();
        if (createArrearsInvoiceAction.get() == ButtonType.OK){
            Alert alertShowCreateArrearsInvoice = new Alert(Alert.AlertType.INFORMATION);
            alertShowCreateArrearsInvoice.setTitle("Information Dialog");
            alertShowCreateArrearsInvoice.setHeaderText(null);
            alertShowCreateArrearsInvoice.setContentText("Creating invoice is successfully!");
            Optional<ButtonType> showCreateArrearsInvoiceAction = alertShowCreateArrearsInvoice.showAndWait();
            if (showCreateArrearsInvoiceAction.get() == ButtonType.OK){
                // update database and table code
            }

        }
        if (createArrearsInvoiceAction.get() == ButtonType.CANCEL){

        }

    }

    @FXML
    void handleCreateDepositInvoiceBtn(ActionEvent event) {
        Alert alertCreateDepositInvoice = new Alert(Alert.AlertType.CONFIRMATION);
        alertCreateDepositInvoice.setTitle("Confirmation Dialog");
        alertCreateDepositInvoice.setHeaderText(null);
        alertCreateDepositInvoice.setContentText("Do you want to create deposit invoice?");
        Optional<ButtonType> createDepositInvoiceAction = alertCreateDepositInvoice.showAndWait();
        if (createDepositInvoiceAction.get() == ButtonType.OK){
            Alert alertShowCreateDepositInvoice = new Alert(Alert.AlertType.INFORMATION);
            alertShowCreateDepositInvoice.setTitle("Information Dialog");
            alertShowCreateDepositInvoice.setHeaderText(null);
            alertShowCreateDepositInvoice.setContentText("Creating invoice is successfully!");
            Optional<ButtonType> showCreateDepositInvoiceAction = alertShowCreateDepositInvoice.showAndWait();
            if (showCreateDepositInvoiceAction.get() == ButtonType.OK){
                // update database and table code
            }


        }
        if (createDepositInvoiceAction.get() == ButtonType.CANCEL){

        }

    }

    @FXML
    void handleTourIDChoiceDI(ActionEvent event) {

    }

    @FXML
    void handleTourIDChoiceAI(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
    }
}
