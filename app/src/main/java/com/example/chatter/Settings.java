package com.example.chatter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.chatter.databinding.ActivitySettingsBinding;

public class Settings extends AppCompatActivity {

    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_settings);
        setContentView(binding.getRoot());

        androidx.appcompat.widget.SwitchCompat darkModeSwitch = binding.lightOrDarkSwitch;
        EditText serverEdtTxt = findViewById(R.id.edtTxtServer);
        Button changeServerButton = findViewById(R.id.btnChangeServer);

        changeServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppService.setBaseURL("http://" + serverEdtTxt.getText().toString() + "/api/");
                SingeltonSerivce.getContactAPI().setUrl(serverEdtTxt.getText().toString());
                SingeltonSerivce.getLoginAPI().setUrl(serverEdtTxt.getText().toString());
                SingeltonSerivce.getMessageAPI().setUrl(serverEdtTxt.getText().toString());
                SingeltonSerivce.getRegisterAPI().setUrl(serverEdtTxt.getText().toString());
            }
        });

        darkModeSwitch.setOnClickListener( new View.OnClickListener() {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    @Override
                    public void onClick(View view)
                    {
                        if (AppService.getIsDark()) {
                            AppCompatDelegate
                                    .setDefaultNightMode(
                                            AppCompatDelegate
                                                    .MODE_NIGHT_NO);
                            AppService.setIsDark(false);
                        } else {
                            AppCompatDelegate
                                    .setDefaultNightMode(
                                            AppCompatDelegate
                                                    .MODE_NIGHT_YES);
                            AppService.setIsDark(true);
                        }

                    }
                });

        binding.btnExitSettings.setOnClickListener(v -> {
            finish();
        });
    }
}