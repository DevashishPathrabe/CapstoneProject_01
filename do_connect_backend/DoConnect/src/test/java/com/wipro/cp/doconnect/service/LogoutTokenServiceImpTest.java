/*
	* @Author: Tapas Kumar Saha
	* Modified Date: 26-08-2022
	* Description: Update approval status of a particular answer for a particular question ID
	* Params: QuestionID long, AnswerID long, AnswerUpdateDTO object, ApprovedBy string
	* Return Type: AnswerResponseDTO wrapped with StatusDTO
	*/

package com.wipro.cp.doconnect.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.cp.doconnect.entity.LogoutToken;

@SpringBootTest
class LogoutTokenServiceImpTest {

	@Autowired
	ILogoutTokenService service;
	/*
	* @Author: Tapas Kumar Saha
	* Modified Date: 26-08-2022
	* Description: Update approval status of a particular answer for a particular question ID
	* Params: QuestionID long, AnswerID long, AnswerUpdateDTO object, ApprovedBy string
	* Return Type: AnswerResponseDTO wrapped with StatusDTO
	*/
	@Test
	void testCheckIfTokenExists() {
		boolean response = service.checkIfTokenExists("$2a$10$uZR8uoZTB76xcr6/F2ibRubV288fXkrZnyW78zNhdsW3zBxefALwe");
		assertNotNull(response);
	}

	/*
	* @Author: Tapas Kumar Saha
	* Modified Date: 26-08-2022
	* Description: Update approval status of a particular answer for a particular question ID
	* Params: QuestionID long, AnswerID long, AnswerUpdateDTO object, ApprovedBy string
	* Return Type: AnswerResponseDTO wrapped with StatusDTO
	*/
	@Test
	void testGetAllTokens() {
		List<LogoutToken> response = service.getAllTokens();
		assertNotNull(response);
	}

	

}
