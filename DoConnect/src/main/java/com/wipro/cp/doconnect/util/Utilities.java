package com.wipro.cp.doconnect.util;

import java.util.List;
import java.util.stream.Collectors;

import com.wipro.cp.doconnect.dto.AnswerResponseDTO;
import com.wipro.cp.doconnect.dto.QuestionResponseDTO;
import com.wipro.cp.doconnect.entity.Answer;
import com.wipro.cp.doconnect.entity.Question;
import com.wipro.cp.doconnect.entity.User;

public class Utilities {
	
	public static String[] getUserEmails(List<User> userList) {
		String[] array = new String[userList.size()];
		for (int i = 0; i < userList.size(); i++) {
			array[i] = userList.get(i).getEmail();
		}
		return array;
	}
	
	public static QuestionResponseDTO convertQuestionToQuestionResponseDTO(Question question) {
		return new QuestionResponseDTO(question.getId(), question.getQuestion(), question.getTopic(), question.getImages(), question.getPostedBy(), question.getPostedAt(), question.getApprovedBy(), question.getIsApproved());
	}
	
	public static List<QuestionResponseDTO> convertQuestionListToQuestionResponseDTOList(List<Question> questionList) {
		return questionList.stream().map(question -> convertQuestionToQuestionResponseDTO(question)).collect(Collectors.toList());
	}
	
	public static AnswerResponseDTO convertAnswerToAnswerResponseDTO(Answer answer) {
		return new AnswerResponseDTO(answer.getId(), answer.getAnswer(), answer.getImages(), answer.getPostedBy(), answer.getPostedAt(), answer.getApprovedBy(), answer.getIsApproved(), Utilities.convertQuestionToQuestionResponseDTO(answer.getQuestion()));
	}
	
	public static List<AnswerResponseDTO> convertAnswerListToAnswerResponseDTOList(List<Answer> answerList) {
		return answerList.stream().map(answer -> convertAnswerToAnswerResponseDTO(answer)).collect(Collectors.toList());
	}

}
