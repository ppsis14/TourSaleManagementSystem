package saleSystem;

import Table.Employee;
import Table.TourPackage;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import databaseConnection.ManageableDatabase;
import databaseConnection.SpringJDBC_DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleManagementUtil {

    public static ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

    public static ManageableDatabase manageableDatabase = context.getBean("SpringJDBC_DB", SpringJDBC_DB.class);

    public static Employee loginEmployee = new Employee();


    public static void loadWindow(URL loc, String title){
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            stage.setResizable(false);

        } catch (IOException e) {
            Logger.getLogger(FXMLLoader.class.getName()).log(Level.SEVERE,null, e);
        }
    }

    public static void initDrawerToolBar(JFXDrawer drawerMenu, JFXHamburger menu, URL loc){
        try {
            VBox toolbar = FXMLLoader.load(loc);
            drawerMenu.setSidePane(toolbar);
            drawerMenu.setDefaultDrawerSize(100);
        } catch (IOException e) {
            Logger.getLogger(FXMLLoader.class.getName()).log(Level.SEVERE,null, e);
        }

        HamburgerNextArrowBasicTransition hamMenu = new HamburgerNextArrowBasicTransition(menu);
        hamMenu.setRate(-1);
        menu.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            hamMenu.setRate(hamMenu.getRate()*-1);
            hamMenu.play();
            if (drawerMenu.isHidden()) drawerMenu.open();
            else drawerMenu.close();

        });
    }

    public static void setTitleNameTH(ChoiceBox choiceBox){
        ObservableList<String> titleNameTHChoices = FXCollections.observableArrayList("นางสาว", "นาง", "นาย", "เด็กหญิง", "เด็กชาย", "ศาตราจารย์", "ผู้ช่วยศาสตราจารย์", "รองศาสตราจารย์");
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.setValue("นางสาว");
        choiceBox.getItems().addAll(titleNameTHChoices);
    }
    public static void setTitleNameEN(ChoiceBox choiceBox){
        ObservableList<String> titleNameENChoices = FXCollections.observableArrayList("Miss", "Mrs.", "Mr.", "Master", "Professor", "Assistant Professor", "Associate Professor");
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.setValue("Miss");
        choiceBox.getItems().addAll(titleNameENChoices);
    }

    public static void setGender(ChoiceBox choiceBox){
        ObservableList<String> genderChoices = FXCollections.observableArrayList("Female", "Male");
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.setValue("Female");
        choiceBox.getItems().addAll(genderChoices);
    }

    public static void setHearAboutUs(ComboBox comboBox){
        ObservableList<String> hereAboutUsChoices = FXCollections.observableArrayList("Bangkokbizs News", "Daily News", "Komchadluek", "Website", "E-News", "SMS", "TV Ads", "Others");
        comboBox.getSelectionModel().selectFirst();
        comboBox.setValue("Bangkokbizs News");
        comboBox.getItems().addAll(hereAboutUsChoices);

    }

    public static void setTourID(ComboBox comboBox){

        List<TourPackage> tourPackageList = manageableDatabase.getAllTourPackage();
        ObservableList<String> tourNameList = FXCollections.observableArrayList();

        for (TourPackage tourPackage: tourPackageList) {
            if(tourPackage.getStatus().equalsIgnoreCase("open"))
                tourNameList.add(tourPackage.getTourName());
        }
        comboBox.getSelectionModel().selectFirst();
        comboBox.setValue(tourNameList.get(0));
        comboBox.getItems().addAll(tourNameList);

    }





}
