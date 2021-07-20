package com.example.hackathonex.constant;

import com.example.hackathonex.DTO.RoomDTO;
import com.example.hackathonex.DTO.UserDTO;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public final class ConstantRoomInfo {
    public static final Map<RoomDTO, List<UserDTO>> roomUserMap = new HashMap<>();

    public static void put(RoomDTO r , UserDTO u ){
        if(!roomUserMap.containsKey(r)){
            roomUserMap.put(r ,new ArrayList<>());
            roomUserMap.get(r).add(u);
        }
        else roomUserMap.get(r).add(u);
    }
}
