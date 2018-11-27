package saleSystem;

import Table.Customer;
import Table.Reservation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.sun.javafx.binding.StringFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
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
    private TextField reserveCode;

    @FXML
    private Label customerNo;

    @FXML
    private ChoiceBox<String> tourCodeChoice;

    @FXML
    private JFXCheckBox oldCustomer;

    @FXML
    private JFXCheckBox newCustomer;
    @FXML
    private TextField searchByCustomerName;
    @FXML
    private Button searchCustomerBtn;
    @FXML private Label loginNameLabel;

    private Reservation reservation = null ;
    private Customer customer = new Customer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        SaleManagementUtil.setTitleNameTH(titleNameTH);
        SaleManagementUtil.setTitleNameEN(titleNameEN);
        SaleManagementUtil.setGender(genderChoice);
        SaleManagementUtil.setHearAboutUs(hearAboutUsChoices);
        reserveCode.setText(createReservationCode());
        loginNameLabel.setText(loginEmployee.getFirstName()+" "+loginEmployee.getLastName()+" [ "+loginEmployee.getPosition()+" ]");

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
/*
        //pop up 1
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to add customer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK){

            setCustomerFromGUI();
            reservation.addCustomerToReservationList(customer);
            System.out.println("จองสำเร็จ");

            //pop up 2
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Confirmation Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Do you want to add another customer?");
            Optional<ButtonType> action2 = alert.showAndWait();
            if (action2.get() == ButtonType.OK){

                Customer newCustomer = new Customer();
                customer = newCustomer;

            }



        }
        */

    }

    @FXML
    void handleSearchCustomerBtn(ActionEvent event){

    }

    public void setReservationFromGUI(){
        reservation.setReservationCode(reserveCode.getText());
        //reservation.setTourID(tourCodeChoice.getSelectionModel().getSelectedItem());
        reservation.setAmountCustomer(Integer.parseInt(customerNo.getText()));
        reservation.setEmployeeName(loginEmployee.getTitleName()+" "+loginEmployee.getFirstName()+" "+loginEmployee.getLastName());

    }

    public void setCustomerFromGUI(){
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
        String lastReservCode;
        String currentReservCode;

        lastReservCode = manageableDatabase.getLastReservationCode();
        currentReservCode = String.format("%06d",Integer.parseInt(lastReservCode) + 1) ;

        return currentReservCode;
    }


}
