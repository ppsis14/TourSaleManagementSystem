package saleSystem;

import Table.Customer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import static saleSystem.SaleManagementUtil.manageableDatabase;

public class EditCustomerPageController implements Initializable {
    @FXML
    private StackPane rootPane;

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
    private ComboBox<String> hearAboutUsChoices;

    @FXML
    private JFXButton saveDataBtn;


    private Customer customer = new Customer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.setTitleNameTH(titleNameTH);
        SaleManagementUtil.setTitleNameEN(titleNameEN);
        SaleManagementUtil.setGender(genderChoice);
        SaleManagementUtil.setHearAboutUs(hearAboutUsChoices);
        SaleManagementUtil.setDatePickerFormat(dateOfBirth);
        SaleManagementUtil.setDatePickerFormat(expPassportDate);

    }
    @FXML
    void handleSaveDataBtn(ActionEvent event) {

        setCustomerFromGUI();
        manageableDatabase.updateData(customer);

        /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType buttonTypeOne = new ButtonType("Submit");
        alert.getButtonTypes().setAll(buttonTypeOne, ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to save?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK){

            setCustomerFromGUI();
            manageableDatabase.updateData(customer);    //update data to database
            System.out.println("Update Successful");

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.close();
        }*/

        ButtonType foo = new ButtonType("Submit Reservation", ButtonBar.ButtonData.OK_DONE);
        ButtonType bar = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "The format for dates is year.month.day.For example, today is 29/11/2561.", foo, bar);

        alert.setTitle("Date format warning");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(bar) == foo) {
            System.out.println("Update Successful");

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.close();
        }

        /*JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Confirm Issue"));
        content.setBody(new Label("Do you want to save and exit?"));
        JFXDialog dialog = new JFXDialog(rootPane, content, JFXDialog.DialogTransition.CENTER);
        dialog.getStyleClass().add("dialog-button");
        dialog.setOverlayClose(false);

        JFXButton yesBtn = new JFXButton("Submit Reservation");
        yesBtn.getStylesheets().add("style.css");
        yesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        JFXButton noBtn = new JFXButton("No");
        //noBtn.getStylesheets().add("style.css");
        noBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        content.setActions(yesBtn, noBtn);
        dialog.show();*/


    }
    @FXML public void handleNotEatBeefCheckbox(ActionEvent event) { eatBeefY.setSelected(false); }
    @FXML public void handleEatBeefCheckbox(ActionEvent event) { eatBeefN.setSelected(false); }

    public void setCustomer(Customer editCustomer){
        this.customer = editCustomer;
        showDataForEditCustomer();
    }

    public void showDataForEditCustomer(){

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
        dateOfBirth.setValue(LocalDate.of(Integer.valueOf(dateCut[2]), Integer.valueOf(dateCut[1]), Integer.valueOf(dateCut[0])));
        passportNo.setText(customer.getPassport_no());
        String[] dateExpCut = customer.getExp_passport().split("-");
        expPassportDate.setValue(LocalDate.of(Integer.valueOf(dateExpCut[2]), Integer.valueOf(dateExpCut[1]), Integer.valueOf(dateExpCut[0])));
        occupation.setText(customer.getOccupation());
        //Contact
        address.setText(customer.getContactAddress());
        cellphoneClient.setText(customer.getCell_phone());
        homeTelClient.setText(customer.getHome_Tel());
        homeFaxClient.setText(customer.getFax());
        emailClient.setText(customer.getEmail());
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
        customer.setCell_phone(cellphoneClient.getText());
        customer.setHome_Tel(homeTelClient.getText());
        customer.setFax(homeFaxClient.getText());
        customer.setEmail(emailClient.getText());
        //moreInfo
        customer.setDisease(underlyingDisease.getText());
        customer.setFoodAllergy(foodAllergy.getText());
        customer.setEatBeef(eatBeefY.isSelected() ? eatBeefY.getText() : eatBeefN.getText());
        customer.setMoreDetail(moreDetail.getText());
        customer.setHearAboutUs(hearAboutUsChoices.getSelectionModel().getSelectedItem());
    }
}
