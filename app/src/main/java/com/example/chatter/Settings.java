package com.example.chatter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.chatter.databinding.ActivityChatContentBinding;
import com.example.chatter.databinding.ActivitySettingsBinding;

public class Settings extends AppCompatActivity {

    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_settings);
        setContentView(binding.getRoot());

        binding.btnExitSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}