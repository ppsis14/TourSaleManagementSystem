package saleSystem;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservePageController implements Initializable {

    @FXML private Pane reservePane;
    @FXML private JFXButton homeBtn;
    @FXML private JFXButton reserveBtn;
    @FXML private JFXButton memberBtn;
    @FXML private JFXButton tourCheckBtn;
    @FXML private JFXButton invoiceBtn;
    @FXML private JFXButton ReceiptBtn;
    @FXML private JFXButton logoutBtn;
    @FXML private JFXButton bckHomeBtn;
    @FXML private Pane homePane;
    @FXML private Label showUserLogin;

    @FXML private JFXTextField th_Firstname;
    @FXML private JFXTextField th_Lastname;
    @FXML private JFXTextField en_Firstname;
    @FXML private JFXTextField en_Lastname;
    @FXML private JFXTextField passpotNumber;
    @FXML private JFXDatePicker expireDate;
    @FXML private JFXTextField mobileNumber;
    @FXML private JFXCheckBox showMember;
    @FXML private JFXTextArea address;


    @FXML
    void handleBackHomeFromReserveBtn() throws IOException {
        bckHomeBtn.getScene().getWindow().hide();
        Stage reserveToHomeWindow = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/homePage.fxml"));
        Scene scene = new Scene(root);
        reserveToHomeWindow.setScene(scene);
        reserveToHomeWindow.show();
        reserveToHomeWindow.setResizable(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set user login by call from database
        //showUserLogin.setText("Thikamporn Simud");
        //homePane.toFront();
        //reservePane.setVisible(false);

//        //record reservation
//        Connection connection = DbConnect.getInstance().getConnection();
//        Statement statement = connection.createStatement();
//
//        ResultSet resultSet = statement.executeQuery("insert into reserve_card_database (FirstnameTH,LastnameTH,FirstnameENG,LastnameENG,Passport_no,Expire_passport_date,Present_address,Mobile_num)"+
//                "values ('"+th_Firstname.getText()+"','"+th_Lastname.getText()+"','"+en_Firstname.getText()+"','"+en_Lastname.getText()+"'," +
//                "'"+passpotNumber.getText()+"','"+expireDate.getPromptText()+"','"+address.getText()+"','"+mobileNumber.getText()+"')");


    }

    public void setLoginName(String name){
        showUserLogin.setText(name);
    }
}
