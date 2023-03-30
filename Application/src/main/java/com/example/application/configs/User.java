package com.example.application.configs;

/**
 * Класс User хранит поля данных
 * зарегистрированного пользователя
 */
public class User {

    /**
     * Поля данных пользователя
     */
    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String gender;
    private String password;
    private String location;
    private String telephone;
    private String seriesNumberPassport;
    private String SNILS;
    private String INN;


    /**
     * Пустой конструктор
     */
    public User() {
    }

    /**
     * Конструктор User с параметрами данных о пользователе
     * @param firstName
     * @param middleName
     * @param lastName
     * @param userName
     * @param gender
     * @param password
     * @param location
     * @param telephone
     * @param seriesNumberPassport
     * @param SNILS
     * @param INN
     */
    public User(String firstName, String middleName, String lastName, String userName, String gender, String password, String location, String telephone, String seriesNumberPassport, String SNILS, String INN) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.userName = userName;
        this.gender = gender;
        this.password = password;
        this.location = location;
        this.telephone = telephone;
        this.seriesNumberPassport = seriesNumberPassport;
        this.SNILS = SNILS;
        this.INN = INN;
    }

    /**
     * Сеттеры
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setSeriesNumberPassport(String seriesNumberPassport) {
        this.seriesNumberPassport = seriesNumberPassport;
    }

    public void setSNILS(String SNILS) {
        this.SNILS = SNILS;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    /**
     * Геттеры
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public String getGender() {
        return gender;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getSeriesNumberPassport() {
        return seriesNumberPassport;
    }

    public String getSNILS() {
        return SNILS;
    }

    public String getINN() {
        return INN;
    }
}
