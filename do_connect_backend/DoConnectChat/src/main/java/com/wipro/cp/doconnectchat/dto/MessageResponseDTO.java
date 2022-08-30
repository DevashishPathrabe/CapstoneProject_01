/*
* @Author: Devashish Ashok Pathrabe
* Modified Date: 26-08-2022
* Description: Message Response DTO
*/

package com.wipro.cp.doconnectchat.dto;

import java.util.Date;

public class MessageResponseDTO {

	private String message;

	private Date postedAt;

	private String postedBy;

	public String getMessage() {
		return message;
	}

	public Date getPostedAt() {
		return postedAt;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public MessageResponseDTO(String message, Date postedAt, String postedBy) {
		super();
		this.message = message;
		this.postedAt = postedAt;
		this.postedBy = postedBy;
	}

	public MessageResponseDTO() {
		super();
	}
	
}
