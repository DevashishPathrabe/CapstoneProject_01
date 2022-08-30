/*
* @Author: Devashish Ashok Pathrabe
* Modified Date: 26-08-2022
* Description: User Login Request DTO
*/

package com.wipro.cp.doconnect.dto;

import java.io.Serializable;

public class UserLoginRequestDTO implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String username;
	private String password;

	public UserLoginRequestDTO() {}

	public UserLoginRequestDTO(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
