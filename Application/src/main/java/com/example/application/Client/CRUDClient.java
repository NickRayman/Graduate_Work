package com.example.application.Client;

import com.example.application.configs.Operation;
import com.example.application.configs.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CRUDClient {


    /**
     *Метод resultUser(User user) для получения int значения с сервера
     */
    public int resultUser(User user) {
        int counter = 0;

        /**
         * Этой строкой мы запрашиваем у сервера доступ на соединение
         */
        try (Socket clientSocket = new Socket("127.0.0.1", 8080)) {
            counter = runGetUser(clientSocket, user);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Объект не передался");
        }
        return counter;
    }

    /**
     *Метод resultFullUser(User user) для получения обекта User с сервера
     */
    public User resultFullUser(User user) {
        User fullUser = null;

        /**
         * Этой строкой мы запрашиваем у сервера доступ на соединение
         */
        try (Socket clientSocket = new Socket("127.0.0.1", 8080)) {
            fullUser = runGetFullUser(clientSocket, user);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Объект не передался");
        }
        return fullUser;
    }

    /**
     *Метод resultAddUser(User user) для добавления обекта User на сервер
     */
    public String resultAddUser(User user) {

        String message = "";
        /**
         * Этой строкой мы запрашиваем у сервера доступ на соединение
         */
        try (Socket clientSocket = new Socket("127.0.0.1", 8080)) {
           message = runAddUser(clientSocket, user);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Объект не передался");
        }
        return message;
    }

    /**
     * Метод runGetUser(Socket clientSocket, User user) делает так, что сервер возращает int значение,
     * если пользователь есть в БД
     */
    private int runGetUser(Socket clientSocket, User user) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {

            System.out.println("Клиент заработал");

            user.setOperation(Operation.GET_USER);

            objectOutputStream.writeObject(user);

            System.out.println("Клиент отправил объект");

            objectOutputStream.flush();

            int counter = (int) objectInputStream.readObject();
            System.out.println("Клиент получил объект");

            return counter;

        }
    }

    /**
     * Метод runGetFullUser(Socket clientSocket, User user) делает так, что сервер возращает полного пользователя в БД
     */
    private User runGetFullUser(Socket clientSocket, User user) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {

            System.out.println("Клиент заработал");

            user.setOperation(Operation.GET_FULL_USER);

            objectOutputStream.writeObject(user);

            System.out.println("Клиент отправил объект");

            objectOutputStream.flush();

            User userFromServer = (User) objectInputStream.readObject();
            System.out.println("Клиент получил объект");

            return userFromServer;

        }
    }

    /**
     * Метод runAddUser(Socket clientSocket, User user) делает так, что сервер добавляет введенного пользователя в БД
     */
    private String runAddUser(Socket clientSocket, User user) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {

            System.out.println("Клиент заработал");

            user.setOperation(Operation.ADD_USER);

            objectOutputStream.writeObject(user);

            System.out.println("Клиент отправил объект");

            objectOutputStream.flush();

            String addUser = (String) objectInputStream.readObject();
            System.out.println("Клиент получил объект");

            return addUser;

        }
    }
}
