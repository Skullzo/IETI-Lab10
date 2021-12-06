package com.example.taskplanner.network.storage;

public interface Storage {
    void saveToken(String token);
    String getToken();
}