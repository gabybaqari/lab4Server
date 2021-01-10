package com.example.lab4.API;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessage {
    private String id;
    private String sender;
    private String receiver;
    private Date  date;

    public ChatMessage(String sender, String receiver){
        this.sender = sender;
        this.receiver = receiver;
        date = new Date();
    }
}
