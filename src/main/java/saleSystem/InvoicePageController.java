package saleSystem;

import Table.Customer;
import Table.Invoice;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static saleSystem.SaleManagementUtil.*;

public class InvoicePageController implements Initializable {

    @FXML
    private ComboBox<String> tourIDChoiceDI;

    @FXML
    private JFXButton createDepositInvoiceBtn;

    @FXML
    private JFXButton confirmDepositInvoiceStatusBtn;

    @FXML
    private ComboBox<String> tourIDChoiceAI;

    @FXML private TableView<Invoice> depositInvoiceTable;
    @FXML private TableColumn<Invoice, String> reservationCodeColumnDI;
    @FXML private TableColumn<Invoice, String> invoice_No_ColumnDI;
    @FXML private TableColumn<Invoice, Integer> amountColumnDI;
    @FXML private TableColumn<Invoice, String> employeeColumnDI;
    @FXML private TableColumn<Invoice, String> invoiceStatusColumnDI;

    @FXML private TableView<Invoice> arrearsInvoiceTable;
    @FXML private TableColumn<Invoice, String> reservationCodeColumnAI;
    @FXML private TableColumn<Invoice, String> invoice_No_ColumnAI;
    @FXML private TableColumn<Invoice, Integer> amountColumnAI;
    @FXML private TableColumn<Invoice, String> employeeColumnAI;
    @FXML private TableColumn<Invoice, String> invoiceStatusColumnAI;

    @FXML
    private JFXButton createArrearsInvoiceBtn;

    @FXML
    private JFXButton confirmArrearsInvoiceStatusBtn;

    @FXML
    private JFXHamburger menu;

    @FXML
    private JFXDrawer drawerMenu;


    ObservableList<Invoice> obListInvoiceDI = FXCollections.observableArrayList();
    ObservableList<Invoice> obListInvoiceAI = FXCollections.observableArrayList();

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
        showTableView();

    }

    @FXML
    void handleTourIDChoiceAI(ActionEvent event) {
        showTableView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        SaleManagementUtil.setTourID(tourIDChoiceDI);
        SaleManagementUtil.setTourID(tourIDChoiceAI);
        showTableView();
    }

    public void showTableView(){

        String tourNameDI = tourIDChoiceDI.getSelectionModel().getSelectedItem();
        String tourNameAI = tourIDChoiceDI.getSelectionModel().getSelectedItem();
        obListInvoiceDI = FXCollections.observableArrayList(manageableDatabase.getAllInvoiceInTourName(DEPOSIT_INVOICE,tourNameDI));
        obListInvoiceAI = FXCollections.observableArrayList(manageableDatabase.getAllInvoiceInTourName(ARREARS_INVOICE,tourNameAI));

        //find data base for show on table view.
        reservationCodeColumnDI.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        invoice_No_ColumnDI.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        amountColumnDI.setCellValueFactory(new PropertyValueFactory<>("amount"));
        employeeColumnDI.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        invoiceStatusColumnDI.setCellValueFactory(new PropertyValueFactory<>("invoiceStatus"));

        reservationCodeColumnAI.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        invoice_No_ColumnAI.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        amountColumnAI.setCellValueFactory(new PropertyValueFactory<>("amount"));
        employeeColumnAI.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        invoiceStatusColumnAI.setCellValueFactory(new PropertyValueFactory<>("invoiceStatus"));

        depositInvoiceTable.setItems(obListInvoiceDI);
        arrearsInvoiceTable.setItems(obListInvoiceAI);
    }
}
