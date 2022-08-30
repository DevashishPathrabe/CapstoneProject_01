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
	
	@Test
	void testSendNotificationEmail() {
		String[] recipients = new String[]{"tapassaha6685@gmail.com"};
		EmailDTO emaildto = new EmailDTO(recipients,"Response checking","This is a testing mail.");
		boolean response = service.sendNotificationEmail(null);
	}

}
