package saleSystem;

import Table.Customer;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import static saleSystem.SaleManagementUtil.loginEmployee;
import static saleSystem.SaleManagementUtil.manageableDatabase;


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

    //private ArrayList<Reservation> reservationList = new ArrayList<>();
    private ArrayList<String> customer_id_List = new ArrayList<>();
    private ReservationPayment reservationPayment = new ReservationPayment();
    private Reservation reservationCustomer = new Reservation() ;
    private Customer customer = new Customer();

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
                setReservationCustomerFromGUI();
                customer_id_List.add(customer.getCustomerID());
                manageableDatabase.insertData(customer);

                //when customer inserted
                clearText();
                Customer newCustomer = new Customer();
                customer = newCustomer;
            }

            else if (oldCustomer.isSelected()){ //search name
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

                //insert payment into database
                setReservationPaymentFromGUI();
                reservationPayment.setCustomerID(customer_id_List.get(0));

                //insert reservarion payment to database
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

    public void setReservationCustomerFromGUI(){

        reservationCustomer.setReservationCode(reserveCode.getText());
        reservationCustomer.setCustomerID(customer.getCustomerID());
        reservationCustomer.setTourID(tourIDComboBox.getSelectionModel().getSelectedItem());
        reservationCustomer.setEmployeeID(loginEmployee.getEmployee_ID());

    }
    public void setReservationPaymentFromGUI(){

        reservationPayment.setReservationCode(reserveCode.getText());
        reservationPayment.setCustomerID(customer.getCustomerID());
        reservationPayment.setTourID(tourIDComboBox.getSelectionModel().getSelectedItem());
        reservationPayment.setEmployeeID(loginEmployee.getEmployee_ID());
        reservationPayment.setAmountCustomer(Integer.valueOf(customerNo.getText()));

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

        lastReserveCode = manageableDatabase.getLastReservationCode();
        currentReserveCode = String.format("%08d",Integer.parseInt(lastReserveCode) + 1) ;

        return currentReserveCode;
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
