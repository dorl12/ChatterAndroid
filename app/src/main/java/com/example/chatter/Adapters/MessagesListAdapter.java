package com.example.chatter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatter.Entities.MessageForRoom;
import com.example.chatter.R;

import java.util.List;

public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.MessageViewHolder> {

    class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        private final TextView messageContent;
        private final TextView messageTime;

        OnMessageListener onMessageListener;

        private MessageViewHolder(View itemView, OnMessageListener onMessageListener) {
            super(itemView);
            messageContent = itemView.findViewById(R.id.messageContent);
            messageTime = itemView.findViewById(R.id.messageTime);
            this.onMessageListener = onMessageListener;
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            onMessageListener.OnLongMessageClick(getAdapterPosition());
            return true;
        }
    }

    public interface OnMessageListener {
        void OnLongMessageClick(int position);
    }

    private final LayoutInflater mInflater;
    private List<MessageForRoom> message;
    private OnMessageListener onMessageListener;

    public MessagesListAdapter(Context context, OnMessageListener onMessageListener) {
        mInflater = LayoutInflater.from(context);
        this.onMessageListener = onMessageListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (message.get(position).isSent()) {
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
        return new MessageViewHolder(itemView, onMessageListener);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        if (message != null) {
            final MessageForRoom current = message.get(position);
            holder.messageContent.setText(current.getContent());
            holder.messageTime.setText(current.getCreated().toString());
        }
    }

    public void setMessages(List<MessageForRoom> s) {
        message = s;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (message != null)
            return message.size();
        else return 0;
    }

    public List<MessageForRoom> getMessages() { return message; }
}
