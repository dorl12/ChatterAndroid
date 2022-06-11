package com.example.chatter;

public class Token {
    private static String token;

    private Token() {
        token = "";
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String newToken) {
        token = newToken;
    }
}
