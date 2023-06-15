package com.example.springwebsocket.controller;

import com.example.springwebsocket.BaseEntity.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){

        return chatMessage;
    }
    @MessageMapping("/chat.adduser")
    @SendTo("/topic/public")
//    when a new user joins it will create connection bw the user and web socket
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor simpMessageHeaderAccessor) {
//add username in websocket session
        simpMessageHeaderAccessor.getSessionAttributes().put("username",chatMessage.getSender());
        return chatMessage;
    }
}
