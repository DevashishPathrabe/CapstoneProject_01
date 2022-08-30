/*
	* @Author: Tapas Kumar Saha
	* Modified Date: 26-08-2022
	* Description: Update approval status of a particular answer for a particular question ID
	* Params: QuestionID long, AnswerID long, AnswerUpdateDTO object, ApprovedBy string
	* Return Type: AnswerResponseDTO wrapped with StatusDTO
	*/



package com.wipro.cp.doconnect.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.cp.doconnect.dto.EmailDTO;

@SpringBootTest
class EmailServiceImpTest {

	@Autowired
	IEmailService service;
	/*
	* @Author: Tapas Kumar Saha
	* Modified Date: 26-08-2022
	* Description: Update approval status of a particular answer for a particular question ID
	* Params: QuestionID long, AnswerID long, AnswerUpdateDTO object, ApprovedBy string
	* Return Type: AnswerResponseDTO wrapped with StatusDTO
	*/
	@Test
	void testSendNotificationEmail() {
		String[] recipients = new String[]{"tapassaha6685@gmail.com"};
		EmailDTO emaildto = new EmailDTO(recipients,"Response checking","This is a testing mail.");
		boolean response = service.sendNotificationEmail(null);
	}

}
