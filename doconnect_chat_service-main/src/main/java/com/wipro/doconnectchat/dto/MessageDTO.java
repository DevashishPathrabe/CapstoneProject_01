package com.wipro.doconnectchat.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;



public class MessageDTO {

	@NotBlank(message = "provide the user Details")
	private String fromUser;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotBlank(message = "provide message")
	private String message;
	private Long id;
	Long datetime = System.currentTimeMillis();
    private Timestamp timestamp=new Timestamp(datetime);
	
	public Timestamp getTimestamp() {
		
		return  timestamp;
	}
	


	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageDTO(@NotBlank(message = "provide the user Details") String fromUser,
			@NotBlank(message = "provide message") String message) {
		super();
		this.fromUser = fromUser;
		this.message = message;
	}
	public MessageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
