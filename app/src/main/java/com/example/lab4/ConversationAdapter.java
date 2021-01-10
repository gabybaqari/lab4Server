package com.example.lab4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {

    private List<MessageAttribute> messagesList;
    private View view;
    private ConversationAdapter.OnItemClickListener mListener;

    public ConversationAdapter(List<MessageAttribute> messagesList) {
        this.messagesList = messagesList;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation, parent, false);

        ConversationViewHolder cvh = new ConversationViewHolder(view, mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {

        MessageAttribute message =messagesList.get(position);
        holder.sendersName.setText(message.getSender());
        holder.sendersText.setText(message.getSendersText());
        holder.time.setText( message.getTime()+"");
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public interface OnItemClickListener {
    }

    public static class ConversationViewHolder extends RecyclerView.ViewHolder {
        static TextView sendersName, sendersText, time;
        public ConversationViewHolder(@NonNull View itemView,final ConversationAdapter.OnItemClickListener listener) {
            super(itemView);
            sendersName=itemView.findViewById(R.id.sender);
            sendersText=itemView.findViewById(R.id.sendersText);
            time=itemView.findViewById(R.id.time);
        }
    }
}
