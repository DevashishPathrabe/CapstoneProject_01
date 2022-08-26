package com.wipro.doconnectchat.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.doconnectchat.dto.MessageDTO;
import com.wipro.doconnectchat.entity.Message;
import com.wipro.doconnectchat.repository.IMessageRepo;

@Service
public class IMessageServiceImpl implements IMessageService {

	@Autowired
	private IMessageRepo messageRepo;

	@Override
	public MessageDTO sendMessage(@Valid MessageDTO messageDTO) {

		Message message = new Message();
		message.setMessage(messageDTO.getMessage());
		message.setFromUser(messageDTO.getFromUser());
		messageDTO.setFromUser(message.getFromUser());
		messageDTO.setMessage(message.getMessage());
		message = messageRepo.save(message);
		return messageDTO;
	}

	@Override
	public List<Message> getMessage() {
		List<MessageDTO> data = new ArrayList<MessageDTO>();

		List<Message> messages = messageRepo.findAll();
//		if(messages.size()==0) {
//			return new List<Mess>
//		}
//		for (Message message : messages) {
//
//			MessageDTO messageDTO = new MessageDTO();
//			messageDTO.setFromUser(message.getFromUser());
//			messageDTO.setMessage(message.getMessage());
//			data.add(messageDTO);
//
//		}

		return messages;
	}

}
