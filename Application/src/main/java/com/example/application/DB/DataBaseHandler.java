package com.example.application.DB;

import com.example.application.configs.Configs;
import com.example.application.configs.Const;
import com.example.application.configs.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

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

    /**
     * Метод signUpUser(User user) добавляет зарегистрированного пользователя
     * в БД
     *
     * @param user
     */
    public void signUpUser(User user) {

        /**
         * SQL-запрос для вставки данных в поля таблицы Users
         */
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_FIRSTNAME + "," + Const.USER_MIDDLE_NAME + "," +
                Const.USER_LASTNAME + "," + Const.USER_USERNAME + "," +
                Const.USER_GENDER + "," + Const.USER_PASSWORD + "," +
                Const.USER_LOCATION + "," + Const.USER_TELEPHONE + "," +
                Const.USER_SERIES_NUMBER_PASSPORT + "," + Const.USER_SNILS + "," +
                Const.USER_INN + ")" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getMiddleName());
            prSt.setString(3, user.getLastName());
            prSt.setString(4, user.getUserName());
            prSt.setString(5, user.getGender());
            prSt.setString(6, user.getPassword());
            prSt.setString(7, user.getLocation());
            prSt.setString(8, user.getTelephone());
            prSt.setString(9, user.getSeriesNumberPassport());
            prSt.setString(10, user.getSNILS());
            prSt.setString(11, user.getINN());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод getUser(User user) возращает авторизированного пользователя
     * в БД
     * @param user
     * @return
     */
    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        /**
         * SQL-запрос для нахождения пользоваетелей по введенным данным "Логин"
         * и "Пароль"
         */
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USER_USERNAME + "=? AND " + Const.USER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}
