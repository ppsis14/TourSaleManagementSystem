package saleSystem;

import Table.Customer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static saleSystem.SaleManagementUtil.manageableDatabase;

public class CustomerManagePageController implements Initializable {

    @FXML
    private TextField searchByCustomerID;

    @FXML
    private TextField searchByCustomerName;

    @FXML
    private JFXButton searchCustomerBtn;

    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> customerIDColumn;
    @FXML private TableColumn<Customer, String> titleNameColumn;
    @FXML private TableColumn<Customer, String> firstNameColumn;
    @FXML private TableColumn<Customer, String> lastNameColumn;
    @FXML private TableColumn<Customer, String> passportNoColumn;

    @FXML
    private JFXButton editCustomerBtn;

    @FXML
    private JFXButton updateCustomerBtn;

    @FXML
    private JFXButton deleteCustomerBtn;

    @FXML
    private JFXHamburger menu;

    @FXML
    private JFXDrawer drawerMenu;

    ObservableList<Customer> obListCustomer = FXCollections.observableList(manageableDatabase.getAllCustomer());
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));

        showTableView(obListCustomer);  //show data on table view


    }

    @FXML
    void handleDeleteCustomerBtn(ActionEvent event) {
        Alert alertConfirmToDeleteCustomer = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmToDeleteCustomer.setTitle("Confirmation Dialog");
        alertConfirmToDeleteCustomer.setHeaderText(null);
        alertConfirmToDeleteCustomer.setContentText("Do you want to delete this customer?");
        Optional<ButtonType> action = alertConfirmToDeleteCustomer.showAndWait();
        if (action.get() == ButtonType.OK){
            // code for delete reservation
            Customer deleteCustomer = customerTable.getSelectionModel().getSelectedItem();  //select item for delete

            if (deleteCustomer != null) {   //when user select data

                manageableDatabase.deleteData(deleteCustomer);  //delete in database
                obListCustomer.remove(customerTable.getSelectionModel().getSelectedItem()); //delete on table view
            }
        }
    }


    @FXML
    void handleEditCustomerBtn(ActionEvent event) {

        Customer editCustomer = customerTable.getSelectionModel().getSelectedItem();

        //SaleManagementUtil.loadWindow(getClass().getResource("/editCustomer.fxml"), "Edit Customer Information");
        if(editCustomer != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editCustomer.fxml"));
                Parent parent = (Parent) loader.load();
                EditCustomerPageController editCustomerPageController = loader.getController();
                editCustomerPageController.setCustomer(editCustomer);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.show();
                stage.setScene(new Scene(parent));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void handleSearchCustomerBtn(ActionEvent event) {

    }

    @FXML
    void handleUpdateCustomerBtn(ActionEvent event) {
        Alert alertShowInformationIsUpdate = new Alert(Alert.AlertType.INFORMATION);
        alertShowInformationIsUpdate.setTitle("Confirmation Dialog");
        alertShowInformationIsUpdate.setHeaderText(null);
        alertShowInformationIsUpdate.setContentText("Customer Information is update!");
        Optional<ButtonType> action = alertShowInformationIsUpdate.showAndWait();
        if (action.get() == ButtonType.OK){
            // code for delete reservation
            obListCustomer = FXCollections.observableList(manageableDatabase.getAllCustomer());
            this.showTableView(obListCustomer);
        }

    }

    public void showTableView(ObservableList<Customer> obListCustomer){

        //find data base for show on table view.

        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        titleNameColumn.setCellValueFactory(new PropertyValueFactory<>("titleNameTH"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstNameTH"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastNameTH"));
        passportNoColumn.setCellValueFactory(new PropertyValueFactory<>("passport_no"));
        passportNoColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        passportNoColumn.setCellValueFactory(new PropertyValueFactory<>("cell_phone"));
        passportNoColumn.setCellValueFactory(new PropertyValueFactory<>("contactAddress"));

        customerTable.setItems(obListCustomer);
    }

}

