package com.example.chatter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatter.databinding.ActivityMainBinding;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String firebaseToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            firebaseToken = newToken;
        });

        AppService.setIsDark(false);
        EditText editTxtUserName = binding.editTextTextUserName;
        EditText editTxtPassword = binding.editTextTextPassword;

        binding.moveToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });


        binding.btnLogin.setOnClickListener(v -> {
            SingeltonSerivce.getLoginAPI();
            SingeltonSerivce.getLoginAPI().logIn(editTxtUserName.getText().toString(), editTxtPassword.getText().toString());
            AppService.setUserID(editTxtUserName.getText().toString());
        });

        SingeltonSerivce.getLoginValue().observe(this, val->{
            if (val.equals("true")){
                SingeltonSerivce.getLoginAPI().firebaseToken(firebaseToken, AppService.getUserID());
                int orientation = getResources().getConfiguration().orientation;
                Intent intent;
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    intent = new Intent(this, HorizontalActivity.class);
                } else {
                    intent = new Intent(this, ChatsActivity.class);
                }
                startActivity(intent);
            }else{
                Toast.makeText(MyApplication.getContext(), val, Toast.LENGTH_SHORT).show();
            }
        });

        binding.settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        });
    }
}
//
//package com.example.chatter;
//
//import android.content.Intent;
//import android.nfc.Tag;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import com.example.chatter.adapters.ChatsListAdapter;
//import com.example.chatter.entities.Contact;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity implements ChatsListAdapter.OnChatListener {
//
//    List<Contact> contacts;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chats);
//
//        RecyclerView lstChats = findViewById(R.id.lstChats);
//        SwipeRefreshLayout refreshChatsLayout = findViewById(R.id.refreshChatsLayout);
//        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
//        FloatingActionButton btnExit = findViewById(R.id.btnExit);
//
//        final ChatsListAdapter adapter = new ChatsListAdapter(this, this);
//        lstChats.setAdapter(adapter);
//        lstChats.setLayoutManager(new LinearLayoutManager(this));
//
//        contacts = new ArrayList<>();
//        contacts.add(new Contact("Or1", "Or1", "server", "Hi, or1", "10:30", R.drawable.generic_profile));
//        contacts.add(new Contact("Or2", "Or2", "server", "Hi, or2", "10:30", R.drawable.generic_profile));
//        contacts.add(new Contact("Or3", "Or3", "server", "Hi, or3", "10:30", R.drawable.generic_profile));
//        contacts.add(new Contact("Or4", "Or4", "server", "Hi, or4", "10:30", R.drawable.generic_profile));
//        contacts.add(new Contact("Or5", "Or5", "server", "Hi, or5", "10:30", R.drawable.generic_profile));
//        contacts.add(new Contact("Or6", "Or6", "server", "Hi, or6", "10:30", R.drawable.generic_profile));
//        contacts.add(new Contact("Or7", "Or7", "server", "Hi, or7", "10:30", R.drawable.generic_profile));
//        contacts.add(new Contact("Or8", "Or8", "server", "Hi, or8", "10:30", R.drawable.generic_profile));
//        contacts.add(new Contact("Or9", "Or9", "server", "Hi, or9", "10:30", R.drawable.generic_profile));
//        contacts.add(new Contact("Or10", "Or10", "server", "Hi, or10", "10:30", R.drawable.generic_profile));
//        adapter.setContacts(contacts);
//
//        lstChats.setClickable(true);
//
//        //Once the submit button clicked
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            //Validity check
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//
//    // When click on contact Activate the function:
//    @Override
//    public void OnChatClick(int position) {
//        Log.d("H","I" + position);
//        //Intent intent = new Intent(this, NewAct.class);
//        //intent.putExtra("MORe", contacts.get(position));
//        //startActivity(intent);
//    }
//}