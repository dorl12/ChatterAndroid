package com.example.chatter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
            finish();
        });
    }
}