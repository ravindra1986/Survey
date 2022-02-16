package com.marketlogic.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marketlogic.survey.model.Answers;

public interface AnswersRepository extends JpaRepository<Answers, Long>{
	@Query("SELECT a FROM Answers a WHERE  a.id = ?1")
	public Answers findAnswerByQuestionId(Long answerId);
}
