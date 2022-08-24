package com.wipro.cp.doconnect.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.cp.doconnect.dto.AnswerRequestDTO;
import com.wipro.cp.doconnect.dto.AnswerResponseDTO;
import com.wipro.cp.doconnect.dto.AnswerUpdateDTO;
import com.wipro.cp.doconnect.dto.StatusDTO;
import com.wipro.cp.doconnect.service.AnswerService;

@RestController
@CrossOrigin
public class AnswerController {
	
	@Autowired
	AnswerService answerService;
	
	@GetMapping("/answers")
	public ResponseEntity<?> getAllAnswers(@RequestParam(name="status") Optional<String> optionalStatus) {
		String answerStatus = "approved";
		if (optionalStatus.isPresent()) {
			answerStatus = optionalStatus.get();
		}
		StatusDTO<List<AnswerResponseDTO>> answerResponseDTOStatus = answerService.getAllAnswers(answerStatus);
		if (!answerResponseDTOStatus.getIsValid()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(answerResponseDTOStatus.getStatusMessage());
		}
		return ResponseEntity.ok(answerResponseDTOStatus.getObject());
	}
	
	@GetMapping("/questions/{questionId}/answers")
	public ResponseEntity<?> getAllAnswersForQuestionId(@PathVariable(value="questionId") Long questionId, @RequestParam(name="status") Optional<String> optionalStatus) {
		String answerStatus = "approved";
		if (optionalStatus.isPresent()) {
			answerStatus = optionalStatus.get();
		}
		StatusDTO<List<AnswerResponseDTO>> answerResponseDTOStatus = answerService.getAllAnswersForQuestionId(questionId, answerStatus);
		if (!answerResponseDTOStatus.getIsValid()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(answerResponseDTOStatus.getStatusMessage());
		}
		return ResponseEntity.ok(answerResponseDTOStatus.getObject());
	}
	
	@PostMapping("/questions/{questionId}/answers")
	public ResponseEntity<?> createAnswerForQuestionId(@PathVariable(value="questionId") Long questionId, @Valid @RequestBody AnswerRequestDTO answerRequestDTO) {
		// TODO: Read the postedBy value from Authorization Header
		String postedBy = "";
		StatusDTO<AnswerResponseDTO> answerResponseDTOStatus = answerService.createAnswerForQuestionId(questionId, answerRequestDTO, postedBy);
		if (!answerResponseDTOStatus.getIsValid()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(answerResponseDTOStatus.getStatusMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(answerResponseDTOStatus.getObject());
	}
	
	@PutMapping("/questions/{questionId}/answers/{answerId}")
	public ResponseEntity<?> updateAnswerForQuestionId(@Valid @RequestBody AnswerUpdateDTO answerUpdateDTO, @PathVariable(value="questionId") Long questionId, @PathVariable(value="answerId") Long answerId) {
		// TODO: Read the approvedBy value from Authorization Header
		String approvedBy = "";
		StatusDTO<AnswerResponseDTO> answerResponseDTOStatus = answerService.updateAnswerForQuestionId(questionId, answerId, answerUpdateDTO, approvedBy);
		if (!answerResponseDTOStatus.getIsValid()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(answerResponseDTOStatus.getStatusMessage());
		}
		return ResponseEntity.ok(answerResponseDTOStatus.getObject());
	}

}
