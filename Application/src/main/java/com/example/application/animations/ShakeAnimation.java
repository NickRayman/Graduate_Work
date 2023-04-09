package com.example.application.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;


/**
 * Класс ShakeAnimation выполняет функцию анимации оконого приложения
 */
public class ShakeAnimation {
    private TranslateTransition transition;

    /**
     * Конструктор hakeAnimation(Node node) с параметром объекта
     * текстового поля оконного приложения
     */
    public ShakeAnimation(Node node) {
        transition = new TranslateTransition(Duration.millis(70), node);
        transition.setFromX(0f);
        transition.setByX(10f);
        transition.setCycleCount(3);
        transition.setAutoReverse(true);
    }

    public void playAnime() {
        transition.playFromStart();
    }
}
