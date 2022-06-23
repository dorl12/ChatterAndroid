package com.example.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.chatter.databinding.ActivityRegisterBinding;
import com.google.firebase.iid.FirebaseInstanceId;

public class RegisterActivity extends AppCompatActivity {
    MutableLiveData<String> isRegister = new MutableLiveData<>();

    private ActivityRegisterBinding binding;
    private String firebaseToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(RegisterActivity.this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            firebaseToken = newToken;
        });

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
                    Toast.makeText(MyApplication.getContext(), val, Toast.LENGTH_SHORT).show();
            }
        });

        SingeltonSerivce.getHasToken().observe(this,(val)-> {
            if (val) {
                SingeltonSerivce.getLoginAPI().firebaseToken(firebaseToken, AppService.getUserID());
                Intent intent = new Intent(this, ChatsActivity.class);
                startActivity(intent);
            }
        });


    }
}