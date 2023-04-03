package com.example.application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button comeBackButton;

    @FXML
    void initialize() {
        comeBackButton.setOnAction(event -> {
            openNewScene("view.fxml");
        });
    }

    public void openNewScene(String window) {
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
}
