package com.example.application.Server;

/**
 * Создаю класс в котором будет производится создания и вызовы функций;
 */
public class ServerMain {
    public static void main(String[] args) {
        ServerDataBase serverDataBase = new ServerDataBase();
        serverDataBase.run();
    }
}
