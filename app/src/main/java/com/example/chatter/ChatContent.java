package com.example.chatter;


import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatter.Adapters.MessagesListAdapter;
import com.example.chatter.Entities.MessageForRoom;
import com.example.chatter.Room.MessageDB;
import com.example.chatter.databinding.ActivityChatContentBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatContent extends AppCompatActivity implements MessagesListAdapter.OnMessageListener{

    private ActivityChatContentBinding binding;
    private int count = 0;
//    List<Message> messages = new ArrayList<Message>();
    // List<Message> allMessages = new ArrayList<Message>();
    private MessageDB db;
//    private MessageDao messageDao;
    private MessagesListAdapter adapter;
    private String contactName;
    MutableLiveData<List<MessageForRoom>> messages;
    // String contactId = "dor";
    List<MessageForRoom> newMsgLst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatContentBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_chat_content);
        setContentView(binding.getRoot());

        TextView txtInputBar = binding.inputBar;
        ImageButton btnSend = binding.sendButton;
        RecyclerView lstMessages = binding.messagesList;
        TextView ContactNickname = binding.contactNickname;
        messages = new MutableLiveData<>();

        Bundle bundle = getIntent().getExtras();
        contactName = bundle.getString("Nickname");
        String contactServer = bundle.getString("contactServer");
        ContactNickname.setText(contactName);

//        db = Room.databaseBuilder(getApplicationContext(), MessageDB.class, "MessageDB2")
//               .allowMainThreadQueries()
//               .build();
//
//        messageDao = db.messageDao();

        adapter = new MessagesListAdapter(this, this);
        lstMessages.setAdapter(adapter);
        lstMessages.setLayoutManager(new LinearLayoutManager(this));


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
//        String time = timeFormat.format(calendar.getTime());
//        allMessages = messageDao.index();
//        for (Message m: allMessages) {
//            if (m.getTo() == null){continue;}
//            if(m.getTo().equals(contactName)){
//                messages.add(m);
//            }
//        }
//
//        adapter.setMessages(messages);

        SingeltonSerivce.setMessageAPI(messages, bundle.getString("ID"));
        SingeltonSerivce.getMessageAPI().get();
//        MessageAPI messageAPI = new MessageAPI(messages, bundle.getString("ID"));
//        messageAPI.get();
        messages.observe(this, (messageList) -> {
            adapter.setMessages(messageList);
            newMsgLst = messageList;
        });

        btnSend.setOnClickListener(v -> {
            if(!txtInputBar.getText().toString().equals("")) {
                Calendar cal = Calendar.getInstance();
                String timeOfMessage = timeFormat.format(cal.getTime());
                MessageForRoom m = new MessageForRoom(count++, txtInputBar.getText().toString(), timeOfMessage, true, contactName);
//                messageDao.insert(m);
//                allMessages = messageDao.index();
//                messages.add(m);
//                adapter.setMessages(messages);
//                //messages.add(new Message(count++, txtInputBar.getText().toString(), timeOfMessage, true));
                SingeltonSerivce.getMessageAPI().insert(m);
                SingeltonSerivce.getUtilsAPI().transfer(m,contactServer);
                txtInputBar.setText("");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        //messages = messageDao.index();

        adapter.setMessages(newMsgLst);
    }

    @Override
    public void OnLongMessageClick(int position) {
//        Message m = messages.remove(position);
//        messageDao.delete(m);
//        adapter.setMessages(messages);
    }
}