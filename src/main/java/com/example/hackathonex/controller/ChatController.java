package com.example.hackathonex.controller;

import com.example.hackathonex.DTO.Returns;
import com.example.hackathonex.constant.Url;
import com.example.hackathonex.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



@RequiredArgsConstructor
@Controller
public class ChatController {

    Url url = new Url();
    Returns returns;
    private final SimpMessageSendingOperations messagingTemplate;



    @MessageMapping("/send_chat") // 이름, context  -> toJSON
    public void send_chat(ChatMessage message) {
        returns = new Returns(url.topic);

        returns.append("/send_chat/").append(message.getRoomID());
        returns.put("userName", message.getUserName())
                .put("context", message.getContext());

        messagingTemplate.convertAndSend(returns.getReturnURL().toString(), returns.getReturnValue());
    }




}
