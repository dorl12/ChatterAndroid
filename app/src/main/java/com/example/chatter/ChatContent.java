package com.example.chatter;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatter.API.MessageAPI;
import com.example.chatter.Adapters.MessagesListAdapter;
import com.example.chatter.Entities.Message;
import com.example.chatter.databinding.ActivityChatContentBinding;

import java.util.List;

public class ChatContent extends AppCompatActivity {

    private ActivityChatContentBinding binding;
    private int count = 0;
    MutableLiveData<List<Message>> messages;
    String contactId = "dor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatContentBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_chat_content);
        setContentView(binding.getRoot());

        TextView txtInputBar = binding.inputBar;
        ImageButton btnSend = binding.sendButton;
        RecyclerView lstMessages = binding.messagesList;
        messages = new MutableLiveData<>();

        final MessagesListAdapter adapter = new MessagesListAdapter(this);
        lstMessages.setAdapter(adapter);
        lstMessages.setLayoutManager(new LinearLayoutManager(this));

        MessageAPI messageAPI = new MessageAPI(messages, contactId);
        messageAPI.get();
        messages.observe(this, (messageList) -> {
            adapter.setMessages(messageList);
        });

//        List<Message> messages = new ArrayList<>();
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
//        String time = timeFormat.format(calendar.getTime());
//
//        messages.add(new Message(count++, "This is one message", time, true));
//        messages.add(new Message(count++, "This is another message", time, false));
//        messages.add(new Message(count++, "This is the third message", time, true));
//        adapter.setMessages(messages);

        btnSend.setOnClickListener(v -> {
//            Calendar cal = Calendar.getInstance();
//            String timeOfMessage = timeFormat.format(cal.getTime());
//            messages.add(new Message(count++, txtInputBar.getText().toString(), timeOfMessage, true));
            txtInputBar.setText("");
        });
    }
}