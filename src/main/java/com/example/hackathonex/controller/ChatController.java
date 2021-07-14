package com.example.hackathonex.controller;

import com.example.hackathonex.DTO.ChatMessageDTO;
import com.example.hackathonex.constant.ConstantURL;
import com.example.hackathonex.constant.Returns;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;



@RequiredArgsConstructor
@Controller
public class ChatController {

    Returns returns;
    private final SimpMessageSendingOperations messagingTemplate;



    @MessageMapping("/send_chat") // 이름, context  -> toJSON
    public void send_chat(ChatMessageDTO message) {
        returns = new Returns(ConstantURL.topic);

        returns.append("/send_chat/").append(message.getRoomID());
        returns.put("userName", message.getUserName())
                .put("context", message.getContext());

        messagingTemplate.convertAndSend(returns.getReturnURL().toString(), returns.getReturnValue());
    }


}
