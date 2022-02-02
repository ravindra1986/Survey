package com.marketlogic.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketlogic.survey.model.Answers;

public interface AnswersRepository extends JpaRepository<Answers, Long>{

}
