package com.example.chatter;

public class AppService {
    private static String token;
    private static String userID;
    private static Boolean isDark;
    private static String baseURL = MyApplication.getContext().getString(R.string.BaseURL);

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


    public static String parseTime(String time) {
        String parsedTime = time.substring(11, 16);
        return parsedTime;
    }

    public static Boolean getIsDark() {
        return isDark;
    }

    public static void setIsDark(Boolean isDark) {
        AppService.isDark = isDark;
    }

    public static String getBaseURL() {
        return baseURL;
    }

    public static void setBaseURL(String baseURL) {
        AppService.baseURL = baseURL;
    }

    public static String fixServer(String server) {
        if(server.contains("localhost")) {
            return "10.0.2.2:5267";
            //return server.replace("localhost", "10.0.2.2");
        }
        return server;
    }
}
