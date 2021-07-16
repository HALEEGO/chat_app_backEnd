package com.example.hackathonex.controller;

import com.example.hackathonex.DTO.ChatMessageDTO;
import com.example.hackathonex.constant.ConstantURL;
import com.example.hackathonex.constant.Enum.MessageType;
import com.example.hackathonex.constant.Returns;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;



@RequiredArgsConstructor
@Controller
public class ChatController {
    Returns returns = new Returns();
    @Autowired
    private final SimpMessageSendingOperations messagingTemplate;


    @MessageMapping("/sendchat") // 이름, context  -> toJSON
    public void send_chat(ChatMessageDTO message) {

        returns
                .append(ConstantURL.topic)
                .append("/sendchat/")
                .append(message.getRoomID());
        returns
                .put("userName", message.getUser().getUserName())
                .put("context", message.getContext());

        if (message.getMessagetype() == MessageType.TALK) {
            messagingTemplate.convertAndSend(returns.getReturnURL().toString(), returns.getReturnValue());
        }
    }
}