package com.example.application.Server;

import com.example.application.DB.DataBaseHandler;
import com.example.application.configs.Operation;
import com.example.application.configs.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс ServerDataBase сервера, который будет
 * получать и отправлять информацию клиенту
 */
public class ServerDataBase {

    /**
     * Поле dataBaseHandler для работы с БД
     */
    private DataBaseHandler dataBaseHandler;

    /**
     * Конструктор
     */
    public ServerDataBase() {
        this.dataBaseHandler = new DataBaseHandler();
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                runWithDataBaseFromClient(clientSocket);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Сервер не отправил сообщение");
        }
    }

    /**
     * Метод runWithDataBaseFromClient(Socket clientSocket) работает с объектами клиента
     */
    private void runWithDataBaseFromClient(Socket clentSocket) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clentSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clentSocket.getInputStream())) {

            System.out.println("Сервер, заработал");

            User user = (User) objectInputStream.readObject();

            System.out.println("Сервер получил объект");

            if (user.getOperation().equals(Operation.GET_USER)) {

                ResultSet result = dataBaseHandler.getUser(user);
                int counter = 0;
                try {
                    while (result.next()) {
                        counter++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                objectOutputStream.writeObject(counter);
                System.out.println("Сервер отправил объект - Integer");

                /**
                 * Выталкиваем все из буфера
                 */
                objectOutputStream.flush();

            } else if (user.getOperation().equals(Operation.GET_FULL_USER)) {
                dataBaseHandler.getFullUser(user);
                objectOutputStream.writeObject(user);
                System.out.println("Сервер отправил объект - User");

                /**
                 * Выталкиваем все из буфера
                 */
                objectOutputStream.flush();
            } else if (user.getOperation().equals(Operation.ADD_USER)) {
                dataBaseHandler.signUpUser(user);
                String message = "Пользователь зарегистрирован";
                objectOutputStream.writeObject(message);
                System.out.println("Сервер отправил объект - String");

                /**
                 * Выталкиваем все из буфера
                 */
                objectOutputStream.flush();
            }
        }
    }

}
