package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class TourCheckPageController implements Initializable {

    @FXML
    private ComboBox<?> tourIDComboBox;

    @FXML
    private Label tourName;

    @FXML
    private Label tourPrice;

    @FXML
    private Label departureDate;

    @FXML
    private Label returnDate;

    @FXML
    private Label AmountCus;

    @FXML
    private Label availableSeat;

    @FXML
    private TableView<?> reservationListTable;

    @FXML
    private JFXButton deleteReserveListBtn;

    @FXML
    private JFXButton confirmStatusBtn;

    @FXML
    private TableView<?> tableDepositPayment1;

    @FXML
    private JFXHamburger menu;

    @FXML
    private JFXDrawer drawerMenu;

    @FXML
    void handleCreateDPayment(ActionEvent event) {

    }

    @FXML
    void handleDeleteReserveListBtn(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

