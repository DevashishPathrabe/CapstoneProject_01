package com.wipro.cp.doconnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.cp.doconnect.dto.AnswerRequestDTO;
import com.wipro.cp.doconnect.dto.AnswerResponseDTO;
import com.wipro.cp.doconnect.dto.AnswerUpdateDTO;
import com.wipro.cp.doconnect.dto.EmailDTO;
import com.wipro.cp.doconnect.dto.StatusDTO;
import com.wipro.cp.doconnect.entity.Answer;
import com.wipro.cp.doconnect.entity.Question;
import com.wipro.cp.doconnect.repository.AnswerRepository;
import com.wipro.cp.doconnect.repository.QuestionRepository;
import com.wipro.cp.doconnect.repository.UserRepository;
import com.wipro.cp.doconnect.util.Utilities;

@Service
public class AnswerServiceImp implements IAnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailServiceImp emailServiceImp;
	
	@Override
	public StatusDTO<List<AnswerResponseDTO>> getAllAnswers(String answerStatus) {
		if (answerStatus.equalsIgnoreCase("all")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, Utilities.convertAnswerListToAnswerResponseDTOList(answerRepository.findAll()));
		}
		else if (answerStatus.equalsIgnoreCase("approved")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, Utilities.convertAnswerListToAnswerResponseDTOList(answerRepository.findByIsApprovedTrue()));
		}
		else if (answerStatus.equalsIgnoreCase("unapproved")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, Utilities.convertAnswerListToAnswerResponseDTOList(answerRepository.findByIsApprovedFalse()));
		}
		else {
			return new StatusDTO<List<AnswerResponseDTO>>("Provided invalid status. Should be one of 'all', 'approved' or 'unapproved'.", false, null);
		}
	}

	@Override
	public StatusDTO<List<AnswerResponseDTO>> getAllAnswersForQuestionId(Long questionId, String answerStatus) {
		if (answerStatus.equalsIgnoreCase("all")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, Utilities.convertAnswerListToAnswerResponseDTOList(answerRepository.findByQuestionId(questionId)));
		}
		else if (answerStatus.equalsIgnoreCase("approved")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, Utilities.convertAnswerListToAnswerResponseDTOList(answerRepository.findByQuestionIdAndIsApprovedTrue(questionId)));
		}
		else if (answerStatus.equalsIgnoreCase("unapproved")) {
			return new StatusDTO<List<AnswerResponseDTO>>("", true, Utilities.convertAnswerListToAnswerResponseDTOList(answerRepository.findByQuestionIdAndIsApprovedFalse(questionId)));
		}
		else {
			return new StatusDTO<List<AnswerResponseDTO>>("Provided invalid status. Should be one of 'all', 'approved' or 'unapproved'.", false, null);
		}
	}
	
	@Override
	public StatusDTO<AnswerResponseDTO> createAnswerForQuestionId(Long questionId, AnswerRequestDTO answerRequestDTO, String postedBy) {
		Optional<Question> optionalQuestion = questionRepository.findById(questionId);
		if (optionalQuestion.isEmpty()) {
			return new StatusDTO<AnswerResponseDTO>("Question with id " + questionId + " does not exist.", false, null);
		}
		Answer answer = new Answer(answerRequestDTO.getAnswer(), answerRequestDTO.getImages(), postedBy, optionalQuestion.get());
		Answer savedAnswer = answerRepository.save(answer);
		String[] recipients = Utilities.getUserEmails(userRepository.findByIsAdminTrue());
		String subject = "Action Required: Approval needed for newly added answer";
		String body = "Dear Admin,\n\nA new answer has been added to DoConnect application for question - '" + optionalQuestion.get().getQuestion() + "'. Please visit the application to either approve or delete the newly added question.\n\nDoConnect Bot\n\n\n\n\n\nAuto generated email. Please do not reply.";
		emailServiceImp.sendNotificationEmail(new EmailDTO(recipients, subject, body));
		return new StatusDTO<AnswerResponseDTO>("", true, Utilities.convertAnswerToAnswerResponseDTO(savedAnswer));
	}
	
	@Override
	public StatusDTO<AnswerResponseDTO> updateAnswerForQuestionId(Long questionId, Long answerId, AnswerUpdateDTO answerUpdateDTO, String approvedBy) {
		boolean questionExists = questionRepository.existsById(questionId);
		if (!questionExists) {
			return new StatusDTO<AnswerResponseDTO>("Question with id " + questionId + " does not exist.", false, null);
		}
		Optional<Answer> optionalAnswer = answerRepository.findByIdAndQuestionId(answerId, questionId);
		if (optionalAnswer.isEmpty()) {
			return new StatusDTO<AnswerResponseDTO>("Answer with id " + answerId + " does not exist for question with id " + questionId + ".", false, null);
		}
		Answer answer = optionalAnswer.get();
		answer.setIsApproved(answerUpdateDTO.getIsApproved());
		answer.setApprovedBy(approvedBy);
		return new StatusDTO<AnswerResponseDTO>("", true, Utilities.convertAnswerToAnswerResponseDTO(answerRepository.save(answer)));
	}
	
	@Override
	public StatusDTO<Boolean> deleteAnswerForQuestionById(Long questionId, Long answerId) {
		boolean questionExists = questionRepository.existsById(questionId);
		if (!questionExists) {
			return new StatusDTO<Boolean>("Question with id " + questionId + " does not exist.", false, null);
		}
		Optional<Answer> optionalAnswer = answerRepository.findByIdAndQuestionId(answerId, questionId);
		if (optionalAnswer.isEmpty()) {
			return new StatusDTO<Boolean>("Answer with id " + answerId + " does not exist for question with id " + questionId + ".", false, null);
		}
		answerRepository.delete(optionalAnswer.get());
		return new StatusDTO<Boolean>("", true, true);
	}

}
