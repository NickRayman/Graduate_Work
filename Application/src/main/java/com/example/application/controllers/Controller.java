package com.example.application.controllers;

import com.example.application.Client.CRUDClient;
import com.example.application.ClientApplication;
import com.example.application.animations.ShakeAnimation;
import com.example.application.configs.User;
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


    /**
     * Ссылка на контроллер окна поиска текста
     */
    private AppController children;
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

    /**
     * Поле CRUDClient client для получения ответа c сервера
     */
    private CRUDClient client = new CRUDClient();

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

            openNewScene("signUp.fxml");
        });

    }

    private void loginUser(String loginText, String loginPassword) {
        //Сервер

        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);

        int counter = client.resultUser(user);


        if (counter >= 1) {
            System.out.println("Пользоваетель найден!");
            openAppController("app.fxml");

        } else {
            System.out.println("Данный пользователь не зарегистрирован!");
            ShakeAnimation userLoginAnime = new ShakeAnimation(login_field);
            ShakeAnimation userPassword = new ShakeAnimation(password_field);
            userLoginAnime.playAnime();
            userPassword.playAnime();
        }
    }

    public void openNewScene(String window) {
        loginSignUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(ClientApplication.class.getResource(window));

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

    }

    public void openAppController(String window) {
        loginSignUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(ClientApplication.class.getResource(window));

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
        children = loader.getController();
        children.setParent(this);
        stage.showAndWait();
    }

    /**
     * Геттеры
     */
    public TextField getLogin_field() {
        return login_field;
    }

    public PasswordField getPassword_field() {
        return password_field;
    }
}
