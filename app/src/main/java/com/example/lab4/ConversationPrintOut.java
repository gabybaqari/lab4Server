package com.example.lab4;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import static com.example.lab4.ConversationsActivity.conversationActivityApplicationContext;
import static com.example.lab4.ConversationsActivity.conversation_recyclerView;

public class ConversationPrintOut {
    public ConversationPrintOut() {
    }
  //MessageAttribute messageAttribute;
    ConversationAdapter conversationAdapter;

    /*public void sendersNameText(String sendersName, MessageAttribute messageAttribute) {
        messageAttribute.setSender(sendersName);
    }
    public void timeText(String time, MessageAttribute messageAttribute) {
        messageAttribute.setTime(time);
    }
    public void sendersTextText(String sendersText, MessageAttribute messageAttribute) {
        messageAttribute.setSendersText(sendersText);
    }*/
    public void addToConversationList(List<MessageAttribute> conversationsList, MessageAttribute messageAttribute) {
        conversationsList.add(messageAttribute);
    }
    public void setAdapter(List<MessageAttribute> conversationsList) {
        conversation_recyclerView.setLayoutManager(new LinearLayoutManager(conversationActivityApplicationContext));
        conversationAdapter = new ConversationAdapter(conversationsList);
        conversation_recyclerView.setAdapter(conversationAdapter);
    }
}
