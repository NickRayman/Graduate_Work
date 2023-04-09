package com.example.application.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.application.Client.CRUDClient;
import com.example.application.ClientApplication;
import com.example.application.DB.DataBaseHandler;
import com.example.application.configs.Const;
import com.example.application.configs.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class AppController {

    private Controller controller;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button comeBackButton;

    @FXML
    private Button downloadButton;

    /**
     * Поле CRUDClient client для получения ответа c сервера
     */
    private CRUDClient client = new CRUDClient();


    @FXML
    void initialize() {
        comeBackButton.setOnAction(event -> {
            openNewScene("view.fxml");
        });

        downloadButton.setOnAction(event -> {
            downloadUserData();
        });
    }

    /**
     * Метод openNewScene(String window) открывает главное оконо авторизации
     * при прожатии кнопки "Назад"
     */
    public void openNewScene(String window) {
        comeBackButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(ClientApplication.class.getResource(window));
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
     * Метод downloadUserData() скачивает данные пользователя в папку FileXLSX
     * в формате .xls при прожатии кнопки "Скачать данные на пк"
     */
    public void downloadUserData() {

        //сервер

        User user = new User();
        user.setUserName(controller.getLogin_field().getText().trim());
        user.setPassword(controller.getPassword_field().getText().trim());

        user = client.resultFullUser(user);

        List<String> constData = new ArrayList<>();
        constData.add(Const.USER_ID);
        constData.add(Const.USER_FIRSTNAME);
        constData.add(Const.USER_MIDDLE_NAME);
        constData.add(Const.USER_LASTNAME);
        constData.add(Const.USER_USERNAME);
        constData.add(Const.USER_GENDER);
        constData.add(Const.USER_PASSWORD);
        constData.add(Const.USER_LOCATION);
        constData.add(Const.USER_TELEPHONE);
        constData.add(Const.USER_SERIES_NUMBER_PASSPORT);
        constData.add(Const.USER_SNILS);
        constData.add(Const.USER_INN);

        List<String> userData = new ArrayList<>();
        userData.add(String.valueOf(user.getId()));
        userData.add(String.valueOf(user.getFirstName()));
        userData.add(String.valueOf(user.getMiddleName()));
        userData.add(String.valueOf(user.getLastName()));
        userData.add(String.valueOf(user.getUserName()));
        userData.add(String.valueOf(user.getGender()));
        userData.add(String.valueOf(user.getPassword()));
        userData.add(String.valueOf(user.getLocation()));
        userData.add(String.valueOf(user.getTelephone()));
        userData.add(String.valueOf(user.getSeriesNumberPassport()));
        userData.add(String.valueOf(user.getSNILS()));
        userData.add(String.valueOf(user.getINN()));

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Данные пользователя");
        Row row = sheet.createRow(0);
        for (int i = 0; i < constData.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(constData.get(i));
            sheet.autoSizeColumn(i);
        }
        row = sheet.createRow(1);
        for (int i = 0; i < constData.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(userData.get(i));
            sheet.autoSizeColumn(i);
        }
        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\kolya\\OneDrive\\Рабочий стол\\" +
                user.getFirstName() + "_" + user.getMiddleName() + "_" +
                user.getLastName() +".xls", false)) {
            wb.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод setParent() устанавливает предка - этот контроллер
     */
    public void setParent (Controller controller) {
        this.controller = controller;
    }
}
