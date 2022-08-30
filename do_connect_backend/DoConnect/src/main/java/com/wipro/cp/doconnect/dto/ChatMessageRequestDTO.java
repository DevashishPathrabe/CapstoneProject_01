/*
* @Author: Devashish Ashok Pathrabe
* Modified Date: 26-08-2022
* Description: Chat Message Request DTO
*/

package com.wipro.cp.doconnect.dto;

public class ChatMessageRequestDTO {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public ChatMessageRequestDTO(String message) {
		super();
		this.message = message;
	}

	public ChatMessageRequestDTO() {
		super();
	}

}
