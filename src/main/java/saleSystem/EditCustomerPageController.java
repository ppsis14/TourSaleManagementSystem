package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditCustomerPageController implements Initializable {
    @FXML
    private StackPane rootPane;

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
    private TextField cellphoneClient;

    @FXML
    private TextField homeTelClient;

    @FXML
    private TextField homeFaxClient;

    @FXML
    private TextField emailClient;

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
    private ChoiceBox<?> tourCodeChioce1;

    @FXML
    private JFXButton saveDataBtn;

    @FXML
    void handleSaveDataBtn(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Do yo want to save?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK){
            System.out.println("hello world");

        }


        /*JFXDialogLayout content = new JFXDialogLayout();
        //content.setHeading(new Label("Confirm to save customer information"));
        content.setBody(new Label("Do you want to save and exit?"));
        JFXDialog dialog = new JFXDialog(rootPane, content, JFXDialog.DialogTransition.CENTER);

        JFXButton yesBtn = new JFXButton("Yes");
        yesBtn.getStyleClass().add("dialog-button");
        yesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        JFXButton noBtn = new JFXButton("No");
        noBtn.getStyleClass().add("dialog-button");
        noBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        content.setActions(yesBtn, noBtn);
        dialog.show();*/


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.setTitleNameTH(titleNameTH);
        SaleManagementUtil.setTitleNameEN(titleNameEN);
        SaleManagementUtil.setGender(genderChoice);

    }
}
