package com.wipro.doconnectchat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.doconnectchat.dto.MessageDTO;
import com.wipro.doconnectchat.entity.Message;
import com.wipro.doconnectchat.service.IMessageService;

@RestController
@RequestMapping("/api/v1/chats")
@CrossOrigin
public class MessageController {

	@Autowired
	private IMessageService messageService;

	@PostMapping("/addmessages")
	public Message sendMessage(@Valid @RequestBody MessageDTO messageDTO) {
		return messageService.sendMessage(messageDTO);
	}

	@GetMapping("/getmessages")
	public List<Message> getMessage() {
		return messageService.getMessage();
	}

	
}

