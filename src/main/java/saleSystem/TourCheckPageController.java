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
    void handleConfirmStatusBtn(ActionEvent event) {
        Alert alertConfirmStatus = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmStatus.setTitle("Confirmation Dialog");
        alertConfirmStatus.setHeaderText(null);
        alertConfirmStatus.setContentText("Do you want to confirm status?");
        Optional<ButtonType> action = alertConfirmStatus.showAndWait();
        if (action.get() == ButtonType.OK){
            // code for set deposit and arrears status
        }

    }

    @FXML
    void handleDeleteReserveListBtn(ActionEvent event) {
        Alert alertConfirmToDeleteReservationList = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmToDeleteReservationList.setTitle("Confirmation Dialog");
        alertConfirmToDeleteReservationList.setHeaderText(null);
        alertConfirmToDeleteReservationList.setContentText("Do you want to delete this reservation?");
        Optional<ButtonType> action = alertConfirmToDeleteReservationList.showAndWait();
        if (action.get() == ButtonType.OK){
            // code for delete reservation
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        SaleManagementUtil.setTourID(tourIDComboBox);

    }
}

