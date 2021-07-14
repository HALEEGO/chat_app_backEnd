package com.example.hackathonex.controller;


import com.example.hackathonex.DTO.ChatMessageDTO;
import com.example.hackathonex.DTO.RoomDTO;
import com.example.hackathonex.constant.ConstantURL;
import com.example.hackathonex.constant.Returns;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RoomController {

    @Autowired
    private final SimpMessageSendingOperations messagingTemplate;
    Returns returns;

    @MessageMapping("/usermove")
    public void move(ChatMessageDTO message){
        returns = new Returns(ConstantURL.topic);

        returns.append("/usermove/").append(message.getRoomID()).append("/Host"); // 리턴 url
        returns.put("RoomID", message.getRoomID()).put("UserName",message.getUserName())
                .put("MessageType",message.getMessagetype().toString()).put("MoveType",message.getMovetype().toString());

        messagingTemplate.convertAndSend(returns.getReturnURL().toString(), returns.getReturnValue());
    }//RoomID , UserName , MessageType = Move, MoveType = ENTER or EXIST



    @MessageMapping("/userlist/{RoomID}" )
    public void userlist(List<String> users,@DestinationVariable("RoomID") String RoomID){

        returns = new Returns(ConstantURL.topic);

        returns.append("/userlist/").append(RoomID);
        returns.put("userlist", users.toString());
        messagingTemplate.convertAndSend(returns.getReturnURL().toString() , returns.getReturnValue());
    } //방에있는 유저들에게 그방 인원 쏴주기 호스트가
    //path = RoomID , Body = UserList









    //테스트용도
    @MessageMapping("/hello")
    public void hello(RoomDTO r, ChatMessageDTO cm){
        System.out.println("ok");

        messagingTemplate.convertAndSend("/topic/greetings", r.getRoomID());
        messagingTemplate.convertAndSend("/topic/greeting", cm.getRoomID());
        System.out.println("ok");
    }










}
