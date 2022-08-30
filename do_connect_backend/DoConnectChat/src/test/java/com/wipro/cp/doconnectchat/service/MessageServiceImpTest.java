/*
	* @Author: Komal Anil Lawand
	* Modified Date: 26-08-2022
	* Description: Create message
	* Params: MessageRequestDTO object
	* Return Type: Boolean
	*/

package com.wipro.cp.doconnectchat.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.aspectj.bridge.IMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.cp.doconnectchat.dto.MessageRequestDTO;
import com.wipro.cp.doconnectchat.dto.MessageResponseDTO;


@SpringBootTest
class MessageServiceImpTest {

	@Autowired
	IMessageService service;
	/*
	* @Author: Komal Anil Lawand
	* Modified Date: 26-08-2022
	* Description: Create message
	* Params: MessageRequestDTO object
	* Return Type: Boolean
	*/
	@Test
	void testGetAllMessages() {
		
		List<MessageResponseDTO> response = service.getAllMessages();
		assertNotNull(response);
	}

	/*
	* @Author: Komal Anil Lawand
	* Modified Date: 26-08-2022
	* Description: Create message
	* Params: MessageRequestDTO object
	* Return Type: Boolean
	*/
	@Test
	void testCreateMessage() {
		MessageRequestDTO msgdto = new MessageRequestDTO("Hey ","User");
		boolean response = service.createMessage(msgdto);
		
		assertNotNull(response);
	}

}
