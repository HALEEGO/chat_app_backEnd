package com.example.hackathonex.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.*;


@Data
@NoArgsConstructor
public class ChatMessage {
    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        MOVE , TALK
    }
    public enum MoveType {
        ENTER, EXIST
    }

    private MessageType messagetype; // 메시지 타입
    private MoveType movetype; // 이동 타입
    private  String roomID; // 방번호
    private String userName ; // 메시지 보낸사람
    private String context; // 메시지


}
