package saleSystem;

import Table.Customer;
import Table.Reservation;
import Table.ReservationPayment;
import Table.TourPackage;
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

import static saleSystem.SaleManagementUtil.manageableDatabase;

public class TourCheckPageController implements Initializable {

    @FXML
    private ComboBox<String> tourIDComboBox;

    @FXML
    private Label tourName;

    @FXML
    private Label tourPrice;

    @FXML
    private Label departureDate;

    @FXML
    private Label returnDate;

    @FXML
    private Label amountCus;

    @FXML
    private Label availableSeat;

    @FXML
    private TableView<ReservationPayment> paymentListTable;
    @FXML private TableColumn<ReservationPayment, String> reservationCodeColumnP;
    @FXML private TableColumn<ReservationPayment, String> nameColumnP;
    @FXML private TableColumn<ReservationPayment, String> depositPaymentStatusColumnP;
    @FXML private TableColumn<ReservationPayment, String> arrearsPaymentStatusColumnP;
    @FXML private TableColumn<ReservationPayment, String> employeeName_ColumnP;

    @FXML
    private TableView<Reservation> reservationListTable;
    @FXML private TableColumn<Reservation, String> reservationCodeColumnR;
    @FXML private TableColumn<Reservation, String> nameColumnR;
    @FXML private TableColumn<Reservation, String> accountColumnR;
    @FXML private TableColumn<Reservation, Integer> amountColumnR;
    @FXML private TableColumn<Reservation, String> invoice_no_ColumnR;
    @FXML
    private JFXButton deleteReserveListBtn;

    @FXML
    private JFXButton confirmStatusBtn;



    @FXML
    private JFXHamburger menu;

    @FXML
    private JFXDrawer drawerMenu;

    ObservableList<Reservation> reservationObList = FXCollections.observableArrayList();
    ObservableList<ReservationPayment> reservPaymentObList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        SaleManagementUtil.setTourID(tourIDComboBox);
        showDetailTourPackage();
        setReservationListTable();
    }

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
        if (action.get() == ButtonType.OK) {

            ReservationPayment deleteReservationPayment = paymentListTable.getSelectionModel().getSelectedItem();  //select item for delete
            String tourID = deleteReservationPayment.getTourID();
            if (deleteReservationPayment != null) {   //when user select data

                manageableDatabase.deleteData(deleteReservationPayment);  //delete in database
                manageableDatabase.deleteDataByReservCode(deleteReservationPayment.getReservationCode());
                reservPaymentObList.remove(paymentListTable.getSelectionModel().getSelectedItem()); //delete on table view
                reservationObList = FXCollections.observableArrayList(manageableDatabase.getAllReservationByTourID(tourID));
                //update last data
                TourPackage tourPackage = manageableDatabase.getTourPackage(tourID);
                int availableSeat = tourPackage.getAvailable() + deleteReservationPayment.getAmountCustomer();
                manageableDatabase.updateAvailableData(tourID,availableSeat);   //update seat in tour package

                showDetailTourPackage();
                setReservationListTable();

            }
        }
    }

    @FXML
    void handleConfirmInvoiceStatusBtn(ActionEvent event){}

    @FXML
    void handleSelectTourIDCombobox(ActionEvent event){
        showDetailTourPackage();
        setReservationListTable();
    }

    void showDetailTourPackage(){
        String tourID = manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem());
        TourPackage tourPackage = manageableDatabase.getTourPackage(tourID);

        tourName.setText(tourPackage.getTourName());
        tourPrice.setText(String.valueOf(tourPackage.getPrice()));
        departureDate.setText(tourPackage.getDepartureDate());
        returnDate.setText(tourPackage.getReturnDate());
        amountCus.setText(String.valueOf(tourPackage.getAmount()));
        availableSeat.setText(String.valueOf(tourPackage.getAvailable()));
    }

    void setReservationListTable(){

        String tourID = manageableDatabase.getTourID(String.valueOf(tourIDComboBox.getSelectionModel().getSelectedItem()));
        reservationObList = FXCollections.observableArrayList(manageableDatabase.getAllReservationByTourID(tourID));
        reservPaymentObList = FXCollections.observableArrayList(manageableDatabase.getAllReservationPaymentByTourID(tourID));

        //find data base for show on table view.
        reservationCodeColumnP.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        nameColumnP.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        depositPaymentStatusColumnP.setCellValueFactory(new PropertyValueFactory<>("depositStatus"));
        arrearsPaymentStatusColumnP.setCellValueFactory(new PropertyValueFactory<>("arrearsStatus"));
        employeeName_ColumnP.setCellValueFactory(new PropertyValueFactory<>("employeeName"));

        reservationCodeColumnR.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        nameColumnR.setCellValueFactory(new PropertyValueFactory<>("customerName"));


        paymentListTable.setItems(reservPaymentObList);
        reservationListTable.setItems(reservationObList);
    }

}

