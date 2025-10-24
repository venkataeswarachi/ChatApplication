package com.websocket.chatapp.Controllers;

import com.websocket.chatapp.payload.MessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping
public class ChatController {


    @MessageMapping("/sendMessage")
    @SendTo("/topic/room")
    public MessageDTO sendMessage(@Payload MessageDTO message){
        return message;
    }
    @MessageMapping("/addUser")
    @SendTo("/topic/room")
    public MessageDTO addUser(@Payload MessageDTO message) {
        message.setType("JOIN");
        return message;
    }
}
