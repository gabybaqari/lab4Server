package com.example.lab4.API;

import com.example.lab4.MessageAttribute;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatList {

    private List<MessageAttribute> chatMessages = new ArrayList<>();
}
