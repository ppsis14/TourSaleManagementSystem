package tourSaleManagementController;

import Table.*;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tourSaleManagementSystemUtil.DisplayGUIUtil;
import tourSaleManagementSystemUtil.FormatConverter;
import tourSaleManagementSystemUtil.Util;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.*;
import static tourSaleManagementSystemUtil.Util.*;

public class TourCheckPageController implements Initializable {
    @FXML private StackPane rootPane;
    @FXML private ComboBox<String> tourIDComboBox;
    @FXML private Label tourName;
    @FXML private Label tourPrice;
    @FXML private Label departureDate;
    @FXML private Label returnDate;
    @FXML private Label amountCus;
    @FXML private Label availableSeat;
    @FXML private Label showTourID;
    @FXML private TableView<ReservationPayment> paymentListTable;
    @FXML private TableColumn<ReservationPayment, String> reservationCodeColumnP;
    @FXML private TableColumn<ReservationPayment, String> nameColumnP;
    @FXML private TableColumn<ReservationPayment, String> depositPaymentStatusColumnP;
    @FXML private TableColumn<ReservationPayment, String> arrearsPaymentStatusColumnP;
    @FXML private TableColumn<ReservationPayment, String> employeeName_ColumnP;
    @FXML private TableView<Reservation> reservationListTable;
    @FXML private TableColumn<Reservation, String> reservationCodeColumnR;
    @FXML private TableColumn<Reservation, String> nameColumnR;
    @FXML private TableColumn<Reservation, String> customerAge;
    @FXML private TableColumn<Reservation, String> phoneNumCus;
    @FXML private JFXButton deleteReserveListBtn;
    @FXML private JFXButton confirmStatusBtn;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;

    ObservableList<Reservation> reservationObList = FXCollections.observableArrayList();
    ObservableList<ReservationPayment> reservePaymentObList = FXCollections.observableArrayList();
    private String status = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayGUIUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        Util.setTourID(tourIDComboBox);
        showTourID.setText(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem()));
        showDetailTourPackage();
        setReservationListTable();
    }

    @FXML
    void handleConfirmStatusBtn(ActionEvent event) {

        ReservationPayment selectReservationPayment = paymentListTable.getSelectionModel().getSelectedItem();

        if(selectReservationPayment != null) {
            String[] options = {"Deposit Invoice Payment", "Invoice Payment"};

            ChoiceDialog<String> dialog = new ChoiceDialog<String>("Deposit Invoice Payment", options);
            dialog.setTitle("Confirmation Dialog");
            dialog.setHeaderText("Confirm Invoice Payment Status");
            dialog.setContentText("Choose Invoice Payment Type ");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                if (result.get().equals("Deposit Invoice Payment")) {
                    //System.out.println("Your choice: " + result.get());
                    if(selectReservationPayment.getDepositStatus().equals(NOT_PAID)) {
                        selectReservationPayment.setDepositStatus(PAID);
                        manageableDatabase.updateStatusPayment(selectReservationPayment);                   //update status reservation payment
                        manageableDatabase.insertData(createReceiptData(DEPOSIT_RECEIPT), DEPOSIT_RECEIPT);  //create deposit receipt
                        manageableDatabase.insertData(createArrearsInvoice(), ARREARS_INVOICE);        //insert arrears invoice
                    }
                }
                else if (result.get().equals("Invoice Payment")){
                    //System.out.println("Your choice: " + result.get());
                    if (selectReservationPayment.getArrearsStatus().equals(NOT_PAID)) {
                        selectReservationPayment.setArrearsStatus(PAID);
                        manageableDatabase.updateStatusPayment(selectReservationPayment);                   //update status reservation payment
                        manageableDatabase.insertData(createReceiptData(ARREARS_RECEIPT), ARREARS_RECEIPT);  //insert arrears receipt
                    }
                }
                setReservationListTable();
            }
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
                reservePaymentObList.remove(paymentListTable.getSelectionModel().getSelectedItem()); //delete on table view
                reservationObList = FXCollections.observableArrayList(manageableDatabase.getAllReservationByTourID(tourID));
                //update last data
                TourPackage tourPackage = manageableDatabase.getOneTourPackage(tourID);
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
        showTourID.setText(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem()));
        showDetailTourPackage();
        setReservationListTable();
    }

    void showDetailTourPackage(){
        String tourID = manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem());
        TourPackage tourPackage = manageableDatabase.getOneTourPackage(tourID);

        tourName.setText(tourPackage.getTourName());
        tourPrice.setText(String.format("%,.2f",Double.valueOf(tourPackage.getPrice())));
        departureDate.setText(tourPackage.getDepartureDate());
        returnDate.setText(tourPackage.getReturnDate());
        amountCus.setText(String.valueOf(tourPackage.getAmount()));
        availableSeat.setText(String.valueOf(tourPackage.getAvailable()));
    }

    void setReservationListTable(){

        String tourID = manageableDatabase.getTourID(String.valueOf(tourIDComboBox.getSelectionModel().getSelectedItem()));
        reservationObList = FXCollections.observableArrayList(manageableDatabase.getAllReservationByTourID(tourID));
        reservePaymentObList = FXCollections.observableArrayList(manageableDatabase.getAllReservationPaymentByTourID(tourID));

        //find data base for show on table view.
        reservationCodeColumnP.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        nameColumnP.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        depositPaymentStatusColumnP.setCellValueFactory(new PropertyValueFactory<>("depositStatus"));
        arrearsPaymentStatusColumnP.setCellValueFactory(new PropertyValueFactory<>("arrearsStatus"));
        employeeName_ColumnP.setCellValueFactory(new PropertyValueFactory<>("employeeName"));

        reservationCodeColumnR.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        nameColumnR.setCellValueFactory(new PropertyValueFactory<>("customerName"));


        paymentListTable.setItems(reservePaymentObList);
        reservationListTable.setItems(reservationObList);
    }

    private Invoice createArrearsInvoice() {
        ReservationPayment selectReservationPayment = paymentListTable.getSelectionModel().getSelectedItem();
        Invoice invoice = manageableDatabase.getOneInvoice(DEPOSIT_INVOICE,selectReservationPayment.getReservationCode());
        invoice.setInvoiceNo(FormatConverter.generateInvoiceNo(ARREARS_INVOICE,selectReservationPayment.getReservationCode()));
        invoice.setInvoiceStatus(NOT_CREATED);
        return invoice;
    }


    private Receipt createReceiptData(String receiptType){

        String invoiceType = null;
        if(receiptType == DEPOSIT_RECEIPT)
            invoiceType = DEPOSIT_INVOICE;
        else if(receiptType == ARREARS_RECEIPT)
            invoiceType = ARREARS_INVOICE;

        ReservationPayment selectReservationPayment = paymentListTable.getSelectionModel().getSelectedItem();
        Invoice selectInvoice = manageableDatabase.getOneInvoice(invoiceType,selectReservationPayment.getReservationCode());
        Receipt receipt = new Receipt(
                selectInvoice.getReservationCode(),
                selectInvoice.getInvoiceNo(),
                selectInvoice.getTourID(),
                selectInvoice.getTourName(),
                selectInvoice.getCustomerID(),
                selectInvoice.getCustomerName(),
                selectInvoice.getEmployeeID(),
                selectInvoice.getEmployeeName(),
                selectInvoice.getAmountCustomer(),
                selectInvoice.getTotalPrice(),
                NOT_CREATED
        );

    return receipt;
    }



}

