package com.wipro.cp.doconnect.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.cp.doconnect.dto.AnswerResponseDTO;
import com.wipro.cp.doconnect.dto.QuestionResponseDTO;
import com.wipro.cp.doconnect.dto.StatusDTO;
import com.wipro.cp.doconnect.entity.Answer;
import com.wipro.cp.doconnect.entity.Question;
import com.wipro.cp.doconnect.repository.AnswerRepository;
import com.wipro.cp.doconnect.repository.QuestionRepository;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	private QuestionResponseDTO convertQuestionToQuestionResponseDTO(Question question) {
		return new QuestionResponseDTO(question.getId(), question.getQuestion(), question.getTopic(), question.getImages(), question.getPostedBy(), question.getPostedAt(), question.getApprovedBy(), question.getIsApproved());
	}
	
	private AnswerResponseDTO convertAnswerToAnswerResponseDTO(Answer answer) {
		return new AnswerResponseDTO(answer.getId(), answer.getAnswer(), answer.getImages(), answer.getPostedBy(), answer.getPostedAt(), answer.getApprovedBy(), answer.getIsApproved(), convertQuestionToQuestionResponseDTO(answer.getQuestion()));
	}
	
	private List<AnswerResponseDTO> convertAnswerListToAnswerResponseDTOList(List<Answer> answerList) {
		return answerList.stream().map(answer -> convertAnswerToAnswerResponseDTO(answer)).collect(Collectors.toList());
	}
	
	public StatusDTO<List<AnswerResponseDTO>> getAllAnswers(String answerStatus) {
		if (answerStatus.equalsIgnoreCase("all")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, convertAnswerListToAnswerResponseDTOList(answerRepository.findAll()));
		}
		else if (answerStatus.equalsIgnoreCase("approved")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, convertAnswerListToAnswerResponseDTOList(answerRepository.findByIsApprovedTrue()));
		}
		else if (answerStatus.equalsIgnoreCase("unapproved")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, convertAnswerListToAnswerResponseDTOList(answerRepository.findByIsApprovedFalse()));
		}
		else {
			return new StatusDTO<List<AnswerResponseDTO>>("Provided invalid status. Should be one of 'all', 'approved' or 'unapproved'.", false, null);
		}
	}

	public StatusDTO<List<AnswerResponseDTO>> getAllAnswersForQuestionId(Long questionId, String answerStatus) {
		if (answerStatus.equalsIgnoreCase("all")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, convertAnswerListToAnswerResponseDTOList(answerRepository.findByQuestionId(questionId)));
		}
		else if (answerStatus.equalsIgnoreCase("approved")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, convertAnswerListToAnswerResponseDTOList(answerRepository.findByQuestionIdAndIsApprovedTrue(questionId)));
		}
		else if (answerStatus.equalsIgnoreCase("unapproved")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, convertAnswerListToAnswerResponseDTOList(answerRepository.findByQuestionIdAndIsApprovedFalse(questionId)));
		}
		else {
			return new StatusDTO<List<AnswerResponseDTO>>("Provided invalid status. Should be one of 'all', 'approved' or 'unapproved'.", false, null);
		}
	}

}
