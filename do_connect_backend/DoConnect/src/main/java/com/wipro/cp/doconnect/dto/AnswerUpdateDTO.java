/*
* @Author: Devashish Ashok Pathrabe
* Modified Date: 26-08-2022
* Description: Answer Update DTO
*/

package com.wipro.cp.doconnect.dto;

public class AnswerUpdateDTO {
	
	private boolean isApproved;

	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public AnswerUpdateDTO(boolean isApproved) {
		super();
		this.isApproved = isApproved;
	}

	public AnswerUpdateDTO() {
		super();
	}

}
