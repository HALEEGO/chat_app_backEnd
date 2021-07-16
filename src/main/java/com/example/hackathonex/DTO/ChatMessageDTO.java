package com.example.hackathonex.DTO;

import com.example.hackathonex.constant.Enum.MessageType;
import com.example.hackathonex.constant.Enum.MoveType;
import com.example.hackathonex.model.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChatMessageDTO {
    private MessageType messagetype; // 메시지 타입
    private MoveType movetype; // 이동 타입
    private  String roomID; // 방번호
    private UserDTO user ; // 메시지 보낸사람
    private String context; // 메시지

}
