package com.example.abdulwahab.mmt;

/**
 * Created by ABDUL WAHAB on 20-Apr-17.
 */
public class Config {
    public static String server = "http://192.168.43.212/php-android/";
    public static String data = "http://192.168.43.212/php-android/";
    //URL to our login.php file
    public static final String LOGIN_URL = Config.server + "login.inc.php";
    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "username";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_BIDAN = "BIDAN";
    public static final String LOGIN_DINKES = "dinkes";
    public static final String LOGIN_PUSKESMAS = "puskesmas";


    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";
    public static final String USER_SHARED_PREF = "datauser";
    public static final String BIDAN_SHARED_PREF = "bidan";
    public static final String NAMA_SHARED_PREF = "";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

}