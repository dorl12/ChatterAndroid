package com.example.chatter.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatter.R;
import com.example.chatter.Entities.Contact;

import java.util.List;

public class ChatsListAdapter extends RecyclerView.Adapter<ChatsListAdapter.ChatViewHolder> {

    class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private final TextView contact_name;
        private final TextView last_massage;
        private final TextView last_message_time;
        private final ImageView profile_image;

        OnChatListener onChatListener;

        private ChatViewHolder(View itemView, OnChatListener onChatListener) {
            super(itemView);
            contact_name = itemView.findViewById(R.id.contact_name);
            last_massage = itemView.findViewById(R.id.last_massage);
            last_message_time = itemView.findViewById(R.id.last_message_time);
            profile_image = itemView.findViewById(R.id.profile_image);

            this.onChatListener = onChatListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            onChatListener.OnLongChatClick(getAdapterPosition());
            return true;
        }
        @Override
        public void onClick(View view) {
            onChatListener.OnChatClick(getAdapterPosition());
        }


    }

    public interface OnChatListener {
        void OnChatClick(int position);
        void OnLongChatClick(int position);
    }

    private final LayoutInflater cInflater;
    private List<Contact> contacts;
    private OnChatListener onChatListener;

    public ChatsListAdapter(Context context, OnChatListener onChatListener) {
        this.cInflater = LayoutInflater.from(context);
        this.onChatListener = onChatListener;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = cInflater.inflate(R.layout.chat_item, parent, false);
        return new ChatViewHolder(itemView, onChatListener);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        if(contacts != null) {
            final Contact curr = contacts.get(position);
            holder.contact_name.setText(curr.getName());
            holder.last_massage.setText(curr.getLast());
            holder.last_message_time.setText(curr.getCreated());
            holder.profile_image.setImageResource(curr.getPic());
        }
    }

    public  void setContacts(List<Contact> c) {
        contacts = c;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (contacts != null)
            return contacts.size();
        else
            return 0;
    }

    public List<Contact> getContacts() { return contacts; }
}
