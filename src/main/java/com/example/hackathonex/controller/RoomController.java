package com.example.hackathonex.controller;


import com.example.hackathonex.DTO.ChatMessageDTO;
import com.example.hackathonex.DTO.RoomDTO;
import com.example.hackathonex.DTO.UserDTO;
import com.example.hackathonex.constant.ConstantURL;
import com.example.hackathonex.constant.Enum.MessageType;
import com.example.hackathonex.constant.Enum.MoveType;
import com.example.hackathonex.constant.Enum.Role;
import com.example.hackathonex.constant.Returns;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RoomController {

    @Autowired
    private final SimpMessageSendingOperations messagingTemplate;
    Returns returns = new Returns(ConstantURL.topic);

    @MessageMapping("/usermove")
    public void move(ChatMessageDTO message){
        System.out.println("move");

        returns
                .append("/usermove/")
                .append(message.getRoomID()); // 리턴 url

        if(message.getMessagetype() == MessageType.MOVE ){
            returns
                    .put("userName",message.getUser().getUserName())
                    .put("moveType",message.getMovetype().toString());

            if(message.getMovetype() == MoveType.ENTER) message.setContext("입장");
            else message.setContext("퇴장");

            returns
                    .put("context", message.getContext());
            messagingTemplate.convertAndSend(returns.getReturnURL().toString(), returns.getReturnValue());
        }
        returns
                .append("/HOST");
        returns
                .remove("moveType")
                .put("roomID",message.getRoomID());
        messagingTemplate.convertAndSend(returns.getReturnURL().toString(), returns.getReturnValue());

    }//RoomID , UserName , MessageType = Move, MoveType = ENTER or EXIST, role




    @MessageMapping("/userlist/{RoomID}" )
    public void userlist(List<String> users,@DestinationVariable("RoomID") String RoomID){
        System.out.println("userlist");

        returns.append("/userlist/").append(RoomID);
        returns.put("userlist", users.toString());
        messagingTemplate.convertAndSend(returns.getReturnURL().toString() , returns.getReturnValue());
    } //방에있는 유저들에게 그방 인원 쏴주기 호스트가
    //path = RoomID , Body = UserList









    //테스트용도
    @MessageMapping("/hello")
    public void hello(ChatMessageDTO message){
        System.out.println("hello");
        System.out.println(message.getRoomID());
        returns.put("roomID", message.getRoomID());
        messagingTemplate.convertAndSend("/topic/greetings" , returns.getReturnValue() );
    }
}
