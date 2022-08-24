package com.wipro.cp.doconnect.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.cp.doconnect.dto.QuestionRequestDTO;
import com.wipro.cp.doconnect.dto.QuestionResponseDTO;
import com.wipro.cp.doconnect.dto.QuestionUpdateDTO;
import com.wipro.cp.doconnect.dto.StatusDTO;
import com.wipro.cp.doconnect.service.QuestionService;

@RestController
@CrossOrigin
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/questions")
	public ResponseEntity<?> getAllQuestions(@RequestParam(name="status") Optional<String> optionalStatus, @RequestParam(name="search") Optional<String> optionalSearch) {
		String status = "approved";
		if (optionalStatus.isPresent()) {
			status = optionalStatus.get();
		}
		String search = null;
		if (optionalSearch.isPresent()) {
			search = optionalSearch.get();
		}
		StatusDTO<List<QuestionResponseDTO>> questionStatus = questionService.getAllQuestions(status, search);
		if (!questionStatus.getIsValid()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(questionStatus.getStatusMessage());
		}
		return ResponseEntity.ok(questionStatus.getObject());
	}
	
	@PostMapping("/questions")
	public ResponseEntity<?> createQuestion(@Valid @RequestBody QuestionRequestDTO questionRequestDTO) {
		// TODO: Read the postedBy value from Authorization Header
		String postedBy = "";
		StatusDTO<QuestionResponseDTO> questionStatus = questionService.createQuestion(questionRequestDTO, postedBy);
		if (!questionStatus.getIsValid()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(questionStatus.getStatusMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(questionStatus.getObject());
	}
	
	@PutMapping("/questions/{id}")
	public ResponseEntity<?> updateQuestion(@Valid @RequestBody QuestionUpdateDTO questionUpdateDTO, @PathVariable Long id) {
		// TODO: Read the approvedBy value from Authorization Header
		String approvedBy = "";
		StatusDTO<QuestionResponseDTO> questionStatus = questionService.updateQuestion(questionUpdateDTO, id, approvedBy);
		if (!questionStatus.getIsValid()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(questionStatus.getStatusMessage());
		}
		return ResponseEntity.ok(questionStatus.getObject());
	}
	
	@DeleteMapping("/questions/{id}")
	public ResponseEntity<?> deleteQuestionById(@PathVariable @Min(1) Long id) {
		boolean deleted = questionService.deleteQuestionById(id);
		if (deleted) {
			return ResponseEntity.ok("Question deleted successfully.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question with ID " + id + " does not exist.");
	}

}
