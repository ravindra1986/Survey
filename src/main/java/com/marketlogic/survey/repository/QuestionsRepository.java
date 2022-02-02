package com.marketlogic.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketlogic.survey.model.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Long>{

}
