package com.wipro.doconnectchat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

	private String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public ResponseDTO(String response) {
		super();
		this.response = response;
	}

	public ResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
