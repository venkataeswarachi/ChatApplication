package com.websocket.chatapp.Repositories;

import com.websocket.chatapp.Models.RoomModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<RoomModel,String> {
    RoomModel findByRoomId(String roomId);
}
