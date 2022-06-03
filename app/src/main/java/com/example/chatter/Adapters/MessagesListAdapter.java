package com.example.chatter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatter.Entities.Message;
import com.example.chatter.R;

import java.util.List;

public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.MessageViewHolder> {

    class MessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageContent;
        private final TextView messageTime;

        private MessageViewHolder(View itemView) {
            super(itemView);
            messageContent = itemView.findViewById(R.id.messageContent);
            messageTime = itemView.findViewById(R.id.messageTime);
        }
    }

    private final LayoutInflater mInflater;
    private List<Message> messages;

    public MessagesListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).isSent()) {
            return 1;
        }
        return 2;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == 1) {
            itemView = mInflater.inflate(R.layout.message_item, parent, false);
        } else {
            itemView = mInflater.inflate(R.layout.message_item2, parent, false);
        }
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        if (messages != null) {
            final Message current = messages.get(position);
            holder.messageContent.setText(current.getContent());
            holder.messageTime.setText(current.getCreated().toString());
        }
    }

    public void setMessages(List<Message> s) {
        messages = s;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (messages != null)
            return messages.size();
        else return 0;
    }

    public List<Message> getMessages() { return messages; }
}
