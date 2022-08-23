package com.wipro.cp.doconnect.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.wipro.cp.doconnect.util.ListToStringConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="answers")
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	@NotEmpty
	private String answer;
	
	@Column
	@Convert(converter = ListToStringConverter.class)
	private List<String> images;
	
	@NotNull
	@NotEmpty
	private String postedBy;
	
	@NotNull
	@NotEmpty
	private String approvedBy;
	
	private boolean isApproved;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public long getId() {
		return id;
	}

	public Answer(@NotNull @NotEmpty String answer, List<String> images, @NotNull @NotEmpty String postedBy,
			@NotNull @NotEmpty String approvedBy, boolean isApproved) {
		super();
		this.answer = answer;
		this.images = images;
		this.postedBy = postedBy;
		this.approvedBy = approvedBy;
		this.isApproved = isApproved;
	}

	public Answer() {
		super();
	}

}
