package com.wipro.doconnectchat.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// @NotBlank(message = "To which user u want to send")
	private String message;
	
	// to do timestamp 
	
	private String username; 
	
	Long datetime = System.currentTimeMillis();
	private Timestamp timestamp = new Timestamp(datetime);
	
	public Timestamp getTimestamp() {
		
		return  timestamp;
	}
	
	public Long getId() {
		return id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFromUser() {
		return username;
	}
	public void setFromUser(String fromUser) {
		this.username = fromUser;
	}
	public Message(String message, String fromUser) {
		super();
		this.message = message;
		this.username = fromUser;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
