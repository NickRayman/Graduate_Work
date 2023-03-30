package com.example.application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.application.DB.DataBaseHandler;
import com.example.application.configs.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SingUpController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button signUpButton;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private TextField signUpCountry;

    @FXML
    private TextField signUpINN;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpMiddleName;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpPassport;

    @FXML
    private TextField signUpPhoneNumber;

    @FXML
    private TextField signUpSNILS;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {
            signNewUser();
        });
    }

    private void signNewUser() {

        DataBaseHandler dbHandler = new DataBaseHandler();

        String firstName = signUpName.getText();
        String middleName = signUpMiddleName.getText();
        String lastName = signUpLastName.getText();
        String userName = login_field.getText();
        String password = password_field.getText();
        String location = signUpCountry.getText();
        String telephone = signUpPhoneNumber.getText();
        String seriesNumberPassport = signUpPassport.getText();
        String SNILS = signUpSNILS.getText();
        String INN = signUpINN.getText();
        String gender = "";

        if (signUpCheckBoxMale.isSelected())
            gender = "Мужской";
        else
            gender = "Женский";

        User user = new User(firstName, middleName,
                lastName, userName, gender, password,
                location, telephone, seriesNumberPassport,
                SNILS, INN);

        dbHandler.signUpUser(user);
    }
}
