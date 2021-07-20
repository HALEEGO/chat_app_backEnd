package com.example.hackathonex.controller;


import com.example.hackathonex.DTO.ChatMessageDTO;
import com.example.hackathonex.DTO.UserDTO;
import com.example.hackathonex.constant.ConstantRoomInfo;
import com.example.hackathonex.constant.ConstantURL;
import com.example.hackathonex.constant.Enum.MessageType;
import com.example.hackathonex.constant.Enum.MoveType;
import com.example.hackathonex.constant.Returns;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RoomController {

    @Autowired
    private final SimpMessageSendingOperations messagingTemplate;
    Returns returns = new Returns(ConstantURL.topic);

    @MessageMapping("/usermove")
    @SendToUser("/queue/userin")
    public Map<String,String> move(ChatMessageDTO message ){
        System.out.println("move");

        ConstantRoomInfo.put(message.getRoom(),message.getUser());

        returns
                .append(ConstantURL.topic)
                .append(message.getRoom().getRoomID())
                .put("UserList",ConstantRoomInfo.roomUserMap.get(message.getRoom()).toString());

        messagingTemplate.convertAndSend(returns.getReturnURL().toString(), message);

        return returns.getReturnValue();
    }//RoomID , UserName , MessageType = Move, MoveType = ENTER or EXIST, role











//
//
//    //테스트용도
//    @MessageMapping("/hello")
////    @SendToUser("/queue/greetings")
//    public void hello(ChatMessageDTO message , SimpMessageHeaderAccessor sha){
//        System.out.println(message.toString());
//        ConstantRoomInfo.put(message.getRoom(),message.getUser());
//        System.out.println(sha.getSessionId());
////        System.out.println(pric.getName());
//        returns
//                .put("RoomSize",String.valueOf(ConstantRoomInfo.roomUserMap.get(message.getRoom()).size()));
//        System.out.println("dddd");
//        messagingTemplate.convertAndSendToUser(sha.getSessionId(), "/queue/greetings" , returns.getReturnValue() );
//    }
//    //테스트용도
//    @MessageMapping("/hello2")
//    public void hello2(ChatMessageDTO message){
//        System.out.println("hello");
//        System.out.println(message.getRoom());
//        returns.put("roomID", message.getRoom().getRoomID());
//        messagingTemplate.convertAndSend("/topic/greetings" , returns.getReturnValue() );
//    }
}
