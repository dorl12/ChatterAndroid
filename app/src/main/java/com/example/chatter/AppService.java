package com.example.chatter;

public class AppService {
    private static String token;
    private static String userID;

    private AppService() {
        token = "";
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String newToken) {
        token = newToken;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String newUserID) {
        userID = newUserID;
    }
}
