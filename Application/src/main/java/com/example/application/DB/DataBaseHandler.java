package com.example.application.DB;

import com.example.application.configs.Configs;
import com.example.application.configs.Const;
import com.example.application.configs.User;
import org.mindrot.jbcrypt.BCrypt;

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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод getUser(User user) возращает авторизированного пользователя
     * в БД
     */
    public int getUser(User user) {
        ResultSet resSet = null;
        int counter = 0;

        /**
         * SQL-запрос для нахождения пользователя по введенному "Логину"
         */
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USER_USERNAME + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, user.getUserName());

            resSet = prSt.executeQuery();

            if (resSet.next()) {
                String hashedPasswordFromDB = resSet.getString(Const.USER_PASSWORD);

                String enteredPassword = user.getPassword();
                boolean passwordMatch = BCrypt.checkpw(enteredPassword, hashedPasswordFromDB);

                if (passwordMatch) {
                    // Пароль совпадает, выполните необходимые действия
                    return 1;
                } else {
                    // Пароль не совпадает, обработайте этот случай
                    return 0;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * Метод getFullUser(User user) возращает авторизированного пользователя
     * из БД
     */
    public void getFullUser(User user) {
        ResultSet resSet;

        /**
         * SQL-запрос для нахождения данных пользователя по введенным данным "Логин"
         * и "Пароль"
         */
        String select = "SELECT " + Const.USER_ID + "," + Const.USER_FIRSTNAME + "," +
                Const.USER_MIDDLE_NAME + "," + Const.USER_LASTNAME + "," +
                Const.USER_USERNAME + "," + Const.USER_GENDER + "," +
                Const.USER_PASSWORD + "," + Const.USER_LOCATION + "," +
                Const.USER_TELEPHONE + "," + Const.USER_SERIES_NUMBER_PASSPORT + "," +
                Const.USER_TELEPHONE + "," + Const.USER_SERIES_NUMBER_PASSPORT + "," +
                Const.USER_SNILS + "," + Const.USER_INN +
                " FROM " + Const.USER_TABLE + " WHERE " +
                Const.USER_USERNAME + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, user.getUserName());
            resSet = prSt.executeQuery();

            while (resSet.next()) {
                String hashedPasswordFromDB = resSet.getString(Const.USER_PASSWORD);

                String enteredPassword = user.getPassword();
                boolean passwordMatch = BCrypt.checkpw(enteredPassword, hashedPasswordFromDB);

                if (passwordMatch) {
                        // Пароль совпадает, выполните необходимые действия
                        user.setId(resSet.getInt(Const.USER_ID));
                        user.setFirstName(resSet.getString(Const.USER_FIRSTNAME));
                        user.setMiddleName(resSet.getString(Const.USER_MIDDLE_NAME));
                        user.setLastName(resSet.getString(Const.USER_LASTNAME));
                        user.setGender(resSet.getString(Const.USER_GENDER));
                        user.setLocation(resSet.getString(Const.USER_LOCATION));
                        user.setTelephone(resSet.getString(Const.USER_TELEPHONE));
                        user.setSeriesNumberPassport(resSet.getString(Const.USER_SERIES_NUMBER_PASSPORT));
                        user.setSNILS(resSet.getString(Const.USER_SNILS));
                        user.setINN(resSet.getString(Const.USER_INN));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
