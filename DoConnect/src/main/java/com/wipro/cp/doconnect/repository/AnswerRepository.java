package com.wipro.cp.doconnect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.cp.doconnect.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
	List<Answer> findByQuestionId(Long questionId);
	Optional<Answer> findByIdAndQuestionId(Long answerId, Long questionId);

}
