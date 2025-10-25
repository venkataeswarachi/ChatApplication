package com.websocket.chatapp.Controllers;

import com.websocket.chatapp.Models.Message;
import com.websocket.chatapp.Models.RoomModel;
import com.websocket.chatapp.Repositories.RoomRepository;
import com.websocket.chatapp.payload.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@CrossOrigin("https://chatn2n.netlify.app/")
public class ChatController {

    @Autowired
    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    //for sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}")// /app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}")//subscribe
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ) {

        RoomModel room = roomRepository.findByRoomId(request.getRoomId());
        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());
        if (room != null) {
            room.getMessages().add(message);
            roomRepository.save(room);
        } else {
            throw new RuntimeException("room not found !!");
        }

        return message;


    }
    @MessageMapping("/addUser/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message addUser(
            @DestinationVariable String roomId,
            MessageRequest request
    ) {
        RoomModel room = roomRepository.findByRoomId(roomId);
        if (room == null) throw new RuntimeException("Room not found");

        if (!room.getMembers().contains(request.getSender())) {
            room.getMembers().add(request.getSender());
            roomRepository.save(room);
        }

        Message joinMsg = new Message();
        joinMsg.setSender(request.getSender());
        joinMsg.setContent(request.getSender() + " joined the room");
        joinMsg.setTimeStamp(LocalDateTime.now());
        return joinMsg;
    }
}
