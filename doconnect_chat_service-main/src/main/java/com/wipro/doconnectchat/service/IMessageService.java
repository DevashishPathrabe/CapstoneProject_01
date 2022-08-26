package com.wipro.doconnectchat.service;

import java.util.List;

import javax.validation.Valid;

import com.wipro.doconnectchat.dto.MessageDTO;
import com.wipro.doconnectchat.entity.Message;

public interface IMessageService {

	public Message sendMessage(@Valid MessageDTO messageDTO);

	public List<Message> getMessage();

}
