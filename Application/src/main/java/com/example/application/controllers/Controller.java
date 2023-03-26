package com.example.application.controllers;

import com.example.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {

        /**
         * Авторизация пользователя при вооде данных в поля
         * "Логин" и "Пароль"
         */
        authSignInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);
            else
                System.out.println("Поля \"Логин\" и \"Пароль\" не заполнены!");

        });

        /**
         * Вызов метода setOnAction, при нажатии на кнопку "Зарегистрироваться",
         * который выполняет событие закрытия главного окна и появления нового окна
         * для регистрации пользователей.
         */
        loginSignUpButton.setOnAction(event -> {
            loginSignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader(Application.class.getResource("signUp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("Путь к файлу указан неверно!");
                e.printStackTrace();
            }

            /**
             * Отображение нужного нам окна, т.е FXML файл
             */
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

    }

    private void loginUser(String loginText, String loginPassword) {

    }

}
