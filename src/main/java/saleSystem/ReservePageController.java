package saleSystem;

import Table.Customer;
import Table.Invoice;
import Table.Reservation;
import Table.ReservationPayment;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static saleSystem.SaleManagementUtil.*;


public class ReservePageController implements Initializable {

    @FXML
    private ChoiceBox<String> titleNameTH;

    @FXML
    private TextField firstNameTH;

    @FXML
    private TextField lastNameTH;

    @FXML
    private ChoiceBox<String> titleNameEN;

    @FXML
    private TextField firstNameEN;

    @FXML
    private TextField lastNameEN;

    @FXML
    private ChoiceBox<String> genderChoice;

    @FXML
    private TextField age;

    @FXML
    private TextField occupation;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private TextField passportNo;

    @FXML
    private DatePicker expPassportDate;

    @FXML
    private TextArea address;

    @FXML
    private TextField phoneNum;

    @FXML
    private TextField homeTelNum;

    @FXML
    private TextField faxNum;

    @FXML
    private TextField email;

    @FXML
    private TextField underlyingDisease;

    @FXML
    private TextField foodAllergy;

    @FXML
    private JFXCheckBox eatBeefY;

    @FXML
    private JFXCheckBox eatBeefN;

    @FXML
    private TextField moreDetail;

    @FXML
    private ComboBox<String> hearAboutUsChoices;

    @FXML
    private JFXHamburger menu;

    @FXML
    private JFXDrawer drawerMenu;

    @FXML
    private JFXButton addCustomerBtn;

    @FXML
    private Label reserveCode;

    @FXML
    private Label customerNo;

    @FXML
    private ComboBox<String> tourIDComboBox;

    @FXML
    private JFXCheckBox oldCustomer;

    @FXML
    private JFXCheckBox newCustomer;
    @FXML
    private TextField searchByCustomerName;
    @FXML
    private Button searchCustomerBtn;
    @FXML
    private Label loginNameLabel;

    private final String DEPOSIT_INVOICE = "invoice_deposit";
    private final String ARREARS_INVOICE = "invoice_arrears";

    private ArrayList<String> customer_id_List = new ArrayList<>();
    private ReservationPayment reservationPayment = new ReservationPayment();
    private Reservation reservationCustomer = new Reservation() ;
    private Customer customer = new Customer();
    private Invoice invoice = new Invoice();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        SaleManagementUtil.setTitleNameTH(titleNameTH);
        SaleManagementUtil.setTitleNameEN(titleNameEN);
        SaleManagementUtil.setGender(genderChoice);
        SaleManagementUtil.setHearAboutUs(hearAboutUsChoices);
        newCustomer.setSelected(true);
        reserveCode.setText(createReservationCode());
        loginNameLabel.setText(loginEmployee.getFirstName()+" "+loginEmployee.getLastName()+" [ "+loginEmployee.getPosition()+" ]");
        SaleManagementUtil.setTourID(tourIDComboBox);
        SaleManagementUtil.setDatePickerFormat(dateOfBirth);
        SaleManagementUtil.setDatePickerFormat(expPassportDate);
    }

    @FXML public void handleNotEatBeefCheckbox(ActionEvent event) { eatBeefY.setSelected(false); }
    @FXML public void handleEatBeefCheckbox(ActionEvent event) { eatBeefN.setSelected(false); }
    @FXML public void handleNewCustomerCheckbox(ActionEvent event) {
        oldCustomer.setSelected(false);
        searchByCustomerName.setDisable(true);
        searchCustomerBtn.setDisable(true);
    }
    @FXML public void handleOldCustomerCheckbox(ActionEvent event) {
        newCustomer.setSelected(false);
        searchByCustomerName.setDisable(false);
        searchCustomerBtn.setDisable(false);
    }

    @FXML
    void handleAddCustomerBtn(ActionEvent event) {

        //pop up 1
        Alert alertConfirmToAddCustomerData = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmToAddCustomerData.setTitle("Confirmation Dialog");
        alertConfirmToAddCustomerData.setHeaderText(null);
        alertConfirmToAddCustomerData.setContentText("Do you want to add reservation?");
        Optional<ButtonType> addCustomerDataAction = alertConfirmToAddCustomerData.showAndWait();


        if (addCustomerDataAction.get() == ButtonType.OK){

            if(newCustomer.isSelected()){

                setCustomerFromGUI();
                manageableDatabase.insertData(customer);
                customer_id_List.add(customer.getCustomerID());
                setReservationCustomerFromGUI();

                //when customer inserted
                clearText();
                Customer newCustomer = new Customer();
                customer = newCustomer;
            }

            else if (oldCustomer.isSelected()){ //search name
                setUpOldCustomerData();
            }


            //pop up 2
            Alert alertConfirmToAddCustomerMore = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirmToAddCustomerMore.setTitle("Confirmation Dialog");
            alertConfirmToAddCustomerMore.setHeaderText(null);
            alertConfirmToAddCustomerMore.setContentText("Do you want to add another customer?");
            Optional<ButtonType> addCustomerMoreAction = alertConfirmToAddCustomerMore.showAndWait();

            if (addCustomerMoreAction.get() == ButtonType.OK){ // if user want to add another customer

                customerNo.setText(String.valueOf(Integer.valueOf(customerNo.getText()) + 1));     // add count amount customer

                //clear text for fill next data
                clearText();
                Customer newCustomer = new Customer();
                customer = newCustomer;

            }
            else if (addCustomerMoreAction.get() == ButtonType.CANCEL){     //stop reserving another customer

                //insert reservation payment and deposit invoice into database
                setReservationPaymentFromGUI();
                setDepositInvoice();
                reservationPayment.setCustomerID(customer_id_List.get(0));
                manageableDatabase.insertData(invoice,DEPOSIT_INVOICE);

                //insert reservation payment to database
                manageableDatabase.insertData(reservationPayment);

                //insert reservation customer to database
                for (String customer_id: customer_id_List) {
                    reservationCustomer.setCustomerID(customer_id);
                    manageableDatabase.insertData(reservationCustomer);

                }

                //setup value of reservation page
                reserveCode.setText(createReservationCode());
                customerNo.setText("1");     // setup count amount customer
                clearText();
                Customer newCustomer = new Customer();
                customer = newCustomer;
            }

        }
        else if (addCustomerDataAction.get() == ButtonType.CANCEL){
            //back to edit
        }



    }

    @FXML
    void handleSearchCustomerBtn(ActionEvent event){

    }



    public void setDepositInvoice(){
        invoice.setReservationCode(reservationPayment.getReservationCode());
        invoice.setInvoiceNo(String.valueOf(Integer.valueOf(manageableDatabase.getLastInvoiceNo(DEPOSIT_INVOICE))+1));
        invoice.setAmount(reservationPayment.getAmountCustomer());
        invoice.setEmployeeName(manageableDatabase.getNameEmployee(reservationPayment.getEmployeeID()));
        invoice.setInvoiceStatus(NOT_CREATED);
        invoice.setTourID(reservationPayment.getTourID());

    }

    public void setReservationCustomerFromGUI(){

        reservationCustomer.setReservationCode(reserveCode.getText());
        reservationCustomer.setTourID(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem()));
        reservationCustomer.setTourName(tourIDComboBox.getSelectionModel().getSelectedItem());
        reservationCustomer.setCustomerID(customer.getCustomerID());
        reservationCustomer.setCustomerName(manageableDatabase.getNameCustomer(reservationCustomer.getCustomerID()));
        reservationCustomer.setEmployeeID(loginEmployee.getEmployee_ID());
        reservationCustomer.setEmployeeName(manageableDatabase.getNameEmployee(reservationCustomer.getEmployeeID()));

    }
    public void setReservationPaymentFromGUI(){

        reservationPayment.setReservationCode(reserveCode.getText());
        reservationPayment.setTourID(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem()));
        reservationPayment.setTourName(tourIDComboBox.getSelectionModel().getSelectedItem());
        reservationPayment.setCustomerID(customer_id_List.get(0));
        reservationPayment.setCustomerName(manageableDatabase.getNameCustomer(reservationPayment.getCustomerID()));
        reservationPayment.setEmployeeID(loginEmployee.getEmployee_ID());
        reservationPayment.setEmployeeName(manageableDatabase.getNameEmployee(reservationCustomer.getEmployeeID()));
        reservationPayment.setAmountCustomer(Integer.parseInt(customerNo.getText()));
        reservationPayment.setTotal_price(reservationPayment.calculateTotalPrice(manageableDatabase.getTourPrice(reservationPayment.getTourID())));
        reservationPayment.setDepositStatus(NOT_PAID);
        reservationPayment.setArrearsStatus(NOT_PAID);

    }

    public void setCustomerFromGUI(){
        int newCustomerID = Integer.valueOf(manageableDatabase.getLastCustomerID())+1;

        customer.setCustomerID(String.valueOf(newCustomerID));
        //information
        customer.setTitleNameTH(titleNameTH.getSelectionModel().getSelectedItem());
        customer.setFirstNameTH(firstNameTH.getText());
        customer.setLastNameTH(lastNameTH.getText());
        customer.setTitleNameENG(titleNameEN.getSelectionModel().getSelectedItem());
        customer.setFirstNameENG(firstNameEN.getText());
        customer.setLastNameENG(lastNameEN.getText());
        customer.setGender(genderChoice.getSelectionModel().getSelectedItem());
        customer.setAge(age.getText());
        customer.setDateOfBirth(dateOfBirth.getEditor().getText());
        customer.setPassport_no(passportNo.getText());
        customer.setExp_passport(expPassportDate.getEditor().getText());
        customer.setOccupation(occupation.getText());
        //Contact
        customer.setContactAddress(address.getText());
        customer.setCell_phone(phoneNum.getText());
        customer.setHome_Tel(homeTelNum.getText());
        customer.setFax(faxNum.getText());
        customer.setEmail(email.getText());
        //moreInfo
        customer.setDisease(underlyingDisease.getText());
        customer.setFoodAllergy(foodAllergy.getText());
        customer.setEatBeef(eatBeefY.isSelected() ? eatBeefY.getText() : eatBeefN.getText());
        customer.setMoreDetail(moreDetail.getText());
        customer.setHearAboutUs(hearAboutUsChoices.getSelectionModel().getSelectedItem());
    }



    public String createReservationCode(){
        String lastReserveCode;
        String currentReserveCode;

        lastReserveCode = manageableDatabase.getLastReservationPaymentCode();
        currentReserveCode = String.format("%08d",Integer.parseInt(lastReserveCode) + 1) ;

        return currentReserveCode;
    }

    public void setUpOldCustomerData(){

        //customer = manageableDatabase.getOneCustomer();
        newCustomer.setSelected(false);
        oldCustomer.setSelected(true);
        //information
        titleNameTH.setValue(customer.getTitleNameTH());
        firstNameTH.setText(customer.getFirstNameTH());
        lastNameTH.setText(customer.getLastNameTH());
        titleNameEN.setValue(customer.getTitleNameENG());
        firstNameEN.setText(customer.getFirstNameENG());
        lastNameEN.setText(customer.getLastNameENG());
        genderChoice.setValue(customer.getGender());
        age.setText(customer.getAge());
        String[] dateCut = customer.getDateOfBirth().split("-");
        dateOfBirth.setValue(LocalDate.of(Integer.valueOf(dateCut[2]), Integer.valueOf(dateCut[0]), Integer.valueOf(dateCut[1])));
        passportNo.setText(customer.getPassport_no());
        String[] dateExpCut = customer.getExp_passport().split("-");
        expPassportDate.setValue(LocalDate.of(Integer.valueOf(dateExpCut[2]), Integer.valueOf(dateExpCut[0]), Integer.valueOf(dateExpCut[1])));
        occupation.setText(customer.getOccupation());
        //Contact
        address.setText(customer.getContactAddress());
        phoneNum.setText(customer.getCell_phone());
        homeTelNum.setText(customer.getHome_Tel());
        faxNum.setText(customer.getFax());
        email.setText(customer.getEmail());
        //moreInfo
        underlyingDisease.setText(customer.getDisease());
        foodAllergy.setText(customer.getFoodAllergy());

        if (customer.getEatBeef().equalsIgnoreCase("yes")) {
            eatBeefY.setSelected(true);
            eatBeefN.setSelected(false);
        } else {
            eatBeefY.setSelected(false);
            eatBeefN.setSelected(true);
        }

        moreDetail.setText(customer.getMoreDetail());
        hearAboutUsChoices.setValue(customer.getHearAboutUs());

    }

    public void clearText(){
        newCustomer.setSelected(true);
        oldCustomer.setSelected(false);
        //information
        titleNameTH.getSelectionModel().clearSelection();
        titleNameTH.setValue("นางสาว");
        firstNameTH.clear();
        lastNameTH.clear();
        titleNameEN.getSelectionModel().clearSelection();
        titleNameEN.setValue("Miss");
        firstNameEN.clear();
        lastNameEN.clear();
        genderChoice.getSelectionModel().clearSelection();
        genderChoice.setValue("Female");
        age.clear();
        dateOfBirth.getEditor().clear();
        passportNo.clear();
        expPassportDate.getEditor().clear();
        occupation.clear();
        //Contact
        address.clear();
        phoneNum.clear();
        homeTelNum.clear();
        faxNum.clear();
        email.clear();
        //moreInfo
        underlyingDisease.clear();
        foodAllergy.clear();
        eatBeefY.setSelected(false);
        eatBeefN.setSelected(false);
        moreDetail.clear();
        hearAboutUsChoices.getSelectionModel().clearSelection();
        hearAboutUsChoices.setValue("Bangkokbizs News");
        searchByCustomerName.setDisable(false);
        searchCustomerBtn.setDisable(false);

    }

}
