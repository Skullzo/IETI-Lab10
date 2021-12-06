package com.example.taskplanner;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.taskplanner.databinding.FragmentFirstBinding;
import com.example.taskplanner.network.RetrofitGenerator;
import com.example.taskplanner.network.dto.LoginDto;
import com.example.taskplanner.network.dto.TokenDto;
import com.example.taskplanner.network.service.AuthService;
import com.example.taskplanner.network.storage.SharedPreferencesStorage;
import com.example.taskplanner.network.storage.Storage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Retrofit;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private SharedPreferences sharedPreferences;
    private Storage storage;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        binding.buttonFirst.setOnClickListener(v -> {
            super.onViewCreated(view, savedInstanceState);
            sharedPreferences = requireContext().getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
            storage = new SharedPreferencesStorage(sharedPreferences);
            sendAuthRequest();
        });
    }

    private void sendAuthRequest() {
        Retrofit retrofit = RetrofitGenerator.getInstance(storage);
        AuthService authService = retrofit.create(AuthService.class);
        LoginDto loginDto = new LoginDto("santiago@mail.com", "passw0rd");
        Action1<TokenDto> successAction = tokenDto -> onSuccess(tokenDto.getAccessToken());
        Action1<Throwable> failedAction = throwable -> Log.e("Developer", "Auth error", throwable);
        authService.auth(loginDto)
                .observeOn(Schedulers.from(ContextCompat.getMainExecutor(requireContext())))
                .subscribe(successAction, failedAction);
    }

    private void onSuccess(String token) {
        Log.d("Developer", "Token: " + token);
        binding.textviewFirst.setText(token);
        storage.saveToken(token);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}