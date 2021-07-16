package com.example.hackathonex.model;

import com.example.hackathonex.constant.Enum.MessageType;
import com.example.hackathonex.constant.Enum.MoveType;
import lombok.*;


public class ChatMessage {

    private MessageType messagetype; // 메시지 타입
    private MoveType movetype; // 이동 타입
    private  String roomID; // 방번호
    private User userName ; // 메시지 보낸사람
    private String context; // 메시지


}
