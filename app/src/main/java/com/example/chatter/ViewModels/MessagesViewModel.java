//package com.example.chatter.ViewModels;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.ViewModel;
//
//import com.example.chatter.Entities.Message;
//
//import java.util.List;
//
//public class MessagesViewModel extends ViewModel {
//
//    private MessagesRepository mRepository;
//    private LiveData<List<Message>> messages;
//
//    public MessagesViewModel(){
//        mRepository = new MessagesRepository();
//        messages = mRepository.getAll();
//    }
//
//    public LiveData<List<Message>> get() { return messages; }
//    public void add (Message message) { mRepository.add(message); }
//    public void delete (Message message) { mRepository.delete(message); }
//    public void reload() { mRepository.reload(); }
//}
