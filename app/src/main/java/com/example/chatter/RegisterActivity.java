package com.example.chatter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatter.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        binding.btnRegister.setOnClickListener(v -> {
            singleAPI.setRegisterAPI();
            singleAPI.getRegisterAPI().post(binding.editTextTextUserName.getText().toString(),
                    binding.editTextTextPassword.getText().toString(),
                    binding.editTextTextNickname.getText().toString());
            Token.setUserID(binding.editTextTextUserName.getText().toString());
            Intent intent = new Intent(this, ChatsActivity.class);

            startActivity(intent);
        });


    }
}