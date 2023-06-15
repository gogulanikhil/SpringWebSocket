package com.example.springwebsocket;

import com.example.springwebsocket.BaseEntity.ChatMessage;
import com.example.springwebsocket.BaseEntity.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
   @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent){
       StompHeaderAccessor stompHeaderAccessor=StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
//       As its is an object we  need to cast to string
       String username=(String) stompHeaderAccessor.getSessionAttributes().get("username");
       if(username!=null){
           log.info("user disconnectd : { }",username);
           var chatMessage = ChatMessage.builder()
                   .type(MessageType.LEAVE)
                   .build();
       }

    }
}
