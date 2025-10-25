package com.websocket.chatapp.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "rooms")
public class RoomModel {
    @Id
    private String id;
    private String roomId;
    private List<Message> messages = new ArrayList<>();
    private List<String> members = new ArrayList<>();
    public RoomModel() {
    }

    public RoomModel(String id, String roomId, List<Message> messages, List<String> members) {
        this.id = id;
        this.roomId = roomId;
        this.messages = messages;
        this.members = members;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public RoomModel(String id, String roomId, List<Message> messages) {
        this.id = id;
        this.roomId = roomId;
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
