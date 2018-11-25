package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerManagePageController implements Initializable {

    @FXML
    private TextField searchByCustomerID;

    @FXML
    private TextField searchByCustomerName;

    @FXML
    private JFXButton searchCustomerBtn;

    @FXML
    private TableView<?> customerTable;

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

    @FXML
    void handleDeleteCustomerBtn(ActionEvent event) {

    }

    @FXML
    void handleEditCustomerBtn(ActionEvent event) {
        SaleManagementUtil.loadWindow(getClass().getResource("/editCustomer.fxml"), "Edit Customer Information");

    }

    @FXML
    void handleSearchCustomerBtn(ActionEvent event) {

    }

    @FXML
    void handleUpdateCustomerBtn(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));

    }
}

