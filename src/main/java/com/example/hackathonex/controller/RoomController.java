package com.example.hackathonex.controller;


import com.example.hackathonex.DTO.Returns;
import com.example.hackathonex.constant.Url;
import com.example.hackathonex.model.ChatMessage;
import com.example.hackathonex.model.Room;
import com.example.hackathonex.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RoomController {

    private final SimpMessageSendingOperations messagingTemplate;
    Url url = new Url();
    Returns returns;
    @MessageMapping("/usermove")
    public void move(ChatMessage message){
        returns = new Returns(url.topic);

        returns.append("/usermove/").append(message.getRoomID()).append("/Host"); // 리턴 url
        returns.put("RoomID", message.getRoomID()).put("UserName",message.getUserName())
                .put("MessageType",message.getMessagetype().toString()).put("MoveType",message.getMovetype().toString());
        messagingTemplate.convertAndSend(returns.getReturnURL().toString(), returns.getReturnValue());
    }//RoomID , UserName , MessageType = Move, MoveType = ENTER or EXIST



    @MessageMapping("/userlist/{RoomID}" )
    public void userlist(List<String> users,@DestinationVariable("RoomID") String RoomID){

        returns = new Returns(url.topic);

        returns.append("/userlist/").append(RoomID);
        returns.put("userlist", users.toString());
        ObjectMapper om = new ObjectMapper();
        messagingTemplate.convertAndSend(returns.getReturnURL().toString() , returns.getReturnValue());
    } //방에있는 유저들에게 그방 인원 쏴주기 호스트가
    //path = RoomID , Body = UserList









    //테스트용도
    @MessageMapping("/hello")
    public void hello(Room r, ChatMessage cm){
        System.out.println("ok");

        messagingTemplate.convertAndSend("/topic/greetings", r.getRoomID());
        messagingTemplate.convertAndSend("/topic/greeting", cm.getRoomID());
        System.out.println("ok");
    }










}
