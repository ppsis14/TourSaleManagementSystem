package saleSystem;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static saleSystem.SaleManagementUtil.loginEmployee;
import static saleSystem.SaleManagementUtil.manageableDatabase;


public class LoginPageController implements Initializable {

    @FXML private TextField username;
    @FXML private JFXButton cancelButton;
    @FXML private JFXButton loginButton;
    @FXML private PasswordField password;
    @FXML private Label showErrorLogin;

    @FXML
    public void handleCancelButton(ActionEvent event) {
        username.clear();
        password.clear();
        showErrorLogin.setText("");
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void handleLoginButton(ActionEvent event) throws IOException, SQLException {

        if(manageableDatabase.checkLogin(username.getText(),password.getText())){
            loginEmployee = manageableDatabase.getEmployeeLogin(username.getText(),password.getText());
            //load windows
            loginButton.getScene().getWindow().hide();
            SaleManagementUtil.loadWindow(getClass().getResource("/homePage.fxml"), "Home");

        }
        else{
            showErrorLogin.setText("Username or Password is not correct");
            username.clear();
            password.clear();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setVisible(true);
        password.setVisible(true);
    }
}




