package saleSystem;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML private JFXButton reserveBtn;
    @FXML private JFXButton customerManageBtn;
    @FXML private JFXButton tourCheckBtn;
    @FXML private JFXButton invoiceBtn;
    @FXML private JFXButton receiptBtn;
    @FXML private JFXButton logoutBtn;
    @FXML private Label loginNameLabel;


    @FXML
    public void handleInvoiceBtn(ActionEvent event) throws IOException {
        invoiceBtn.getScene().getWindow().hide();

    }

    @FXML
    public void handleLogoutBtn(ActionEvent event) throws IOException {
        logoutBtn.getScene().getWindow().hide();

    }

    @FXML
    public void handleCustomerManageBtn(ActionEvent event) throws IOException {
        customerManageBtn.getScene().getWindow().hide();


    }

    @FXML
    public void handleReceiptBtn(ActionEvent event) throws IOException {
        receiptBtn.getScene().getWindow().hide();


    }

    @FXML
    public void handleReserveBtn(ActionEvent event) throws IOException {
        reserveBtn.getScene().getWindow().hide();

    }

    @FXML
    public void handleTourCheckBtn(ActionEvent event) throws IOException {
        tourCheckBtn.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // set user login by call from database
        //showUserLogined.setText("Thikamporn Simud");
        //homePane.toFront();
        //reservePane.setVisible(false);

    }

    public void setLoginName(String txt){
        loginNameLabel.setText(txt);
    }

    /*SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));

    ObservableList<String> nameENTitleChoices = FXCollections.observableArrayList("Miss", "Mrs.", "Mr.", "Master", "Professor", "Assistant Professor", "Associate Professor");
        nameTitleEN.getSelectionModel().selectFirst();
        nameTitleEN.setValue("Miss");
        nameTitleEN.getItems().addAll(nameENTitleChoices);

    ObservableList<String> nameTHTitleChoices = FXCollections.observableArrayList("นางสาว", "นาง", "นาย", "เด็กหญิง", "เด็กชาย", "ศาตราจารย์", "ผู้ช่วยศาสตราจารย์", "รองศาสตราจารย์");
        nameTitleTH.getSelectionModel().selectFirst();
        nameTitleTH.setValue("นางสาว");
        nameTitleTH.getItems().addAll(nameTHTitleChoices);

    ObservableList<String> travelServices = FXCollections.observableArrayList("ชื่อเสียงของบริษัท", "โปรแกรมทัวร์", "ราคา", "การบริการของพนักงานขาย", "วันเวลาเดินทาง");
        travelServiceChoice.setValue("ชื่อเสียงของบริษัท");
        travelServiceChoice.setItems(travelServices);

    ObservableList<String> travelFocus = FXCollections.observableArrayList("ที่พัก", "ร้านอาหาร", "ยานพาหนะ", "ระยะเวลาชมสถานที่ท่องเที่ยว", "ไกด์/มัคคุเทศน์");
        travelFocusingChoice.setValue("ที่พัก");
        travelFocusingChoice.setItems(travelFocus);*/

}
