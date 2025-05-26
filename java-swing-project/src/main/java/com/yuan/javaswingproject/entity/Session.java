package com.yuan.javaswingproject.entity;

import lombok.Data;


public class Session {

    private static String currentUsername;

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void clear() {
        currentUsername = null;
    }
}
