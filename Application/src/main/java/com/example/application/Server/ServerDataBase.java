package com.example.application.Server;

import com.example.application.DB.DataBaseHandler;
import com.example.application.configs.Operation;
import com.example.application.configs.User;
import org.apache.log4j.Logger;


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
     * Поле логгер
     */
    private Logger logger = Logger.getLogger(ServerDataBase.class);

    /**
     * Конструктор
     */
    public ServerDataBase() {
        this.dataBaseHandler = new DataBaseHandler();
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            logger.info("Сервер заработал");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                runWithDataBaseFromClient(clientSocket);
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error("Сервер не отправил сообщение", e);
        }
    }

    /**
     * Метод runWithDataBaseFromClient(Socket clientSocket) работает с объектами клиента
     */
    private void runWithDataBaseFromClient(Socket clentSocket) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clentSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clentSocket.getInputStream())) {

            User user = (User) objectInputStream.readObject();

            logger.info("Сервер получил объект - User");

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
                logger.info("Сервер отправил объект - Integer");

                /**
                 * Выталкиваем все из буфера
                 */
                objectOutputStream.flush();

            } else if (user.getOperation().equals(Operation.GET_FULL_USER)) {
                dataBaseHandler.getFullUser(user);
                objectOutputStream.writeObject(user);
                logger.info("Сервер отправил объект - User");

                /**
                 * Выталкиваем все из буфера
                 */
                objectOutputStream.flush();
            } else if (user.getOperation().equals(Operation.ADD_USER)) {
                dataBaseHandler.signUpUser(user);
                String message = "Пользователь зарегистрирован";
                objectOutputStream.writeObject(message);
                logger.info("Сервер отправил объект - String");

                /**
                 * Выталкиваем все из буфера
                 */
                objectOutputStream.flush();
            }
        }
    }

}
