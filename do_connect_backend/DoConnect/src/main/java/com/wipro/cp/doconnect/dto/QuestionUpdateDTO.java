/*
* @Author: Devashish Ashok Pathrabe
* Modified Date: 26-08-2022
* Description: Question Update DTO
*/

package com.wipro.cp.doconnect.dto;

public class QuestionUpdateDTO {

	private boolean isApproved;

	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public QuestionUpdateDTO(boolean isApproved) {
		super();
		this.isApproved = isApproved;
	}

	public QuestionUpdateDTO() {
		super();
	}
	
}
