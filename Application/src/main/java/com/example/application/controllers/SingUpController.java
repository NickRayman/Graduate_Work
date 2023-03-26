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
    private TextField login_field1;

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
    private TextField signUpLastName;

    @FXML
    private TextField signUpName;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {
            signNewUser();
        });
    }

    private void signNewUser() {

        DataBaseHandler dbHandler = new DataBaseHandler();

        String firstName = signUpName.getText();
        String lastName = signUpLastName.getText();
        String userName = login_field1.getText();
        String password = password_field.getText();
        String location = signUpCountry.getText();
        String gender = "";
        if (signUpCheckBoxMale.isSelected())
            gender = "Мужской";
        else
            gender = "Женский";

        User user = new User(firstName, lastName,
                userName, password, location, gender);

        dbHandler.signUpUser(user);
    }
}
