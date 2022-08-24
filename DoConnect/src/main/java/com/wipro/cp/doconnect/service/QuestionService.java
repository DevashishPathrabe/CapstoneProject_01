package com.wipro.cp.doconnect.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.cp.doconnect.dto.AnswerResponseDTO;
import com.wipro.cp.doconnect.dto.QuestionRequestDTO;
import com.wipro.cp.doconnect.dto.QuestionResponseDTO;
import com.wipro.cp.doconnect.dto.QuestionUpdateDTO;
import com.wipro.cp.doconnect.dto.StatusDTO;
import com.wipro.cp.doconnect.entity.Answer;
import com.wipro.cp.doconnect.entity.Question;
import com.wipro.cp.doconnect.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	private AnswerResponseDTO convertAnswerToAnswerResponseDTO(Answer answer) {
		return new AnswerResponseDTO(answer.getId(), answer.getAnswer(), answer.getImages(), answer.getPostedBy(), answer.getPostedAt(), answer.getApprovedBy(), answer.getIsApproved());
	}
	
	private List<AnswerResponseDTO> convertAnswerListToAnswerResponseDTOList(List<Answer> answerList) {
		return answerList.stream().map(answer -> convertAnswerToAnswerResponseDTO(answer)).collect(Collectors.toList());
	}
	
	private QuestionResponseDTO convertQuestionToQuestionResponseDTO(Question question) {
		return new QuestionResponseDTO(question.getId(), question.getQuestion(), question.getTopic(), question.getImages(), question.getPostedBy(), question.getPostedAt(), question.getApprovedBy(), question.getIsApproved(), convertAnswerListToAnswerResponseDTOList(question.getAnswers()));
	}
	
	private List<QuestionResponseDTO> convertQuestionListToQuestionResponseDTOList(List<Question> questionList) {
		return questionList.stream().map(question -> convertQuestionToQuestionResponseDTO(question)).collect(Collectors.toList());
	}
	
	private boolean checkIfAnswerIsApproved(Answer answer) {
		return answer.getIsApproved();
	}
	
	private QuestionResponseDTO convertQuestionToQuestionResponseDTOWithApprovedAnswers(Question question) {
		List<AnswerResponseDTO> approvedAnswerResponseDTOList = convertAnswerListToAnswerResponseDTOList(question.getAnswers().stream().filter(answer -> checkIfAnswerIsApproved(answer)).collect(Collectors.toList()));
		return new QuestionResponseDTO(question.getId(), question.getQuestion(), question.getTopic(), question.getImages(), question.getPostedBy(), question.getPostedAt(), question.getApprovedBy(), question.getIsApproved(), approvedAnswerResponseDTOList);
	}
	
	private List<QuestionResponseDTO> convertQuestionListToQuestionResponseDTOListWithApprovedAnswers(List<Question> questionList) {
		return questionList.stream().map(question -> convertQuestionToQuestionResponseDTOWithApprovedAnswers(question)).collect(Collectors.toList());
	}
	
	public StatusDTO<List<QuestionResponseDTO>> getAllQuestions(String status, String search) {
		if (search == null) {
			if (status.equalsIgnoreCase("all")) {
				return new StatusDTO<List<QuestionResponseDTO>>("", true, convertQuestionListToQuestionResponseDTOList(questionRepository.findAll()));
			}
			else if (status.equalsIgnoreCase("approved")) {
				return new StatusDTO<List<QuestionResponseDTO>>("", true, convertQuestionListToQuestionResponseDTOListWithApprovedAnswers(questionRepository.findByIsApprovedTrue()));
			}
			else if (status.equalsIgnoreCase("unapproved")) {
				return new StatusDTO<List<QuestionResponseDTO>>("", true, convertQuestionListToQuestionResponseDTOList(questionRepository.findByIsApprovedFalse()));
			}
			else {
				return new StatusDTO<List<QuestionResponseDTO>>("Provided invalid status. Should be one of 'all', 'approved' or 'unapproved'.", false, null);
			}
		}
		else {
			if (status.equalsIgnoreCase("approved")) {
				return new StatusDTO<List<QuestionResponseDTO>>("", true, convertQuestionListToQuestionResponseDTOListWithApprovedAnswers(questionRepository.findByQuestionContainingIgnoreCaseAndIsApprovedTrue(search)));
			}
			else {
				return new StatusDTO<List<QuestionResponseDTO>>("Question search only works with 'approved' status.", false, null);
			}
		}
	}
	
	public StatusDTO<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO, String postedBy) {
		Question question = new Question(questionRequestDTO.getQuestion(), questionRequestDTO.getTopic(), questionRequestDTO.getImages(), postedBy);
		return new StatusDTO<QuestionResponseDTO>("", true, convertQuestionToQuestionResponseDTO(questionRepository.save(question)));
	}
	
	public StatusDTO<QuestionResponseDTO> updateQuestion(QuestionUpdateDTO questionUpdateDTO, Long id, String approvedBy) {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
		if (optionalQuestion.isEmpty()) {
			return new StatusDTO<QuestionResponseDTO>("User with ID " + id + " does not exist.", false, null);
		}
		Question question = optionalQuestion.get();
		question.setIsApproved(questionUpdateDTO.getIsApproved());
		question.setApprovedBy(approvedBy);
		return new StatusDTO<QuestionResponseDTO>("", true, convertQuestionToQuestionResponseDTO(questionRepository.save(question)));
	}
	
	public boolean deleteQuestionById(Long id) {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
		if (optionalQuestion.isEmpty()) {
			return false;
		}
		questionRepository.deleteById(id);
		return true;
	}

}
