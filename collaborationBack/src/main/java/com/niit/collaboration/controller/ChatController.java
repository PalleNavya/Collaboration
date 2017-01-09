package com.niit.collaboration.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.collaboration.model.Message;
import com.niit.collaboration.model.OutputMessage;




@Controller
public class ChatController {
	
	private static final Logger logger	= LoggerFactory.getLogger(UserController.class);

	@MessageMapping("/chat")
	@SendTo("/queue/message")
	public OutputMessage sendMessage(Message message){
		logger.debug("Calling the method sendMessage");
		logger.debug("Message: ",message.getMessage());
		logger.debug("Message ID:",message.getId() );
		
		return new OutputMessage(message,new Date());
		
	}

}