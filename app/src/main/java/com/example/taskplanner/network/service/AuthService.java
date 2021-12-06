package com.example.taskplanner.network.service;

import com.example.taskplanner.network.dto.LoginDto;
import com.example.taskplanner.network.dto.TokenDto;


import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface AuthService {
    @POST("auth")
    Observable<TokenDto> auth(@Body LoginDto loginDto);
}