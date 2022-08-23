package com.wipro.cp.doconnect.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.wipro.cp.doconnect.util.ListToStringConverter;

@Entity
@Table(name="questions")
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	@NotEmpty
	private String question;
	
	@NotNull
	@NotEmpty
	private String topic;
	
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
	
	@OneToMany(orphanRemoval = true)
	@JoinColumn(name="question_id")
	private List<Answer> answers;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
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

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public long getId() {
		return id;
	}

	public Question(@NotNull @NotEmpty String question, @NotNull @NotEmpty String topic, List<String> images,
			@NotNull @NotEmpty String postedBy, @NotNull @NotEmpty String approvedBy, boolean isApproved,
			List<Answer> answers) {
		super();
		this.question = question;
		this.topic = topic;
		this.images = images;
		this.postedBy = postedBy;
		this.approvedBy = approvedBy;
		this.isApproved = isApproved;
		this.answers = answers;
	}

	public Question() {
		super();
	}
	
}
