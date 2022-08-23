package com.wipro.cp.doconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.cp.doconnect.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
