package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservePageController implements Initializable {

    @FXML
    private ChoiceBox<?> titleNameTH;

    @FXML
    private TextField firstNameTH;

    @FXML
    private TextField lastNameTH;

    @FXML
    private ChoiceBox<?> titleNameEN;

    @FXML
    private TextField firstNameEN;

    @FXML
    private TextField lastNameEN;

    @FXML
    private ChoiceBox<?> genderChoice;

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
    private ChoiceBox<?> hearAboutUsChoice;

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
    private ChoiceBox<?> tourCodeChioce;

    @FXML
    private JFXCheckBox oldCustomer;

    @FXML
    private JFXCheckBox newCustomer;

    @FXML
    void handleAddCustomerBtn(ActionEvent event) {

    }

    @FXML
    void handleSearchCustomerBtn(ActionEvent event){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        SaleManagementUtil.setTitleNameTH(titleNameTH);
        SaleManagementUtil.setTitleNameEN(titleNameEN);
        SaleManagementUtil.setGender(genderChoice);
    }
}
