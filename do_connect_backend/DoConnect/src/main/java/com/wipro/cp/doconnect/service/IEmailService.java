/*
* @Author: Devashish Ashok Pathrabe
* Modified Date: 26-08-2022
* Description: Email Service Interface
*/

package com.wipro.cp.doconnect.service;

import com.wipro.cp.doconnect.dto.EmailDTO;

public interface IEmailService {
	
	boolean sendNotificationEmail(EmailDTO emailDTO);
	
}
