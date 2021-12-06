package com.example.taskplanner.network.interceptor;

import androidx.annotation.NonNull;

import com.example.taskplanner.network.storage.Storage;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private final Storage storage;

    public AuthInterceptor(Storage storage) {
        this.storage = storage;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder request = chain.request().newBuilder();
        String token = storage.getToken();
        if (!token.isEmpty()) {
            request.addHeader("Authorization", "Bearer " + token);
        }
        return chain.proceed(request.build());
    }
}