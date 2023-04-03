package com.example.application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.application.Application;
import com.example.application.DB.DataBaseHandler;
import com.example.application.configs.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SingUpController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button comeBackButton;

    @FXML
    private Label labelCountry;

    @FXML
    private Label labelINN;

    @FXML
    private Label labelLastName;

    @FXML
    private Label labelLogin;

    @FXML
    private Label labelMale;

    @FXML
    private Label labelMiddleName;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelSNILS;

    @FXML
    private Label labelSeriesNumberPassport;

    @FXML
    private Label labelTelephone;

    @FXML
    private Label labelInformation;

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

        /**
         * Регистрация пользователя при вооде данных в поля
         * данных о пользователе
         */
        signUpButton.setOnAction(event -> {
            signNewUser();
        });

        /**
         * Вернуться на главный экран
         */
        comeBackButton.setOnAction(event -> {
            openNewScene("view.fxml");
        });
    }

    /**
     * Метод signNewUser() регистрирует пользователя в БД
     */
    private void signNewUser() {

        resetLabel();

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

        if (signUpCheckBoxMale.isSelected() && signUpCheckBoxFemale.isSelected())
            gender = "Undefined";
        else if (signUpCheckBoxMale.isSelected())
            gender = "Мужской";
        else if (signUpCheckBoxFemale.isSelected())
            gender = "Женский";


        User user = new User(firstName, middleName,
                lastName, userName, gender, password,
                location, telephone, seriesNumberPassport,
                SNILS, INN);

        if (user.dataChecking()) {
            labelInformation.setText("Пользователь зарегистрирован");
            dbHandler.signUpUser(user);
        } else {
            labelInformation.setText("Пользователь не зарегистрирован");
            if (user.getFirstName().equals(""))
                labelName.setText("Заполните поле \"Имя\"");
            if (user.getMiddleName().equals(""))
                labelMiddleName.setText("Заполните поле \"Отчество\"");
            if (user.getLastName().equals(""))
                labelLastName.setText("Заполните поле \"Фамилия\"");
            if (user.getUserName().equals(""))
                labelLogin.setText("Заполните поле \"Логин\"");
            if (user.getGender().equals(""))
                labelMale.setText("Выберете пол");
            if (user.getGender().equals("Undefined"))
                labelMale.setText("Выберете один пол");
            if (user.getPassword().equals(""))
                labelPassword.setText("Заполните поле \"Пароль\"");
            if (user.getLocation().equals(""))
                labelCountry.setText("Заполните поле \"Страна\"");
            if (user.getTelephone().equals(""))
                labelTelephone.setText("Заполните поле \"Телефон\"");
            if (user.getSeriesNumberPassport().equals(""))
                labelSeriesNumberPassport.setText("Заполните поле \"Серия и номер паспорта\"");
            if (user.getSNILS().equals(""))
                labelSNILS.setText("Заполните поле \"СНИЛС\"");
            if (user.getINN().equals(""))
                labelINN.setText("Заполните поле \"ИНН\"");
        }


    }

    /**
     * Метод openNewScene(String window) открывает главное оконо авторизации
     * при прожатии кнопки "Назад"
     */
    private void openNewScene(String window) {
        comeBackButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(Application.class.getResource(window));
        try {

            /**
             * Отображение нужного нам окна, т.е FXML файл
             */

            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("My first App");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Путь к файлу указан неверно!");
            e.printStackTrace();
        }
    }

    /**
     * Обнуляет все метки в окне "Регистрации"
     */
    private void resetLabel() {
        labelInformation.setText("");
        labelName.setText("");
        labelMiddleName.setText("");
        labelLastName.setText("");
        labelLogin.setText("");
        labelPassword.setText("");
        labelCountry.setText("");
        labelTelephone.setText("");
        labelSeriesNumberPassport.setText("");
        labelSNILS.setText("");
        labelINN.setText("");
        labelMale.setText("");
    }
}
