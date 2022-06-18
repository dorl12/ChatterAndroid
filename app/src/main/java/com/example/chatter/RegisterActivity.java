package com.example.chatter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.chatter.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    MutableLiveData<String> isRegister = new MutableLiveData<>();

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
            SingeltonSerivce.setRegisterAPI(isRegister);
            SingeltonSerivce.getLoginAPI();
            SingeltonSerivce.getRegisterAPI().post(binding.editTextTextUserName.getText().toString(),
                    binding.editTextTextPassword.getText().toString(),
                    binding.editTextTextNickname.getText().toString());
            AppService.setUserID(binding.editTextTextUserName.getText().toString());
        });

        isRegister.observe(this,(val)->{
            if(val.equals("true")){
                SingeltonSerivce.getLoginAPI().logIn(binding.editTextTextUserName.getText().toString(),
                        binding.editTextTextPassword.getText().toString());
            }
            else{
                int a =1 ;
                // todo: make toast for error message
            }
        });

        SingeltonSerivce.getHasToken().observe(this,(val)-> {
            if (val) {
                Intent intent = new Intent(this, ChatsActivity.class);
                startActivity(intent);
            }
        });


    }
}