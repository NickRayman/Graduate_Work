package com.example.application.DB;

import com.example.application.configs.Configs;
import com.example.application.configs.Const;
import com.example.application.configs.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Класс DataBaseHandler выполняет работу с БД;
 */
public class DataBaseHandler extends Configs {
    Connection dbConnection;

    /**
     * Метод getDbConnection выполняет проверку соединения с базой данных;
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(User user) {

        /**
         * SQL-запрос для вставки данных в поля таблицы Users
         */
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME + "," +
                Const.USER_USERNAME + "," + Const.USER_PASSWORD + "," +
                Const.USER_LOCATION + "," + Const.USER_GENDER + ")" +
                "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getLocation());
            prSt.setString(6, user.getGender());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
